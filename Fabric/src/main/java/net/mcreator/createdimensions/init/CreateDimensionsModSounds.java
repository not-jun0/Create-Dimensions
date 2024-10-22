
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.createdimensions.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

public class CreateDimensionsModSounds {
	public static SoundEvent WINDSOUND = SoundEvent.createVariableRangeEvent(new ResourceLocation("create_dimensions", "windsound"));
	public static SoundEvent MIMRANDOM = SoundEvent.createVariableRangeEvent(new ResourceLocation("create_dimensions", "mimrandom"));

	public static void load() {
		Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("create_dimensions", "windsound"), WINDSOUND);
		Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("create_dimensions", "mimrandom"), MIMRANDOM);
	}
}
