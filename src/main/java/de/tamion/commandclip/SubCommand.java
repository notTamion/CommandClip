package de.tamion.commandclip;

public class SubCommand extends GenericCommand {
    protected String name;
    public SubCommand(String name) {
        this.name = name;
    }

    @Override
    public SubCommand usage(String usage) {
        return (SubCommand) super.usage(usage);
    }

    @Override
    public SubCommand permission(String permission) {
        return (SubCommand) super.permission(permission);
    }

    @Override
    public SubCommand permission(String permission, String permissionMessage) {
        return (SubCommand) super.permission(permission, permissionMessage);
    }

    @Override
    public SubCommand testArgs(ArgTester tester) {
        return (SubCommand) super.testArgs(tester);
    }

    @Override
    public SubCommand subCommand(String name, SubCommandBuilder command) {
        return (SubCommand) super.subCommand(name, command);
    }

    @Override
    public SubCommand execute(ExecutionLogic logic) {
        return (SubCommand) super.execute(logic);
    }

    @Override
    public SubCommand tabComplete(TabCompletionLogic logic) {
        return (SubCommand) super.tabComplete(logic);
    }

}
