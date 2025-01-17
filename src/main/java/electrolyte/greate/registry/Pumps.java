package electrolyte.greate.registry;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import electrolyte.greate.Greate;
import electrolyte.greate.content.fluids.pump.TieredPumpBlock;
import electrolyte.greate.content.kinetics.TieredBlockMaterials;
import electrolyte.greate.foundation.data.GreateBuilderTransformers;
import net.minecraft.world.level.material.MapColor;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static electrolyte.greate.Greate.REGISTRATE;
import static electrolyte.greate.GreateValues.TM;

public class Pumps {

	public static BlockEntry<TieredPumpBlock>[] MECHANICAL_PUMPS = new BlockEntry[10];
	public static BlockEntry<TieredPumpBlock>
			ANDESITE_MECHANICAL_PUMP,
			STEEL_MECHANICAL_PUMP,
			ALUMINIUM_MECHANICAL_PUMP,
			STAINLESS_STEEL_MECHANICAL_PUMP,
			TITANIUM_MECHANICAL_PUMP,
			TUNGSTENSTEEL_MECHANICAL_PUMP,
			PALLADIUM_MECHANICAL_PUMP,
			NAQUADAH_MECHANICAL_PUMP,
			DARMSTADTIUM_MECHANICAL_PUMP,
			NEUTRONIUM_MECHANICAL_PUMP;

	public static void register() {
		REGISTRATE.setCreativeTab(Greate.GREATE_TAB);

		MECHANICAL_PUMPS[ULV] = ANDESITE_MECHANICAL_PUMP = pump(ULV, 0.5);
		MECHANICAL_PUMPS[LV] = STEEL_MECHANICAL_PUMP = pump(LV, 1.0);
		MECHANICAL_PUMPS[MV] = ALUMINIUM_MECHANICAL_PUMP = pump(MV, 1.5);
		MECHANICAL_PUMPS[HV] = STAINLESS_STEEL_MECHANICAL_PUMP = pump(HV, 2.0);
		MECHANICAL_PUMPS[EV] = TITANIUM_MECHANICAL_PUMP = pump(EV, 2.5);
		MECHANICAL_PUMPS[IV] = TUNGSTENSTEEL_MECHANICAL_PUMP = pump(IV, 3.0);
		MECHANICAL_PUMPS[LuV] = PALLADIUM_MECHANICAL_PUMP = pump(LuV, 3.5);
		MECHANICAL_PUMPS[ZPM] = NAQUADAH_MECHANICAL_PUMP = pump(ZPM, 4.0);
		MECHANICAL_PUMPS[UV] = DARMSTADTIUM_MECHANICAL_PUMP = pump(UV, 4.5);
		MECHANICAL_PUMPS[UHV] = NEUTRONIUM_MECHANICAL_PUMP = pump(UHV, 5.0);
	}

	public static BlockEntry<TieredPumpBlock> pump(int tier, double pumpImpact) {
		return REGISTRATE
				.block(TM[tier].getName() + "_mechanical_pump", TieredPumpBlock::new)
				.initialProperties(SharedProperties::copperMetal)
				.properties(p -> p.mapColor(MapColor.STONE))
				.transform(pickaxeOnly())
				.transform(GreateBuilderTransformers.tieredMechanicalPump())
				.transform(BlockStressDefaults.setImpact(pumpImpact))
				.transform(TieredBlockMaterials.setMaterialForBlock(TM[tier]))
				.onRegister(c -> c.setTier(tier))
				.register();
	}
}
