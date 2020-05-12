package art.celestro.berserkermod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BerserkerMod implements ModInitializer {

	public static final ToolMaterial TOOL_MATERIAL = new BloodDiamondMaterial();
	public static final ArmorMaterial ARMOR_MATERIAL = new BloodDiamondArmorMaterial();
	public static final Item BLOOD_DIAMOND_SWORD = new BloodDiamondSword(TOOL_MATERIAL, 4, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
	public static final Item BLOOD_DIAMOND_PICKAXE = new BloodDiamondPickaxe(TOOL_MATERIAL, 2, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_AXE = new BloodDiamondAxe(TOOL_MATERIAL, 6.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_SHOVEL = new BloodDiamondShovel(TOOL_MATERIAL, 1.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_HOE = new BloodDiamondHoe(TOOL_MATERIAL, -4, 1.0F, (new Item.Settings()).group(ItemGroup.TOOLS));

	public static final Item BLOOD_DIAMOND_HELMET = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item BLOOD_DIAMOND_CHESTPLATE = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item BLOOD_DIAMOND_LEGGINGS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item BLOOD_DIAMOND_BOOTS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));

	public static final Item BLOOD_DIAMOND_HORSE_ARMOR = new HorseArmorItem(16, "blood_diamond", (new Item.Settings()).maxCount(1).group(ItemGroup.MISC));

	public static final Item BLOOD_DIAMOND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Block BLOOD_DIAMOND_BLOCK = new Block(Block.Settings.of(Material.METAL, MaterialColor.RED).strength(5.0F, 6.0F));
	public static final Block BLOOD_DIAMOND_ORE = new OreBlock(Block.Settings.of(Material.STONE).strength(3.0F, 3.0F));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond"), BLOOD_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_sword"), BLOOD_DIAMOND_SWORD);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_pickaxe"), BLOOD_DIAMOND_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_axe"), BLOOD_DIAMOND_AXE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_shovel"), BLOOD_DIAMOND_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_hoe"), BLOOD_DIAMOND_HOE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_helmet"), BLOOD_DIAMOND_HELMET);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_chestplate"), BLOOD_DIAMOND_CHESTPLATE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_leggings"), BLOOD_DIAMOND_LEGGINGS);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_boots"), BLOOD_DIAMOND_BOOTS);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_horse_armor"), BLOOD_DIAMOND_HORSE_ARMOR);
		Registry.register(Registry.BLOCK, new Identifier("berserker_mod", "blood_diamond_block"), BLOOD_DIAMOND_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("berserker_mod", "blood_diamond_ore"), BLOOD_DIAMOND_ORE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_block"), new BlockItem(BLOOD_DIAMOND_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_ore"), new BlockItem(BLOOD_DIAMOND_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.BIOME.forEach(this::handleBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
	}

	private void handleBiome(Biome biome) {
		if(biome.getCategory() == Biome.Category.NETHER) {
			int veinCount;
			veinCount = 1;

			biome.addFeature(
					GenerationStep.Feature.UNDERGROUND_ORES,
					Feature.ORE.configure(
							new OreFeatureConfig(
									OreFeatureConfig.Target.NETHERRACK,
									BLOOD_DIAMOND_ORE.getDefaultState(),
									3 // ore vein size
							)).createDecoratedFeature(
							Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
									veinCount, // veins per chunk
									0,
									1, // min y level
									128 // max y level
							))));
		}
	}
}
