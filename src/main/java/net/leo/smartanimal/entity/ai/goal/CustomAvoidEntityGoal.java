package net.leo.smartanimal.entity.ai.goal;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class CustomAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

    public CustomAvoidEntityGoal(LivingEntity entity, Class<T> classToAvoid, float avoidDistance, double walkSpeedModifier, double sprintSpeedModifier) {
        super((PathfinderMob) entity, classToAvoid, avoidDistance, walkSpeedModifier, sprintSpeedModifier);
    }

    @Override
    public boolean canUse() {
        if (this.toAvoid instanceof Player) {
            Player player = (Player) this.toAvoid;
            if (player.isCrouching()
                            ||
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == Items.LEATHER_BOOTS
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.LEATHER_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.IRON_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.GOLDEN_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.DIAMOND_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.CHAINMAIL_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
                            ||
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items.NETHERITE_LEGGINGS
                            &&
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) >0
            ) {

                return false;  // Non scappare se il giocatore è accovacciato o indossa stivali di pelle
            }
        }
        return super.canUse();
    }
}
