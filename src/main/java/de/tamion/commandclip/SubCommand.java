package de.tamion.commandclip;

public class SubCommand extends GenericCommand {
    protected String name;
    public SubCommand(String name) {
        this.name = name;
    }

    @Override
    public SubCommand execute(ExecutionLogic logic) {
        return (SubCommand) super.execute(logic);
    }

    @Override
    public SubCommand tabComplete(TabCompletionLogic logic) {
        return (SubCommand) super.tabComplete(logic);
    }

    @Override
    public SubCommand subCommand(SubCommand command) {
        return (SubCommand) super.subCommand(command);
    }

    @Override
    public SubCommand subCommand(SubCommandBuilder command) {
        return (SubCommand) super.subCommand(command);
    }
}
