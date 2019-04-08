package seedu.address.testutil.epiggy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import seedu.address.logic.commands.epiggy.EditBudgetCommand;
import seedu.address.model.epiggy.Budget;
import seedu.address.model.expense.Cost;
import seedu.address.model.expense.Period;

/**
 * A utility class to help with building EditBudgetDetails objects.
 */
public class EditBudgetDetailsBuilder {

    private EditBudgetCommand.EditBudgetDetails details;

    public EditBudgetDetailsBuilder() {
        details = new EditBudgetCommand.EditBudgetDetails();
    }

    public EditBudgetDetailsBuilder(EditBudgetCommand.EditBudgetDetails details) {
        this.details = new EditBudgetCommand.EditBudgetDetails(details);
    }

    /**
     * Returns an {@code EditBudgetDetails} with fields containing {@code person}'s details
     */
    public EditBudgetDetailsBuilder(Budget budget) {
        details = new EditBudgetCommand.EditBudgetDetails();
        details.setAmount(budget.getBudgetedAmount());
        details.setPeriod(budget.getPeriod());
        details.setStartDate(budget.getStartDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditBudgetDetails} that we are building.
     */
    public EditBudgetDetailsBuilder withAmount(String amount) {
        details.setAmount(new Cost(amount));
        return this;
    }

    /**
     * Sets the {@code Period} of the {@code EditBudgetDetails} that we are building.
     */
    public EditBudgetDetailsBuilder withPeriod(String period) {
        details.setPeriod(new Period(period));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditBudgetDetails} that we are building.
     */
    public EditBudgetDetailsBuilder withDate(String startDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            details.setStartDate(dateFormat.parse(startDate));
        } catch (ParseException e) {
            System.out.println("Date should be in the format dd/mm/yyyy.");
        }
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditBudgetDetails} that we are building.
     */
    public EditBudgetDetailsBuilder withDate(Date startDate) {
        details.setStartDate(startDate);
        return this;
    }

    public EditBudgetCommand.EditBudgetDetails build() {
        return details;
    }
}
