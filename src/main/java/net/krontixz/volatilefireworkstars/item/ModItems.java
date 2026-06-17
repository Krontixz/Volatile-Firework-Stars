package net.krontixz.volatilefireworkstars.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item VOLATILE_FIREWORK_STAR = new Item(new Item.Settings());

	public static void registerModItems() {
		Registry.register(Registries.ITEM, Identifier.of("volatilefireworkstars", "volatile_firework_star"), VOLATILE_FIREWORK_STAR);
	}
}
