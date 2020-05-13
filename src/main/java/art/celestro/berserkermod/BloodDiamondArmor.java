package art.celestro.berserkermod;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BloodDiamondArmor extends ArmorItem {
    private static final UUID[] MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),   // 0 - FEET
                                                         UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),   // 1 - LEGS
                                                         UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),   // 2 - CHEST
                                                         UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") }; // 3 - HEAD
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    private final int protection;
    private final float toughness;
    private final float knockbackResistance;

    public BloodDiamondArmor(ArmorMaterial material, EquipmentSlot slot) {
            super(material, slot, (new Item.Settings()).group(ItemGroup.COMBAT));
            this.protection = material.getProtectionAmount(slot);
            this.toughness = material.getToughness();
            this.knockbackResistance = material.getKnockbackResistance();
            Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            UUID uUID = MODIFIERS[slot.getEntitySlotId()];
            builder.put(EntityAttributes.GENERIC_ARMOR,
                new EntityAttributeModifier(uUID, "Armor modifier", (double) this.protection, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                new EntityAttributeModifier(uUID, "Armor toughness", (double) this.toughness, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                new EntityAttributeModifier(uUID, "Armor knockback resistance", (double) this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));

            this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
            return equipmentSlot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public int getProtection() {
            return this.protection;
    }

    @Override
    public float method_26353() {
            return this.toughness;
    }
}