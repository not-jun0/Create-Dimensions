
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.createdimensions.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.createdimensions.CreateDimensionsMod;

public class CreateDimensionsModBlocks {
	public static void load() {
	}

	public static void clientLoad() {
	}

	private static Block register(String registryName, Block block) {
		return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(CreateDimensionsMod.MODID, registryName), block);
	}
}
