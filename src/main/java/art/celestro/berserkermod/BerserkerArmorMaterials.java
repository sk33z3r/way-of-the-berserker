package art.celestro.berserkermod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum BerserkerArmorMaterials implements ArmorMaterial {
    BLOOD_DIAMOND("blood_diamond", 40, new int[]{14, 16, 17, 12}, 10, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, new int[]{3,7,9,4}, 4.0F, 0.2F, Ingredient.ofItems(BerserkerMod.BLOOD_DIAMOND));

    private final String name;
    private final int durabilityMultiplier;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final Ingredient repairIngredient;
    private final float knockbackResistance;
    private final int[] baseDurability;
    private final int[] protectionAmounts;

    BerserkerArmorMaterials(String name, int durabilityMultiplier, int[] baseDurability, int enchantability, SoundEvent soundEvent, int[] protectionAmounts, float toughness, float knockbackResistance, Ingredient repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.baseDurability = baseDurability;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.protectionAmounts = protectionAmounts;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    public int getDurability(EquipmentSlot equipmentSlot_1) {
        return baseDurability[equipmentSlot_1.getEntitySlotId()] * this.durabilityMultiplier;
    }

    public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
        return this.protectionAmounts[equipmentSlot_1.getEntitySlotId()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Environment(EnvType.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
