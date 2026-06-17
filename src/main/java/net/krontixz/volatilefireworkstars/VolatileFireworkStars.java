package net.krontixz.volatilefireworkstars;

import net.fabricmc.api.ModInitializer;
import net.krontixz.volatilefireworkstars.item.ModItems;

public class VolatileFireworkStars implements ModInitializer {
	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
