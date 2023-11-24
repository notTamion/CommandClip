package de.tamion.commandclip;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public abstract class GenericCommand {

    HashMap<String, SubCommand> subCommands = new HashMap<>();
    ExecutionLogic executionLogic;
    TabCompletionLogic tabCompletionLogic;

    public GenericCommand execute(ExecutionLogic logic) {
        this.executionLogic = logic;
        return this;
    }

    public GenericCommand tabComplete(TabCompletionLogic logic) {
        this.tabCompletionLogic = logic;
        return this;
    }

    public GenericCommand subCommand(SubCommand command) {
        this.subCommands.put(command.name, command);
        return this;
    }

    public GenericCommand subCommand(SubCommandBuilder command) {
        SubCommand subCommand = command.build();
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
        @NotNull SubCommand build();
    }
}
