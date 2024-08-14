package ink.fsp.carpetfspaddition;

import ink.fsp.carpetfspaddition.commands.ShowCommand;
import ink.fsp.carpetfspaddition.dispenserBehavior.rule.AnvilDispenserBehavior;
import ink.fsp.carpetfspaddition.event.rule.itemFrameAdvancedInteract.PlayerUseEntityCallback;
import ink.fsp.carpetfspaddition.event.rule.itemFrameAdvancedInteract.PlayerUseEntityHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarpetFspAddition implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "carpet-fsp-addition";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

//		LOGGER.info("Hello Fabric world!");
		CarpetFspAdditionServer.init();
		PlayerUseEntityCallback.EVENT.register(new PlayerUseEntityHandler());
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> ShowCommand.register(dispatcher));
		DispenserBlock.registerBehavior(Items.ANVIL, new AnvilDispenserBehavior());
	}
}