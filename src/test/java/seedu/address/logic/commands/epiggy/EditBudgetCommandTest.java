package seedu.address.logic.commands.epiggy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_ONE;
import static seedu.address.logic.commands.epiggy.EditBudgetCommand.createEditedBudget;
import static seedu.address.testutil.TypicalBudgets.getTypicalEPiggy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.epiggy.EditBudgetCommand.EditBudgetDetails;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyEPiggy;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.epiggy.Allowance;
import seedu.address.model.epiggy.Budget;
import seedu.address.model.epiggy.Expense;
import seedu.address.model.epiggy.Goal;
import seedu.address.model.epiggy.Savings;
import seedu.address.model.epiggy.item.Cost;
import seedu.address.model.epiggy.item.Period;
import seedu.address.model.person.Person;
import seedu.address.testutil.epiggy.BudgetBuilder;
import seedu.address.testutil.epiggy.EditBudgetDetailsBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditBudgetCommand.
 */
@Ignore
public class EditBudgetCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalEPiggy(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();
    private Date todaysDate = new Date();

    @Test
    public void execute_allFieldsSpecified_success() throws Exception {
        ModelStubWithOneCurrentBudget modelStub = new ModelStubWithOneCurrentBudget();

        EditBudgetDetails details = DESC_ONE;
        Budget budgetToEdit = new BudgetBuilder().build();
        Budget editedBudget = createEditedBudget(budgetToEdit, details);
        CommandResult commandResult = new EditBudgetCommand(details).execute(modelStub, commandHistory);

        assertEquals(String.format(EditBudgetCommand.MESSAGE_EDIT_BUDGET_SUCCESS, editedBudget),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(editedBudget), modelStub.budgets);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_amountSpecified_success() throws Exception {
        ModelStubWithOneCurrentBudget modelStub = new ModelStubWithOneCurrentBudget();

        Budget budgetToEdit = new BudgetBuilder().build();
        EditBudgetDetails details = new EditBudgetDetails();
        details.setPeriod(budgetToEdit.getPeriod());
        details.setStartDate(budgetToEdit.getStartDate());
        details.setAmount(new Cost(200));

        Budget editedBudget = createEditedBudget(budgetToEdit, details);
        CommandResult commandResult = new EditBudgetCommand(details).execute(modelStub, commandHistory);

        assertEquals(String.format(EditBudgetCommand.MESSAGE_EDIT_BUDGET_SUCCESS, editedBudget),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(editedBudget), modelStub.budgets);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_periodSpecified_success() throws Exception {
        ModelStubWithOneCurrentBudget modelStub = new ModelStubWithOneCurrentBudget();

        Budget budgetToEdit = new BudgetBuilder().build();
        EditBudgetDetails details = new EditBudgetDetails();
        details.setStartDate(budgetToEdit.getStartDate());
        details.setAmount(budgetToEdit.getCost());
        details.setPeriod(new Period(26));

        Budget editedBudget = createEditedBudget(budgetToEdit, details);
        CommandResult commandResult = new EditBudgetCommand(details).execute(modelStub, commandHistory);

        assertEquals(String.format(EditBudgetCommand.MESSAGE_EDIT_BUDGET_SUCCESS, editedBudget),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(editedBudget), modelStub.budgets);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_dateSpecified_success() throws Exception {
        ModelStubWithOneCurrentBudget modelStub = new ModelStubWithOneCurrentBudget();

        Budget budgetToEdit = new BudgetBuilder().build();
        EditBudgetDetails details = new EditBudgetDetails();
        details.setAmount(budgetToEdit.getCost());
        details.setPeriod(budgetToEdit.getPeriod());

        Date newDate = new GregorianCalendar(2010, Calendar.FEBRUARY, 11).getTime();
        details.setStartDate(newDate);

        Budget editedBudget = createEditedBudget(budgetToEdit, details);
        CommandResult commandResult = new EditBudgetCommand(details).execute(modelStub, commandHistory);

        assertEquals(String.format(EditBudgetCommand.MESSAGE_EDIT_BUDGET_SUCCESS, editedBudget),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(editedBudget), modelStub.budgets);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_noCurrentBudget_failure() throws Exception {
        EditBudgetDetails details = new EditBudgetDetailsBuilder().withAmount(VALID_AMOUNT_ONE).build();

        EditBudgetCommand editBudgetCommand = new EditBudgetCommand(details);
        ModelStubWithNoCurrentBudget modelStub = new ModelStubWithNoCurrentBudget();

        thrown.expect(CommandException.class);
        thrown.expectMessage(EditBudgetCommand.MESSAGE_EDIT_BUDGET_FAIL);
        editBudgetCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        final EditBudgetCommand standardCommand = new EditBudgetCommand(DESC_ONE);

        // same values -> returns true
        EditBudgetDetails copyDetails = new EditBudgetDetails(DESC_ONE);
        EditBudgetCommand commandWithSameValues = new EditBudgetCommand(copyDetails);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different details -> returns false
        assertFalse(standardCommand.equals(new EditBudgetCommand(DESC_TWO)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public ObservableList<Budget> getBudgetList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortExpenses(String keyword) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getExpenseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEPiggyFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEPiggyFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAllowance(Allowance allowance) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Expense> selectedExpenseProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBudget(int index, Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean budgetsOverlap(Date startDate, Date endDate, Budget earlierBudget) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteBudgetAtIndex(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCurrentBudgetIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SimpleObjectProperty<Savings> getSavings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SimpleObjectProperty<Goal> getGoal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGoal(Goal goal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEPiggy(ReadOnlyEPiggy newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEPiggy getEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentBudget(Budget editedBudget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Budget> getFilteredBudgetList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getFilteredExpenseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBudgetList(Predicate<Budget> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpensesList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitEPiggy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Person> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Expense getSelectedExpense() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithOneCurrentBudget extends ModelStub {
        final ArrayList<Budget> budgets = new ArrayList<>();
        private Date todaysDate = new Date();

        ModelStubWithOneCurrentBudget() {
            Budget currentBudget = new BudgetBuilder().withDate(todaysDate).build();
            budgets.add(currentBudget);
        }

        @Override
        public int getCurrentBudgetIndex() {
            return 0;
        }

        @Override
        public void setCurrentBudget(Budget editedBudget) {
            budgets.set(0, editedBudget);
        }

        @Override
        public void updateFilteredBudgetList(Predicate<Budget> predicate) {
        }

        @Override
        public ObservableList<Budget> getFilteredBudgetList() {
            return FXCollections.observableArrayList(budgets);
        }

        @Override
        public void commitEPiggy() {
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithNoCurrentBudget extends ModelStub {
        final ArrayList<Budget> budgets = new ArrayList<>();

        @Override
        public int getCurrentBudgetIndex() {
            return -1;
        }

        @Override
        public ObservableList<Budget> getFilteredBudgetList() {
            return FXCollections.observableArrayList(budgets);
        }
    }
}
