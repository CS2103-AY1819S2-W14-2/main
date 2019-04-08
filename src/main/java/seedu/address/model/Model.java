package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.expense.Cost;
import seedu.address.model.expense.Expense;
import seedu.address.model.epiggy.Allowance;
import seedu.address.model.epiggy.Budget;
import seedu.address.model.epiggy.Goal;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Budget> PREDICATE_SHOW_ALL_BUDGETS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getEPiggyFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setEPiggyFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code ePiggy}.
     */
    void setEPiggy(ReadOnlyEPiggy ePiggy);

    /** Returns the EPiggy */
    ReadOnlyEPiggy getEPiggy();

    /**
     * Adds the given expense.
     * {@code } must not already exist in the address book.
     */
    void addExpense(Expense expense);

    /**
     * Adds the given allowance.
     */
    void addAllowance(Allowance allowance);

    /**
     * Adds a new budget.
     */
    void addBudget(int index, Budget budget);

    /**
     * Checks if there are any overlapping budgets.
     */
    boolean budgetsOverlap(Date startDate, Date endDate, Budget earlierBudget);

    /**
     * Deletes the budget at the specific index.
     */
    void deleteBudgetAtIndex(int index);

    /**
     * Deletes the expense {@code toDelete}.
     * @param toDelete the expense to be deleted.
     */
    void deleteExpense(Expense toDelete);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the address book.
     * The expense identity of {@code editedExpense} must not be the same as
     * another existing expense in the address book.
     */
    void setExpense(Expense target, Expense editedExpense);

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpensesList(Predicate<Expense> predicate);

    /**
     * Gets the current budget list.
     */
    ObservableList<Budget> getBudgetList();

    /**
     * Gets the expense list.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Gets the current budget's index.
     * @return -1 if there is no current budget.
     */
    int getCurrentBudgetIndex();


    /**
     * Get the current savings.
     */
    SimpleObjectProperty<Cost> getSavings();

    /**
     * Get the savings goal.
     */
    SimpleObjectProperty<Goal> getGoal();

    /**
     * Sets the savings goal.
     */
    void setGoal(Goal goal);

    /**
     * Replaces the current budget with {@code editedBudget}.
     */
    void setCurrentBudget(Budget editedBudget);

    /** Returns an unmodifiable view of the filtered expense list */
    ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the filtered budget list */
    ObservableList<Budget> getFilteredBudgetList();

    /**
     * Updates the filter of the filtered budget list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBudgetList(Predicate<Budget> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoEPiggy();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoEPiggy();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoEPiggy();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoEPiggy();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitEPiggy();

    /**
     * Selected expense in the filtered expense list.
     * null if no expense is selected.
     */
    ReadOnlyProperty<Expense> selectedExpenseProperty();

    /**
     * Returns the selected expense in the filtered expense list.
     * null if no expense is selected.
     */
    Expense getSelectedExpense();

    /**
     * Sets the selected expense in the filtered expense list.
     */
    void setSelectedExpense(Expense expense);

    /**
     * Sorts the expenses according to the specified {@param expenseComparator}.
     */
    void sortExpenses(Comparator<Expense> expenseComparator);

    /**
     * Reveres the {@code filteredExpenses} list.
     */
    void reverseFilteredExpensesList();
}
