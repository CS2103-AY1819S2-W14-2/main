package seedu.address.logic.commands.epiggy;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERIOD;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.epiggy.Budget;

//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

/**
 * Sets a budget for the total expenses.
 */
public class SetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "setBudget";
    public static final String COMMAND_ALIAS = "sb";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a budget for ePiggy. "
            + "Parameters: "
            + PREFIX_COST + "BUDGET IN DOLLARS"
            + PREFIX_PERIOD + "TIME PERIOD IN WEEKS"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COST + "500.00 "
            + PREFIX_PERIOD + "4";

    public static final String MESSAGE_SUCCESS = "Budget set for: %1$s";
    //    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    // public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the address book";

    private final Budget toSet;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetBudgetCommand(Budget budget) {
        requireNonNull(budget);
        toSet = budget;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        model.setBudget(toSet);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toSet));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetBudgetCommand // instanceof handles nulls
                && toSet.equals(((SetBudgetCommand) other).toSet));
    }
}
