package electrolyte.greate;

import electrolyte.greate.registry.ModBlocks;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Greate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GreateEventHandler {

    @SubscribeEvent
    public static void assignShaftTiers(AddReloadListenerEvent event) {
        ModBlocks.ANDESITE_SHAFT.get().setCapacityTier(GreateEnums.TIER.ULTRA_LOW);
        ModBlocks.STEEL_SHAFT.get().setCapacityTier(GreateEnums.TIER.LOW);
        ModBlocks.ALUMINIUM_SHAFT.get().setCapacityTier(GreateEnums.TIER.MEDIUM);
        ModBlocks.STAINLESS_STEEL_SHAFT.get().setCapacityTier(GreateEnums.TIER.HIGH);
        ModBlocks.TITANIUM_SHAFT.get().setCapacityTier(GreateEnums.TIER.EXTREME);
        ModBlocks.TUNGSTENSTEEL_SHAFT.get().setCapacityTier(GreateEnums.TIER.INSANE);
        ModBlocks.PALLADIUM_SHAFT.get().setCapacityTier(GreateEnums.TIER.LUDICRIOUS);
        ModBlocks.NAQUADAH_SHAFT.get().setCapacityTier(GreateEnums.TIER.ZPM);
        ModBlocks.DARMSTADTIUM_SHAFT.get().setCapacityTier(GreateEnums.TIER.ULTIMATE);
        ModBlocks.NEUTRONIUM_SHAFT.get().setCapacityTier(GreateEnums.TIER.ULTIMATE_HIGH);
    }
}
