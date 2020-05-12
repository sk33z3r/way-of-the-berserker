package art.celestro.berserkermod;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

class BloodDiamondMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 2048;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 11.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4.0F;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 9;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(BerserkerMod.BLOOD_DIAMOND);
    }
}
