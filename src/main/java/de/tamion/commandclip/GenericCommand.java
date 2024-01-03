package de.tamion.commandclip;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

abstract class GenericCommand {

    String permission;
    String permissionMessage = "You aren't allowed to execute this Command";
    ArgTester argTester;
    HashMap<String, SubCommand> subCommands = new HashMap<>();
    ExecutionLogic executionLogic;
    TabCompletionLogic tabCompletionLogic;

    /**
     * Sets the permission required to execute this command.
     *
     * @param permission the permission required
     * @return the current command to make further changes
     */
    @NotNull
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
    @NotNull
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
    @NotNull
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
    @NotNull
    public GenericCommand executes(ExecutionLogic logic) {
        this.executionLogic = logic;
        return this;
    }

    /**
     * Executes the ExecutionLogic if set
     *
     * @param sender the CommandSender
     * @param args truncated Args to not include subcommands
     * @return the current command to make further changes
     */
    @NotNull
    public GenericCommand execute(CommandSender sender, String alias, String[] args) {
        this.executionLogic.execute(sender, alias, args);
        return this;
    }

    /**
     * Sets the Logic executed when a user is trying to tab complete.
     *
     * @param logic the logic executed
     * @return the current command to make further changes
     */
    @NotNull
    public GenericCommand tabCompletes(TabCompletionLogic logic) {
        this.tabCompletionLogic = logic;
        return this;
    }

    /**
     * Executes the TabCompletionLogic if set
     *
     * @param sender the CommandSender
     * @param args truncated Args to not include subcommands
     * @return the current command to make further changes
     */
    @NotNull
    public GenericCommand tabComplete(CommandSender sender, String alias, String[] args) {
        this.tabCompletionLogic.tabComplete(sender, alias, args);
        return this;
    }

    /**
     * Adds the provided SubCommand to this command
     *
     * @param subCommand the subcommand
     * @return the current command to make further changes
     */
    @NotNull
    public GenericCommand subCommand(SubCommand subCommand) {
        this.subCommands.put(subCommand.name, subCommand);
        return this;
    }

    public interface ExecutionLogic {

        /**
         * The Logic that gets run when a user executes the command
         *
         * @param sender the CommandSender
         * @param alias the alias used to call the command
         * @param args truncated Args to not include subcommands
         */
        void execute(CommandSender sender, String alias, String[] args);
    }

    /**
     * See {@link #tabComplete(CommandSender, String, String[])}
     */
    public interface TabCompletionLogic {

        /**
         * The Logic that gets run when a user tries to tab complete
         *
         * @param sender the CommandSender
         * @param alias the alias used to call the command
         * @param args truncated Args to not include subcommands
         * @return the completions displayed to the user
         */
        @NotNull List<String> tabComplete(CommandSender sender, String alias, String[] args);
    }

    /**
     * See {@link #test(String, String[])}
     */
    public interface ArgTester {
        /**
         * The Logic that gets run when a user tries to execute a command
         *
         * @param alias the alias used to call the command
         * @param args truncated Args to not include subcommands
         * @return null if Args passed test or usage String to be sent to the user
         */
        String test(String alias, String[] args);
    }
}
