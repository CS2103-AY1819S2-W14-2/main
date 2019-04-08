package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.epiggy.TypicalExpenses.getTypicalEPiggy;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.EPiggy;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.storage.epiggy.JsonSerializableEPiggy;

@Ignore
public class JsonSerializableEPiggyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEPiggyTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsEPiggyBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonEPiggyBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonEPiggyBook.json");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableEPiggy dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableEPiggy.class).get();
        EPiggy ePiggyFromFile = dataFromFile.toModelType();
        EPiggy typicalPersonsEPiggy = getTypicalEPiggy();
        assertEquals(ePiggyFromFile, typicalPersonsEPiggy);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEPiggy dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableEPiggy.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    //    @Test
    //    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
    //        JsonSerializableEPiggy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //                JsonSerializableEPiggy.class).get();
    //        thrown.expect(IllegalValueException.class);
    //        thrown.expectMessage(JsonSerializableEPiggy.MESSAGE_DUPLICATE_PERSON);
    //        dataFromFile.toModelType();
    //    }

}
