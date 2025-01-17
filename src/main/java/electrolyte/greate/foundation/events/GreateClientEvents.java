package electrolyte.greate.foundation.events;

import electrolyte.greate.content.kinetics.belt.item.TieredBeltConnectorHandler;
import electrolyte.greate.content.kinetics.fan.TieredAirCurrent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class GreateClientEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event) {
        if(!isGameActive()) return;
        if(event.phase == Phase.START) {
            TieredAirCurrent.tickClientPlayerSounds();
            return;
        }
        TieredBeltConnectorHandler.tick();
    }

    protected static boolean isGameActive() {
        return !(Minecraft.getInstance().level == null || Minecraft.getInstance().player == null);
    }
}
