package net.krontixz.volatilefireworkstars.mixin;

import net.krontixz.volatilefireworkstars.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin {

	@Inject(method = "explode", at = @At("HEAD"))
	private void onExplode(CallbackInfo ci) {
		FireworkRocketEntity self = (FireworkRocketEntity) (Object) this;
		ItemStack stack = self.getStack();
		FireworksComponent component = stack.get(DataComponentTypes.FIREWORKS);

		if (component != null) {
			for (FireworkExplosionComponent explosion : component.explosions()) {
				if (explosion.item() != null && explosion.item().isOf(ModItems.VOLATILE_FIREWORK_STAR)) {
					double radius = 6.0;
					self.getWorld().createExplosion(self, self.getX(), self.getY(), self.getZ(), 4.0f, World.ExplosionSourceType.TNT);

					List<LivingEntity> entities = self.getWorld().getEntitiesByClass(LivingEntity.class, new Box(self.getX() - radius, self.getY() - radius, self.getZ() - radius, self.getX() + radius, self.getY() + radius, self.getZ() + radius), entity -> true);
					for (LivingEntity entity : entities) {
						entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
					}
					break;
				}
			}
		}
	}
}
