/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.mcreator.createdimensions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.mcreator.createdimensions.init.CreateDimensionsModSounds;
import net.mcreator.createdimensions.init.CreateDimensionsModProcedures;
import net.mcreator.createdimensions.init.CreateDimensionsModItems;
import net.mcreator.createdimensions.init.CreateDimensionsModBlocks;
import net.mcreator.createdimensions.init.CreateDimensionsModBiomes;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.api.ModInitializer;

public class CreateDimensionsMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "create_dimensions";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing CreateDimensionsMod");

		CreateDimensionsModBlocks.load();
		CreateDimensionsModItems.load();

		CreateDimensionsModProcedures.load();

		CreateDimensionsModSounds.load();

		CreateDimensionsModBiomes.loadEndBiomes();
		ServerLifecycleEvents.SERVER_STARTING.register((server) -> {
			CreateDimensionsModBiomes.load(server);
		});
	}
}
