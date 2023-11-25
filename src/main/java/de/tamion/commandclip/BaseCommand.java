package de.tamion.commandclip;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BaseCommand extends GenericCommand {

    private final InternalCommand internalCommand;
    private final JavaPlugin plugin;

    /**
     * Creates a BaseCommand to which subcommands etc. can be added.
     *
     * @param plugin The Plugin that registers the Command
     * @param name the Command name
     */
    public BaseCommand(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.internalCommand = new InternalCommand(name, this);
    }

    @Override
    public BaseCommand usage(String usage) {
        return (BaseCommand) super.usage(usage);
    }

    /**
     * Sets the Usage that gets displayed when running /help etc.
     * Needs to be called after adding subcommands since that will
     * overwrite the usage
     *
     * @param usage the usage
     * @return the current command to make further changes
     */
    public BaseCommand commandUsage(String usage) {
        this.internalCommand.setUsage(usage);
        return this;
    }

    /**
     * Sets the Description that gets displayed when running /help etc.
     *
     * @param description the description
     * @return the current command to make further changes
     */
    public BaseCommand commandDescription(String description) {
        internalCommand.setDescription(description);
        return this;
    }

    /**
     * Adds the alias to all current command Aliases
     *
     * @param alias the alias added
     * @return the current command to make further changes
     */
    public BaseCommand alias(String alias) {
        List<String> aliases = internalCommand.getAliases();
        aliases.add(alias);
        internalCommand.setAliases(aliases);
        return this;
    }

    /**
     * Sets the Permission required to execute this command and any subcommand
     *
     * @param permission the permission required
     * @return the current command to make further changes
     */
    public BaseCommand commandPermission(String permission) {
        internalCommand.setPermission(permission);
        return this;
    }

    public BaseCommand permission(String permission) {
        return (BaseCommand) super.permission(permission);
    }

    @Override
    public BaseCommand permission(String permission, String permissionMessage) {
        return (BaseCommand) super.permission(permission, permissionMessage);
    }

    @Override
    public BaseCommand testArgs(ArgTester tester) {
        return (BaseCommand) super.testArgs(tester);
    }

    @Override
    public BaseCommand subCommand(String name, SubCommandBuilder command) {
        super.subCommand(name, command);
        this.internalCommand.setUsage("/" + this.internalCommand.getName() + " " + this.subCommands.keySet());
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

    /**
     * Registers the command to the Servers CommandMap
     *
     * @return if the command was successfully registered
     */
    public boolean register() {
        return Bukkit.getServer().getCommandMap().register(this.plugin.getName() + ":" + internalCommand.getName(), internalCommand);
    }


}
