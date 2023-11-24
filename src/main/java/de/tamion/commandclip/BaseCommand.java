package de.tamion.commandclip;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseCommand extends GenericCommand {

    private final InternalCommand internalCommand;
    private final JavaPlugin plugin;

    public BaseCommand(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.internalCommand = new InternalCommand(name, this);
    }

    @Override
    public BaseCommand execute(ExecutionLogic logic) {
        return (BaseCommand) super.execute(logic);
    }

    @Override
    public BaseCommand tabComplete(TabCompletionLogic logic) {
        return (BaseCommand) super.tabComplete(logic);
    }

    @Override
    public BaseCommand subCommand(SubCommand command) {
        return (BaseCommand) super.subCommand(command);
    }

    @Override
    public BaseCommand subCommand(SubCommandBuilder command) {
        return (BaseCommand) super.subCommand(command);
    }

    public boolean register() {
        return Bukkit.getServer().getCommandMap().register(this.plugin.getName() + ":" + internalCommand.getName(), internalCommand);
    }


}
