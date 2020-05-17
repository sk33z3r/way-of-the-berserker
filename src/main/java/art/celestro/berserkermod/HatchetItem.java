package art.celestro.berserkermod;

import net.minecraft.item.*;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;

public class HatchetItem extends MiningToolItem {
   private static final Set<Material> field_23139;
   private static final Set<Block> EFFECTIVE_BLOCKS;

   protected HatchetItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
      super(attackDamage, attackSpeed, material, EFFECTIVE_BLOCKS, settings);
   }

   public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
      Material material = state.getMaterial();
      return field_23139.contains(material) ? this.miningSpeed : super.getMiningSpeedMultiplier(stack, state);
   }

   static {
      field_23139 = Sets.newHashSet(new Material[]{Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.REPLACEABLE_PLANT, Material.BAMBOO, Material.PUMPKIN});
      EFFECTIVE_BLOCKS = Sets.newHashSet(new Block[]{Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON});
   }
}
