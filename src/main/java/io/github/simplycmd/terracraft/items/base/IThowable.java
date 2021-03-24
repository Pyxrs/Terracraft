package io.github.simplycmd.terracraft.items.base;

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
	
	public abstract int explosionRadias();
	
	public default void throwItem() {
		if (MinecraftClient.getInstance().player != null) {
			if (setThrowType() == ThowableType.NORMAL) {
				thrownEntity().world.spawnEntity(thrownEntity());
				thrownEntity().setPos(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ());
			} else if (setThrowType() == ThowableType.EXPLOSIVE) {
				thrownEntity().world.spawnEntity(thrownEntity());
				thrownEntity().setPos(MinecraftClient.getInstance().player.getX(), MinecraftClient.getInstance().player.getY(), MinecraftClient.getInstance().player.getZ());
				thrownEntity().world.createExplosion(thrownEntity(), thrownEntity().getX(), thrownEntity().getY(), thrownEntity().getZ(), explosionRadias(), DestructionType.DESTROY);
			}
		}
	}
} 