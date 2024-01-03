package de.tamion.commandclip;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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
    public BaseCommand(String name, JavaPlugin plugin) {
        this.internalCommand = new InternalCommand(name, this);
        this.plugin = plugin;
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

    public @NotNull BaseCommand permission(String permission) {
        return (BaseCommand) super.permission(permission);
    }

    @Override
    public @NotNull BaseCommand permission(String permission, String permissionMessage) {
        return (BaseCommand) super.permission(permission, permissionMessage);
    }

    @Override
    public @NotNull BaseCommand testArgs(ArgTester tester) {
        return (BaseCommand) super.testArgs(tester);
    }

    @Override
    public @NotNull BaseCommand subCommand(SubCommand subCommand) {
        super.subCommand(subCommand);
        this.internalCommand.setUsage("/" + this.internalCommand.getName() + " " + this.subCommands.keySet());
        return this;
    }

    @Override
    public @NotNull BaseCommand executes(ExecutionLogic logic) {
        return (BaseCommand) super.executes(logic);
    }

    @Override
    public @NotNull BaseCommand execute(CommandSender sender, String alias, String[] args) {
        return (BaseCommand) super.execute(sender, alias, args);
    }

    @Override
    public @NotNull BaseCommand tabCompletes(TabCompletionLogic logic) {
        return (BaseCommand) super.tabCompletes(logic);
    }

    @Override
    public @NotNull BaseCommand tabComplete(CommandSender sender, String alias, String[] args) {
        return (BaseCommand) super.tabComplete(sender, alias, args);
    }

    /**
     * Gets the bukkit command for you to make further changes
     *
     * @return the internal bukkit command object
     */
    public Command internalCommand() {
        return this.internalCommand;
    }

    /**
     * Registers the command to the Servers CommandMap
     *
     * @return if the command was successfully registered
     */
    public boolean register() {
        return Bukkit.getServer().getCommandMap().register(this.plugin.getName(), internalCommand);
    }
}