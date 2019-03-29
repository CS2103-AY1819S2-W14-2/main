package seedu.address.logic.commands.epiggy;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


//@@author rahulb99
/**
 * Finds and lists all expenses in EPiggy whose expense contains any of the argument keywordss.
 * keywords matching is case insensitive.
 */
public class SortExpenseCommand extends Command {

    public static final String COMMAND_WORD = "sortExpense";
    public static final String COMMAND_ALIAS = "sE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " : Sorts the expenses as specified by the user. "
            + " The keywordss do not need to be in order.\n"
            + " Parameters: -[n/d/$]...\n"
            + " Example: " + COMMAND_WORD + " n/n";
    public static final String MESSAGE_SUCCESS = "Sorting Expenses...\n";

    private final String keyword;

    public SortExpenseCommand(String keyword) {
        assert keyword != null : "keywords should not be null.";
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.sortExpenses(keyword);
        model.getExpenseList();
        model.commitAddressBook();

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredExpenseList().size()));
    }
}
