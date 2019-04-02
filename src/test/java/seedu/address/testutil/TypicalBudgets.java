package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EPiggy;
import seedu.address.model.epiggy.Budget;
import seedu.address.testutil.epiggy.BudgetBuilder;

/**
 * A utility class containing a list of {@code Budget} objects to be used in tests.
 */
public class TypicalBudgets {

    // Date is in the form MM/dd/yyyy
    public static final Budget ONE = new BudgetBuilder().withAmount("100")
            .withPeriod("7").withDate("02/04/2019").build();
    public static final Budget TWO = new BudgetBuilder().withAmount("300")
            .withPeriod("18").withDate("02/11/2019").build();
    public static final Budget THREE = new BudgetBuilder().withAmount("500")
            .withDate("03/01/2019").withPeriod("30").build();
    public static final Budget FOUR = new BudgetBuilder().withAmount("15")
            .withDate("03/31/2019").withPeriod("1").build();
    public static final Budget FIVE = new BudgetBuilder().withAmount("20")
            .withDate("04/01/2019").withPeriod("2").build();
    public static final Budget SIX = new BudgetBuilder().withAmount("90")
            .withDate("04/15/2019").withPeriod("5").build();
    public static final Budget SEVEN = new BudgetBuilder().withAmount("12000")
            .withDate("04/20/2019").withPeriod("365").build();
    public static final Budget EIGHT = new BudgetBuilder().withAmount("90")
            .withDate("02/01/2019").withPeriod("3").build();
    public static final Budget NINE = new BudgetBuilder().withAmount("12")
            .withDate("01/31/2019").withPeriod("1").build();

    // Other budgets
    public static final Budget TEN = new BudgetBuilder().withAmount("10")
            .withPeriod("1").withDate("02/04/2020").build();
    public static final Budget ELEVEN = new BudgetBuilder().withAmount("30")
            .withPeriod("1").withDate("02/05/2020").build();
    public static final Budget TWELVE = new BudgetBuilder().withAmount("50")
            .withDate("02/06/2020").withPeriod("1").build();
    public static final Budget THIRTEEN = new BudgetBuilder().withAmount("15")
            .withDate("02/07/2020").withPeriod("1").build();
    public static final Budget FOURTEEN = new BudgetBuilder().withAmount("20")
            .withDate("02/08/2020").withPeriod("1").build();
    public static final Budget FIFTEEN = new BudgetBuilder().withAmount("9")
            .withDate("02/09/2020").withPeriod("1").build();
    public static final Budget SIXTEEN = new BudgetBuilder().withAmount("15")
            .withDate("02/10/2020").withPeriod("1").build();
    public static final Budget SEVENTEEN = new BudgetBuilder().withAmount("90")
            .withDate("02/11/2020").withPeriod("1").build();
    public static final Budget EIGHTEEN = new BudgetBuilder().withAmount("12")
            .withDate("02/12/2020").withPeriod("1").build();
    public static final Budget NINETEEN = new BudgetBuilder().withAmount("12")
            .withDate("02/13/2020").withPeriod("1").build();
    public static final Budget TWENTY = new BudgetBuilder().withAmount("12")
            .withDate("02/14/2020").withPeriod("1").build();

    // TODO
    // Manually added - Budget's details found in {@code CommandTestUtil}
    //    public static final Budget TEN = new BudgetBuilder().withAmount(VALID_NAME_AMY)
    //            .withDate(VALID_EMAIL_AMY).withPeriod(VALID_ADDRESS_AMY).build();
    //    public static final Budget ELEVEN = new BudgetBuilder().withAmount(VALID_NAME_BOB)
    //            .withDate(VALID_EMAIL_BOB).withPeriod(VALID_ADDRESS_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBudgets() {} // prevents instantiation

    /**
     * Returns an {@code EPiggy} with all the typical persons.
     */
    public static EPiggy getTypicalEPiggy() {
        EPiggy ab = new EPiggy();
        int i = 0;
        for (Budget budget : getTypicalBudgets()) {
            ab.addBudget(i, budget);
            i++;
        }
        return ab;
    }

    public static List<Budget> getTypicalBudgets() {
        return new ArrayList<>(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE));
    }

    public static List<Budget> getMaximumNumberOfBudgets() {
        return new ArrayList<>(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN,
                TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY));
    }
}
