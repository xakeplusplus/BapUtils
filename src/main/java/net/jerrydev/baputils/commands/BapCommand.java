package net.jerrydev.baputils.commands;

import kotlin.jvm.JvmOverloads;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.commands.bap.*;
import net.jerrydev.baputils.guis.BapGui;
import net.jerrydev.baputils.utils.ChatColors.CCodes;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;
import java.util.List;

import static net.jerrydev.baputils.utils.ChatColors.ccolorize;

public class BapCommand extends CommandBase {
    public static final List<String> commandAliases = Arrays.asList("bp", "baputils", "baputilities", "uwa", "pig", "tom", "fishing");

    @Override
    public String getCommandName() {
        return "bap";
    }

    @Override
    public List<String> getCommandAliases() {
        return commandAliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            BapUtils.queueClientMessage(
                    ccolorize(CCodes.DARK_RED, "Th")
                            + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "e")
                            + ccolorize(CCodes.DARK_RED, " GUI ")
                            + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "i")
                            + ccolorize(CCodes.DARK_RED, "s co")
                            + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "m")
                            + ccolorize(CCodes.DARK_RED, "in")
                            + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "g")
                            + ccolorize(CCodes.DARK_RED, " so")
                            + ccolorize(Arrays.asList(CCodes.DARK_RED, CCodes.OBFUSCATED), "o")
                            + ccolorize(CCodes.DARK_RED, "n...")
            );

            // Display main GUI
            BapUtils.setActiveGui(new BapGui());

            return;
        }

        final String subcommand = args[0].toLowerCase();

        if ((BapAliases.commandName + BapAliases.commandAliases).contains(subcommand)) {
            // bap aliases
            checkArgsLen(args.length - 1, BapAliases.requiredParams, BapAliases.commandUsage);

            BapAliases.execute();
        } else if ((BapColors.commandName + BapColors.commandAliases).contains(subcommand)) {
            // bap colors
            checkArgsLen(args.length - 1, BapColors.requiredParams, BapColors.commandUsage);

            BapColors.execute();
        } else if ((BapDev.commandName + BapDev.commandAliases).contains(subcommand)) {
            // bap dev
            checkArgsLen(args.length - 1, BapDev.requiredParams, BapDev.commandUsage);

            BapDev.execute();
        } else if ((BapDungeonJoin.commandName + BapDungeonJoin.commandAliases).contains(subcommand)) {
            // bap dungeonjoin <String> <String>
            checkArgsLen(args.length - 1, BapDungeonJoin.requiredParams, BapDungeonJoin.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("You must specify a dungeon floor (^e0$|^[fm][0-7]$).", "DungeonJoin");
                return;
            } else if (args.length == 2) {
                BapUtils.throwCommandException("You must specify a player.", "DungeonJoin");
                return;
            }

            BapDungeonJoin.execute(args[1], args[2]);
        } else if ((BapHello.commandName + BapHello.commandAliases).contains(subcommand)) {
            // bap hello
            checkArgsLen(args.length - 1, BapHello.requiredParams, BapHello.commandUsage);

            BapHello.execute();
        } else if ((BapHelp.commandName + BapHelp.commandAliases).contains(subcommand)) {
            // bap help
            checkArgsLen(args.length - 1, BapHelp.requiredParams, BapHelp.commandUsage);

            BapHelp.execute();
        } else if ((BapTakeover.commandName + BapTakeover.commandAliases).contains(subcommand)) {
            // bap takeover <String>
            checkArgsLen(args.length - 1, BapTakeover.requiredParams, BapTakeover.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.", "Takeover");
                return;
            }

            BapTakeover.execute(args[1]);
        } else if ((BapTrust.commandName + BapTrust.commandAliases).contains(subcommand)) {
            // bap trust <String>
            checkArgsLen(args.length - 1, BapTrust.requiredParams, BapTrust.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("Your must specify a player.", "Trust");
                return;
            }

            BapTrust.execute(args[1]);
        } else if ((BapUuid.commandName + BapUuid.commandAliases).contains(subcommand)) {
            // bap uuid <String>
            checkArgsLen(args.length - 1, BapUuid.requiredParams, BapUuid.commandUsage);

            if (args.length == 1) {
                BapUtils.throwCommandException("You must specify a player.", "Uuid");
                return;
            }

            BapUuid.execute(args[1]);
        }
    }


    public static void checkArgsLen(int input, int expected) {
        if (input > expected) {
            BapUtils.queueClientMessage(ccolorize(Arrays.asList(CCodes.DARK_GRAY, CCodes.ITALIC),
                    "Warning: expected " + expected + " arguments, but got " + input + "."));
        }
        if (input < expected) {
            BapUtils.queueClientMessage(ccolorize(Arrays.asList(CCodes.GRAY, CCodes.ITALIC),
                    "Expected " + expected + " arguments, but got " + input + "."));
        }
    }

    public static void checkArgsLen(int input, int expected, String usage) {
        if ((input == 0) && !(expected <= 0)) {
            BapUtils.queueClientMessage("Command usage: " + usage);
            return;
        }

        // Method overloading
        checkArgsLen(input, expected);
    }
}
