package art.celestro.berserkermod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

class BloodDiamondArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[]{14, 16, 17, 12};
    private static final int[] PROTECTION_AMOUNT = new int[]{3,7,9,4};


    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 40;
    }

    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNT[slot.getEntitySlotId()];
    }

    public int getEnchantability() {
        return 10;
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(BerserkerMod.BLOOD_DIAMOND);
    }

    @Environment(EnvType.CLIENT)
    public String getName() {
        return "blood_diamond";
    }

    public float getToughness() {
        return 4.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 5.0F;
    }
}