package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;

import seedu.address.logic.commands.OpenCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.image.Image;

/**
 * Parses input arguments and creates a new ImportImage object
 */
public class OpenCommandParser implements Parser<OpenCommand> {
    /**
     * Parses the given {@code String} of arguments in the context
     * of the OpenCommand and returns an OpenCommand object for execution.
     *
     * @throws ParseException   if the user input does not conform the expected format.
     */
    public OpenCommand parse(String args) throws ParseException {
        args = args.trim();
        Image image;
        File file = new File("src/main/resources/assets/" + args);

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
        }

        if (file.isFile()) {
            image = new Image("src/main/resources/assets/" + args);
        } else {
            throw new ParseException(String.format(MESSAGE_FILE_NOT_FOUND, OpenCommand.MESSAGE_USAGE));
        }

        return new OpenCommand(image);
    }
}
