package de.tamion.commandclip;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class InternalCommand extends Command {

    private final BaseCommand baseCommand;

    protected InternalCommand(@NotNull String name, BaseCommand baseCommand) {
        super(name);
        this.baseCommand = baseCommand;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        GenericCommand command;
        List<String> commandStack = new ArrayList<>();
        for(command = this.baseCommand; args.length > 0 && command.subCommands.containsKey(args[0]); args = removeFirst(args)) {
            command = command.subCommands.get(args[0]);
            commandStack.add(args[0]);
        }

        // If the command doesn't have Execution Logic then send back usage
        if (command.executionLogic == null) {
            sender.sendMessage("/" + commandLabel + " " + String.join(" ", commandStack) + (commandStack.isEmpty() ? "" : " ") + (command.usage == null ? command.subCommands.keySet() : command.usage));
            return false;
        }
        if(command.permission != null && !sender.hasPermission(command.permission)) {
            sender.sendMessage(command.permissionMessage);
            return false;
        }
        if(command.argTester != null && !command.argTester.test(args)) {
            if (command.usage == null) {
                sender.sendMessage("Invalid Arguments");
            } else {
                sender.sendMessage("/" + commandLabel + " " + (commandStack.isEmpty() ? "" : String.join(" ", commandStack) + " ") + (!command.subCommands.isEmpty() ? command.subCommands.keySet() + " / " : "") + command.usage);
            }
            return false;
        }
        // Execute Execution Logic
        command.executionLogic.execute(sender, args);
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        GenericCommand command;
        for(command = this.baseCommand; args.length > 0 && command.subCommands.containsKey(args[0]); args = removeFirst(args)) {
            command = command.subCommands.get(args[0]);
        }

        List<String> completions = new ArrayList<>();
        if (command.tabCompletionLogic != null) {
            completions.addAll(command.tabCompletionLogic.tabComplete(sender, args));
        }
        completions.addAll(command.subCommands.entrySet().stream().filter(subCommandEntry -> subCommandEntry.getValue().permission == null || sender.hasPermission(subCommandEntry.getValue().permission)).map(Map.Entry::getKey).toList());
        return completions;
    }

    private String[] removeFirst(String[] array) {
        if(array.length < 2)
            return new String[0];
        return Arrays.copyOfRange(array, 1, array.length);
    }
}
