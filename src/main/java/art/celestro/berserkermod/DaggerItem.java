package art.celestro.berserkermod;

import net.minecraft.item.*;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import java.util.function.Consumer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

public class DaggerItem extends ToolItem implements Vanishable {
   private final float attackDamage;
   private final Multimap<EntityAttribute, EntityAttributeModifier> field_23745;

   public DaggerItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Settings settings) {
      super(toolMaterial, settings);
      this.attackDamage = (float)attackDamage + toolMaterial.getAttackDamage();
      Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
      builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
      builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));
      this.field_23745 = builder.build();
   }

   public float getAttackDamage() {
      return this.attackDamage;
   }

   public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
      stack.damage(1, (LivingEntity) attacker, (Consumer) ((targetx) -> {
          ((LivingEntity) targetx).sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
      }));
      return true;
   }

   public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
      return slot == EquipmentSlot.MAINHAND ? this.field_23745 : super.getAttributeModifiers(slot);
   }
}
