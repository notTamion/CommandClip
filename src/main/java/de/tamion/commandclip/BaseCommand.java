package de.tamion.commandclip;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BaseCommand extends GenericCommand {

    private final InternalCommand internalCommand;
    private final JavaPlugin plugin;

    public BaseCommand(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.internalCommand = new InternalCommand(name, this);
    }

    public BaseCommand commandDescription(String description) {
        internalCommand.setDescription(description);
        return this;
    }

    public BaseCommand alias(String alias) {
        List<String> aliases = internalCommand.getAliases();
        aliases.add(alias);
        internalCommand.setAliases(aliases);
        return this;
    }

    public BaseCommand permission(String permission) {
        return (BaseCommand) super.permission(permission);
    }

    @Override
    public BaseCommand permission(String permission, String permissionMessage) {
        return (BaseCommand) super.permission(permission, permissionMessage);
    }

    public BaseCommand commandPermission(String permission) {
        internalCommand.setPermission(permission);
        return this;
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
    public BaseCommand subCommand(String name, SubCommandBuilder command) {
        return (BaseCommand) super.subCommand(name, command);
    }

    public boolean register() {
        return Bukkit.getServer().getCommandMap().register(this.plugin.getName() + ":" + internalCommand.getName(), internalCommand);
    }


}
