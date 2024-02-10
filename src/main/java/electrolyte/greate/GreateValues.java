package electrolyte.greate;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.minecraft.ChatFormatting.*;

public class GreateValues {

    /**
     * The short names for the stress tiers
     */
    public static final String[] SN = new String[]{"ULS", "LS", "MS", "HS", "ES", "IS", "LuS", "ZPMS", "US", "UHS"};

    /**
     * The short names for the stress tiers, formatted for text
     */
    public static final String[] SNF = new String[]{
            DARK_GRAY + "ULS",
            GRAY + "LS",
            AQUA + "MS",
            GOLD + "HS",
            DARK_PURPLE + "ES",
            BLUE + "IS",
            LIGHT_PURPLE + "LuS",
            RED + "ZPMS",
            DARK_AQUA + "US",
            DARK_RED + "UHS",
    };

    /**
     * The long names for the stress tiers
     */
    public static final String[] STRESS_NAMES = new String[]{
            "Ultra Low Stress",
            "Low Stress",
            "Medium Stress",
            "High Stress",
            "Extreme Stress",
            "Insane Stress",
            "Ludicrous Stress",
            "ZPM Stress",
            "Ultimate Stress",
            "Ultra High Stress",
            "Ultra Excessive Stress",
            "Ultra Immense Stress",
            "Ultra Extreme Stress",
            "Overpowered Stress",
            "Maximum Stress"
    };

    public static int convertGTEUToTier(List<Content> content) {
        if (content.isEmpty()) return GTValues.ULV;
        long eut = (Long) content.get(0).getContent();
        for (int i = 0; i < GTValues.V.length; i++) {
            long voltage = GTValues.V[i];
            if (eut <= voltage) {
                return GTValues.ALL_TIERS[i];
            }
        }
        return GTValues.MAX;
    }

    /**
     * Tier materials
     * Can eventually be replaced with GTMaterials.VOLTAGE_COMMON_MATERIALS
     */
    public static final String[] TM = new String[]{
            "andesite",
            "steel",
            "aluminium",
            "stainless_steel",
            "titanium",
            "tungstensteel",
            "palladium",
            "naquadah",
            "darmstadtium",
            "neutronium",
    };;

    /**
     * Belt materials
     */
    public static final String[] BM = new String[]{
            "rubber",
            "silicone",
            "polyethylene",
            "polytetrafluoroethylene",
            "polybenzimidazole",
    };
}