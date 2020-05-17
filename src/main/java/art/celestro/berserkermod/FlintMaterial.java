package art.celestro.berserkermod;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

class FlintMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 90;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 3.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0.0F;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.FLINT);
    }
}
