package de.tamion.commandclip;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SubCommand extends GenericCommand {

    protected String name;
    public SubCommand(String name) {
        this.name = name;
    }

    @Override
    public @NotNull SubCommand permission(String permission) {
        return (SubCommand) super.permission(permission);
    }

    @Override
    public @NotNull SubCommand permission(String permission, String permissionMessage) {
        return (SubCommand) super.permission(permission, permissionMessage);
    }

    @Override
    public @NotNull SubCommand testArgs(ArgTester tester) {
        return (SubCommand) super.testArgs(tester);
    }

    @Override
    public @NotNull SubCommand subCommand(SubCommand subCommand) {
        return (SubCommand) super.subCommand(subCommand);
    }

    @Override
    public @NotNull SubCommand executes(ExecutionLogic logic) {
        return (SubCommand) super.executes(logic);
    }

    @Override
    public @NotNull SubCommand execute(CommandSender sender, String alias, String[] args) {
        return (SubCommand) super.execute(sender, alias, args);
    }

    @Override
    public @NotNull SubCommand tabCompletes(TabCompletionLogic logic) {
        return (SubCommand) super.tabCompletes(logic);
    }

    @Override
    public @NotNull SubCommand tabComplete(CommandSender sender, String alias, String[] args) {
        return (SubCommand) super.tabComplete(sender, alias, args);
    }
}
