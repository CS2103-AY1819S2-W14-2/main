package seedu.address.logic.commands.epiggy;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all the commands with their brief description and syntax.
 * TODO: to complete full commands list
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_ALIAS = "hp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n";
    public static final String SHOWING_HELP_MESSAGE = "Commands List : \n"
            + "Add : add n/EXPENSE_NAME $/COST c/CATEGORY [d/DATE] [t/TAG]… \n"
            + "Delete : `delete INDEX \n"
            + "";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false);
    }

}
