package art.celestro.berserkermod;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BerserkerArmorItems {

    public static final ArmorMaterial BD_MATERIAL = BerserkerArmorMaterials.BLOOD_DIAMOND;
	public static final Item BLOOD_DIAMOND_HORSE_ARMOR = new HorseArmorItem(16, "blood_diamond", (new Item.Settings()).maxCount(1).group(ItemGroup.MISC));

    public static final Item BLOOD_DIAMOND_HELMET = register("blood_diamond_helmet",
                    new BloodDiamondArmor(BD_MATERIAL, EquipmentSlot.HEAD));
    public static final Item BLOOD_DIAMOND_CHESTPLATE = register("blood_diamond_chestplate",
                    new BloodDiamondArmor(BD_MATERIAL, EquipmentSlot.CHEST));
    public static final Item BLOOD_DIAMOND_LEGGINGS = register("blood_diamond_leggings",
                    new BloodDiamondArmor(BD_MATERIAL, EquipmentSlot.LEGS));
    public static final Item BLOOD_DIAMOND_BOOTS = register("blood_diamond_boots",
                    new BloodDiamondArmor(BD_MATERIAL, EquipmentSlot.FEET));

    private static Item register(String id, Item item) {
        return register(new Identifier("berserker_mod", id), item);
    }

    private static Item register(Identifier id, Item item) {
            if (item instanceof BlockItem) {
                    ((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
            }

            return Registry.register(Registry.ITEM, id, item);
    }

    public static void init() {
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_horse_armor"), BLOOD_DIAMOND_HORSE_ARMOR);
    }
}