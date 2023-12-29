package de.tamion.commandclip;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

abstract class GenericCommand {

    String usage;
    String permission;
    String permissionMessage = "You aren't allowed to execute this Command";
    ArgTester argTester;
    HashMap<String, SubCommand> subCommands = new HashMap<>();
    ExecutionLogic executionLogic;
    TabCompletionLogic tabCompletionLogic;

    /**
     * Sets your own usage for the command. This will be sent to the user
     * if the ArgTester returns false or there is no ExecutionLogic set
     * for this Command.
     *
     * @param usage the usage for the command
     * @return the current command to make further changes
     */
    public GenericCommand usage(String usage) {
        this.usage = usage;
        return this;
    }

    /**
     * Sets the permission required to execute this command.
     *
     * @param permission the permission required
     * @return the current command to make further changes
     */
    public GenericCommand permission(String permission) {
        this.permission = permission;
        return this;
    }

    /**
     * Sets the permission required to execute this command and
     * the message sent to the player if they lack that permission.
     *
     * @param permission the permission required
     * @param permissionMessage the message sent
     * @return the current command to make further changes
     */
    public GenericCommand permission(String permission, String permissionMessage) {
        this.permission = permission;
        this.permissionMessage = permissionMessage;
        return this;
    }

    /**
     * Sets the ArgTester that gets run before the command executes.
     *
     * @param tester the tester used
     * @return the current command to make further changes
     */
    public GenericCommand testArgs(ArgTester tester) {
        this.argTester = tester;
        return this;
    }

    /**
     * Sets the Logic executed when a user is running the command.
     *
     * @param logic the logic executed
     * @return the current command to make further changes
     */
    public GenericCommand execute(ExecutionLogic logic) {
        this.executionLogic = logic;
        return this;
    }


    /**
     * Sets the Logic executed when a user is trying to tab complete.
     *
     * @param logic the logic executed
     * @return the current command to make further changes
     */
    public GenericCommand tabComplete(TabCompletionLogic logic) {
        this.tabCompletionLogic = logic;
        return this;
    }

    /**
     * Adds the provided SubCommand to this command
     *
     * @param name name of the subcommand
     * @param command the subcommand
     * @return the current command to make further changes
     */
    public GenericCommand subCommand(String name, SubCommandBuilder command) {
        SubCommand subCommand = command.build(new SubCommand(name).permission(this.permission));
        this.subCommands.put(subCommand.name, subCommand);
        return this;
    }

    public interface ExecutionLogic {

        /**
         * The Logic that gets run when a user executes the command
         *
         * @param sender the CommandSender
         * @param args truncated Args to not include subcommands
         */
        void execute(CommandSender sender, String[] args);
    }

    /**
     * See {@link #tabComplete(TabCompletionLogic)}
     */
    public interface TabCompletionLogic {

        /**
         * The Logic that gets run when a user tries to tab complete
         *
         * @param sender the CommandSender
         * @param args truncated Args to not include subcommands
         * @return the completions displayed to the user
         */
        @NotNull List<String> tabComplete(CommandSender sender, String[] args);
    }

    /**
     * See {@link #build(SubCommand)}
     */
    public interface SubCommandBuilder {

        /**
         *
         * @param subCommand a pre created subcommand with name set and permission inherited from parent command
         * @return the modified SubCommand
         */
        @NotNull SubCommand build(SubCommand subCommand);
    }

    /**
     * See {@link #test(String[])}
     */
    public interface ArgTester {
        /**
         *
         * @param args truncated Args to not include subcommands
         * @return if the arguments are correct and the command may be executed
         */
        boolean test(String[] args);
    }
}
