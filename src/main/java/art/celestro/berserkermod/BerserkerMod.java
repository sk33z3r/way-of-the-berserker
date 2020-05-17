package art.celestro.berserkermod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.*;
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

	public static final ToolMaterial BLOOD_DIAMOND_MATERIAL = new BloodDiamondMaterial();
	public static final ToolMaterial FLINT_MATERIAL = new FlintMaterial();
	public static final Item BLOOD_DIAMOND_SWORD = new BloodDiamondSword(BLOOD_DIAMOND_MATERIAL, 4, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
	public static final Item BLOOD_DIAMOND_PICKAXE = new BloodDiamondPickaxe(BLOOD_DIAMOND_MATERIAL, 2, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_AXE = new BloodDiamondAxe(BLOOD_DIAMOND_MATERIAL, 6.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_SHOVEL = new BloodDiamondShovel(BLOOD_DIAMOND_MATERIAL, 1.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item BLOOD_DIAMOND_HOE = new BloodDiamondHoe(BLOOD_DIAMOND_MATERIAL, -4, 1.0F, (new Item.Settings()).group(ItemGroup.TOOLS));

	public static final Item BLOOD_DIAMOND = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Block BLOOD_DIAMOND_BLOCK = new Block(Block.Settings.of(Material.METAL, MaterialColor.RED).strength(5.0F, 6.0F));
	public static final Block BLOOD_DIAMOND_ORE = new OreBlock(Block.Settings.of(Material.STONE).strength(3.0F, 3.0F));

	public static final Item FLINT_HATCHET = new FlintHatchet(FLINT_MATERIAL, 3.0F, -3.4F, (new Item.Settings()).group(ItemGroup.COMBAT));
	public static final Item FLINT_DAGGER = new FlintDagger(FLINT_MATERIAL, 0.6F, -2.0F, (new Item.Settings()).group(ItemGroup.COMBAT));
	public static final Item FLINT_SWORD = new FlintSword(FLINT_MATERIAL, 1, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond"), BLOOD_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_sword"), BLOOD_DIAMOND_SWORD);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_pickaxe"), BLOOD_DIAMOND_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_axe"), BLOOD_DIAMOND_AXE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_shovel"), BLOOD_DIAMOND_SHOVEL);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_hoe"), BLOOD_DIAMOND_HOE);
		Registry.register(Registry.BLOCK, new Identifier("berserker_mod", "blood_diamond_block"), BLOOD_DIAMOND_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("berserker_mod", "blood_diamond_ore"), BLOOD_DIAMOND_ORE);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_block"), new BlockItem(BLOOD_DIAMOND_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "blood_diamond_ore"), new BlockItem(BLOOD_DIAMOND_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
		BerserkerArmorItems.init();

		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "flint_hatchet"), FLINT_HATCHET);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "flint_dagger"), FLINT_DAGGER);
		Registry.register(Registry.ITEM, new Identifier("berserker_mod", "flint_sword"), FLINT_SWORD);

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
