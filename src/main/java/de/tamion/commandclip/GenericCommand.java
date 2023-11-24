package de.tamion.commandclip;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public abstract class GenericCommand {

    HashMap<String, SubCommand> subCommands = new HashMap<>();
    ExecutionLogic executionLogic;
    TabCompletionLogic tabCompletionLogic;
    String permission;
    String permissionMessage = "You aren't allowed to execute this Command";

    public GenericCommand permission(String permission) {
        this.permission = permission;
        return this;
    }

    public GenericCommand permission(String permission, String permissionMessage) {
        this.permission = permission;
        this.permissionMessage = permissionMessage;
        return this;
    }

    public GenericCommand execute(ExecutionLogic logic) {
        this.executionLogic = logic;
        return this;
    }

    public GenericCommand tabComplete(TabCompletionLogic logic) {
        this.tabCompletionLogic = logic;
        return this;
    }

    public GenericCommand subCommand(String name, SubCommandBuilder command) {
        SubCommand subCommand = command.build(new SubCommand(name).permission(this.permission));
        this.subCommands.put(subCommand.name, subCommand);
        return this;
    }

    public interface ExecutionLogic {
        void execute(CommandSender sender, String[] args);
    }

    public interface TabCompletionLogic {
        @NotNull List<String> tabComplete(CommandSender sender, String[] args);
    }

    public interface SubCommandBuilder {
        @NotNull SubCommand build(SubCommand subCommand);
    }
}
