/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.createdimensions.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;

import net.mcreator.createdimensions.item.MimTransmitterItem;
import net.mcreator.createdimensions.CreateDimensionsMod;

public class CreateDimensionsModItems {
	public static Item MIM_TRANSMITTER;

	public static void load() {
		MIM_TRANSMITTER = register("mim_transmitter", new MimTransmitterItem());
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CreateDimensionsMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ItemProperties.register(item, new ResourceLocation("blocking"), (ClampedItemPropertyFunction) ItemProperties.getProperty(Items.SHIELD, new ResourceLocation("blocking")));
	}
}
