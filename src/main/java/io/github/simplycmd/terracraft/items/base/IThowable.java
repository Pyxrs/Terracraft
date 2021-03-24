package io.github.simplycmd.terracraft.items.base;

import io.github.simplycmd.terracraft.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.explosion.Explosion.DestructionType;

public abstract interface IThowable {

	public enum ThowableType {
		NORMAL,
		EXPLOSIVE
	}

	public abstract Entity thrownEntity();

	public abstract ThowableType setThrowType();

	public abstract int framesUntilExplosion();

	public abstract int explosionRadius();

	public default void throwItem() {
		if (MinecraftClient.getInstance().player != null) {
			if (setThrowType() == ThowableType.NORMAL) {
				thrownEntity().world.spawnEntity(thrownEntity());
				thrownEntity().setPos(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ());
			} else if (setThrowType() == ThowableType.EXPLOSIVE) {
				Main.isThrownItemThrown = true;
				thrownEntity().world.spawnEntity(thrownEntity());
				thrownEntity().setPos(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ());
				if (Main.throwTicks >= framesUntilExplosion()) {
					thrownEntity().world.createExplosion(thrownEntity(), thrownEntity().getX(), thrownEntity().getY(), thrownEntity().getZ(), explosionRadius(), DestructionType.DESTROY);
					Main.throwTicks = 0;
					Main.isThrownItemThrown = false;
				}
			}
		}
	}
} 