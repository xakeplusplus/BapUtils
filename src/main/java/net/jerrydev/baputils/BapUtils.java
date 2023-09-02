package net.jerrydev.baputils;

import net.jerrydev.baputils.commands.BapHandler;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.events.ChatListener;
import net.jerrydev.baputils.events.ClientPeriodic;
import net.jerrydev.baputils.utils.ChatUtils;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandException;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.Arrays;

import static net.jerrydev.baputils.Constants.*;

@Mod(modid = Constants.kModId, version = Constants.kModVersion, clientSideOnly = true)
public class BapUtils {
    // dev
    public static final boolean isLocalDev = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(kClientPrefix + " HELLO from " + kModName + "! You are on Minecraft Forge version 1.8.9.");

        // Register slash (/) commands
        ClientCommandHandler.instance.registerCommand(new BapHandler());

        // Register events
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new ClientPeriodic());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        System.out.println(kClientPrefix + " BapUtils has been initialized!");
    }

    // utils
    public static void setActiveGui(GuiScreen gui) {
        ClientPeriodic.activeGui = gui;
    }

    public static void clientVerbose(String message) {
        if(BapSettingsGui.INSTANCE.getClientChatVerbose()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                ChatUtils.ccolorize(CCodes.GRAY, kClientPrefix) + " " + ChatUtils.ccolorize(Arrays.asList(CCodes.DARK_GRAY, CCodes.ITALIC), message)
            ));
        }
    }


    public static void queueServerMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

    public static void queueServerMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(addPrefix ? (kServerPrefix + " > " + message) : message);
    }

    public static void queuePartyChat(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage((isLocalDev ? "." : "") + "/party chat " + message);
    }

    public static void queuePartyChat(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage((isLocalDev ? "." : "") + "/party chat " + (addPrefix ? "bap> " : "") + message);
    }

    public static void queueCommand(String command) {
        Debug.dout("Executing: /" + command);
        Minecraft.getMinecraft().thePlayer.sendChatMessage((isLocalDev ? "." : "") + "/" + command);
    }

    public static void queueClientMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ChatUtils.ccolorize(CCodes.AQUA, kClientPrefix) + " " + message
        ));
    }

    public static void queueClientMessage(String message, boolean addPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            addPrefix ? (ChatUtils.ccolorize(CCodes.AQUA, kClientPrefix) + " " + message) : message
        ));
    }

    public static void queueClientMessage(String message, CCodes prefixColor) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ChatUtils.ccolorize(prefixColor, kClientPrefix) + " " + message
        ));
    }

    public static void queueClientMessage(String message, String customPrefix) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            customPrefix + " " + message
        ));
    }

    public static void queueErrorMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ChatUtils.ccolorize(CCodes.RED, kClientPrefix + " Error: " + message)
        ));
    }

    public static void queueWarnMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
            ChatUtils.ccolorize(CCodes.GOLD, kClientPrefix + " Warning: " + message)
        ));
    }

    public static void throwCommandException(String message) throws CommandException {
        throw new CommandException(ChatUtils.ccolorize(CCodes.RED, kClientPrefix) + ChatUtils.ccolorize(CCodes.RED, " Error: " + message));
    }
}
