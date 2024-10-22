
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.createdimensions.init;

import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.server.MinecraftServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;

import net.mcreator.createdimensions.CreateDimensionsMod;

public class CreateDimensionsModBiomes {
	public static ResourceKey<Biome> LAND_OF_THE_MIM = ResourceKey.create(Registries.BIOME, new ResourceLocation(CreateDimensionsMod.MODID, "land_of_the_mim"));

	public static void loadEndBiomes() {
	}

	public static void load(MinecraftServer server) {
		Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
		Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
		Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
		for (LevelStem levelStem : levelStemTypeRegistry.stream().toList()) {
			DimensionType dimensionType = levelStem.type().value();

		}
	}
}
