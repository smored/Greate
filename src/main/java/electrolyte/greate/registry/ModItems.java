package electrolyte.greate.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import electrolyte.greate.Greate;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.simibubi.create.AllItems.*;
import static electrolyte.greate.Greate.REGISTRATE;
import static electrolyte.greate.GreateValues.TM;

public class ModItems {

    // Alloy
    public static final ItemEntry<Item>[] ALLOYS = new ItemEntry[10];
    public static ItemEntry<Item>
            STEEL_ALLOY,
            ALUMINIUM_ALLOY,
            STAINLESS_STEEL_ALLOY,
            TITANIUM_ALLOY,
            TUNGSTENSTEEL_ALLOY,
            PALLADIUM_ALLOY,
            NAQUADAH_ALLOY,
            DARMSTADTIUM_ALLOY,
            NEUTRONIUM_ALLOY;

    // Whisk
    public static final ItemEntry<Item>[] WHISKS = new ItemEntry[10];
    public static ItemEntry<Item>
            STEEL_WHISK,
            ALUMINIUM_WHISK,
            STAINLESS_STEEL_WHISK,
            TITANIUM_WHISK,
            TUNGSTENSTEEL_WHISK,
            PALLADIUM_WHISK,
            NAQUADAH_WHISK,
            DARMSTADTIUM_WHISK,
            NEUTRONIUM_WHISK;

    public static final ItemEntry<Item>[] PROPELLERS = new ItemEntry[10];
    public static ItemEntry<Item>
            STEEL_PROPELLER,
            ALUMINIUM_PROPELLER,
            STAINLESS_STEEL_PROPELLER,
            TITANIUM_PROPELLER,
            TUNGSTENSTEEL_PROPELLER,
            PALLADIUM_PROPELLER,
            NAQUADAH_PROPELLER,
            DARMSTADTIUM_PROPELLER,
            NEUTRONIUM_PROPELLER;

    public static ItemEntry<Item> ULV_CONVEYOR_MODULE;
    public static ItemEntry<Item> ULV_ELECTRIC_MOTOR;

    public static void register() {
        REGISTRATE.setCreativeTab(Greate.GREATE_TAB);

        ALLOYS[ULV] = ANDESITE_ALLOY;
        ALLOYS[LV] = STEEL_ALLOY = alloy(LV);
        ALLOYS[MV] = ALUMINIUM_ALLOY = alloy(MV);
        ALLOYS[HV] = STAINLESS_STEEL_ALLOY = alloy(HV);
        ALLOYS[EV] = TITANIUM_ALLOY = alloy(EV);
        ALLOYS[IV] = TUNGSTENSTEEL_ALLOY = alloy(IV);
        ALLOYS[LuV] = PALLADIUM_ALLOY = alloy(LuV);
        ALLOYS[ZPM] = NAQUADAH_ALLOY = alloy(ZPM);
        ALLOYS[UV] = DARMSTADTIUM_ALLOY = alloy(UV);
        ALLOYS[UHV] = NEUTRONIUM_ALLOY = alloy(UHV);

        PROPELLERS[ULV] = PROPELLER;
        PROPELLERS[LV] = STEEL_PROPELLER = propeller(LV);
        PROPELLERS[MV] = ALUMINIUM_PROPELLER = propeller(MV);
        PROPELLERS[HV] = STAINLESS_STEEL_PROPELLER = propeller(HV);
        PROPELLERS[EV] = TITANIUM_PROPELLER = propeller(EV);
        PROPELLERS[IV] = TUNGSTENSTEEL_PROPELLER = propeller(IV);
        PROPELLERS[LuV] = PALLADIUM_PROPELLER = propeller(LuV);
        PROPELLERS[ZPM] = NAQUADAH_PROPELLER = propeller(ZPM);
        PROPELLERS[UV] = DARMSTADTIUM_PROPELLER = propeller(UV);
        PROPELLERS[UHV] = NEUTRONIUM_PROPELLER = propeller(UHV);

        WHISKS[ULV] = WHISK;
        WHISKS[LV] = STEEL_WHISK = whisk(LV);
        WHISKS[MV] = ALUMINIUM_WHISK = whisk(MV);
        WHISKS[HV] = STAINLESS_STEEL_WHISK = whisk(HV);
        WHISKS[EV] = TITANIUM_WHISK = whisk(EV);
        WHISKS[IV] = TUNGSTENSTEEL_WHISK = whisk(IV);
        WHISKS[LuV] = PALLADIUM_WHISK = whisk(LuV);
        WHISKS[ZPM] = NAQUADAH_WHISK = whisk(ZPM);
        WHISKS[UV] = DARMSTADTIUM_WHISK = whisk(UV);
        WHISKS[UHV] = NEUTRONIUM_WHISK = whisk(UHV);

        ULV_CONVEYOR_MODULE = REGISTRATE.item("ulv_conveyor_module", Item::new).lang("ULV Conveyor Module").register();
        ULV_ELECTRIC_MOTOR = REGISTRATE.item("ulv_electric_motor", Item::new).lang("ULV Electric Motor").register();
    }

    public static ItemEntry<Item> alloy(int tier) {
        return REGISTRATE
                .item(TM[tier].getName() + "_alloy", Item::new)
                .model((c, p) -> p.withExistingParent(c.getName(), "item/generated")
                        .texture("layer0", p.modLoc("item/" + c.getName().substring(0, c.getName().length() - 6) + "/alloy")))
                .register();
    }

    public static ItemEntry<Item> whisk(int tier) {
        return REGISTRATE
                .item(TM[tier].getName() + "_whisk", Item::new)
                .model((c, p) -> p.withExistingParent(c.getName(), "item/generated")
                        .texture("layer0", p.modLoc("item/" + c.getName().substring(0, c.getName().length() - 6) + "/whisk")))
                .register();
    }

    public static ItemEntry<Item> propeller(int tier) {
        return REGISTRATE
                .item(TM[tier].getName() + "_propeller", Item::new)
                .model((c, p) -> p.withExistingParent(c.getName(), "item/generated")
                        .texture("layer0", p.modLoc("item/" + c.getName().substring(0, c.getName().length() - 10) + "/propeller")))
                .register();
    }
}
