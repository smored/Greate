package electrolyte.greate.mixin;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticEffectHandler;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.Lang;
import electrolyte.greate.Greate;
import electrolyte.greate.content.kinetics.simpleRelays.ITieredKineticBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(KineticBlockEntity.class)
public abstract class MixinKineticBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation, IHaveHoveringInformation, ITieredKineticBlockEntity {

    @Shadow public abstract float getSpeed();
    @Shadow protected float speed;
    @Shadow protected float capacity;
    @Shadow public abstract void onSpeedChanged(float previousSpeed);
    @Shadow protected KineticEffectHandler effects;

    @Shadow public abstract boolean hasNetwork();

    @Unique protected float greate_shaftMaxCapacity;
    @Unique protected boolean greate_OverCapacity;
    @Unique protected float greate_networkMaxCapacity;

    public MixinKineticBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "write", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/blockEntity/SmartBlockEntity;write(Lnet/minecraft/nbt/CompoundTag;Z)V"), remap = false)
    private void greate_Write(CompoundTag compound, boolean clientPacket, CallbackInfo ci) {
        compound.putFloat("MaxCapacity", getMaxCapacityFromBlock(this.getBlockState().getBlock()));
        if(hasNetwork()) {
            CompoundTag networkTag = compound.getCompound("Network");
            networkTag.putFloat("MaxCapacity", greate_networkMaxCapacity);
            compound.put("Network", networkTag);
        }
    }

    @Inject(method = "read", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/transmission/sequencer/SequencedGearshiftBlockEntity$SequenceContext;fromNBT(Lnet/minecraft/nbt/CompoundTag;)Lcom/simibubi/create/content/kinetics/transmission/sequencer/SequencedGearshiftBlockEntity$SequenceContext;"), remap = false)
    private void greate_Read(CompoundTag tag, boolean clientPacket, CallbackInfo ci) {
        boolean overCapacityBefore = greate_OverCapacity;
        greate_shaftMaxCapacity = tag.getFloat("MaxCapacity");

        if(tag.contains("Network")) {
            CompoundTag networkTag = tag.getCompound("Network");
            capacity = networkTag.getFloat("Capacity");
            greate_networkMaxCapacity = networkTag.getFloat("MaxCapacity");
            greate_OverCapacity = capacity > greate_networkMaxCapacity;
        }

        if(clientPacket && overCapacityBefore != greate_OverCapacity && speed != 0) {
            effects.triggerOverStressedEffect();
        }
    }

    @Override
    public void updateFromNetwork(float maxStress, float currentStress, int networkSize, float networkMaxCapacity) {
        this.greate_networkMaxCapacity = networkMaxCapacity;
        boolean overCapacity = capacity > networkMaxCapacity;
        setChanged();

        if(overCapacity != this.greate_OverCapacity) {
            float prevSpeed = getSpeed();
            this.greate_OverCapacity = overCapacity;
            onSpeedChanged(prevSpeed);
            sendData();
        }
    }

    @Inject(method = "clearKineticInformation", at = @At("RETURN"), remap = false)
    private void greate_clearKineticInformation(CallbackInfo ci) {
        greate_shaftMaxCapacity = Integer.MAX_VALUE;
        greate_networkMaxCapacity = Integer.MAX_VALUE;
        greate_OverCapacity = false;
    }

    @Inject(method = "getSpeed", at = @At("RETURN"), remap = false, cancellable = true)
    private void greate_getSpeed(CallbackInfoReturnable<Float> cir) {
        if(greate_OverCapacity) cir.setReturnValue(0.0F);
    }

    @Inject(method = "addToTooltip", at = @At("RETURN"), cancellable = true, remap = false)
    private void greate_addToTooltip(List<Component> tooltip, boolean isPlayerSneaking, CallbackInfoReturnable<Boolean> cir) {
        if(greate_OverCapacity) {
            Lang.builder(Greate.MOD_ID).translate("tooltip.overcapacity").style(ChatFormatting.GOLD).forGoggles(tooltip);
            Component hint = Lang.builder(Greate.MOD_ID).translate("tooltip.overcapacity.description").component();
            List<Component> cutString = TooltipHelper.cutTextComponent(hint, TooltipHelper.Palette.GRAY_AND_WHITE);
            for (Component component : cutString) {
                Lang.builder().add(component.copy()).forGoggles(tooltip);
            }
            cir.setReturnValue(true);
        }
    }
}
