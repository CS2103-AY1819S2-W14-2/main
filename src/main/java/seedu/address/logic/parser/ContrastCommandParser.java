package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Config.ASSETS_FILEPATH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATH;

import java.io.File;
import java.util.OptionalDouble;

import seedu.address.logic.commands.ContrastCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class parses the contrast command.
 */
public class ContrastCommandParser implements Parser<ContrastCommand> {

    /**
     * Parses the Contrast Command.
     * @param args
     * @return a ContrastCommand object
     * @throws ParseException
     */
    public ContrastCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String fileName;
        OptionalDouble contrastValue;
        args = args.trim();
        String[] parsed = args.split(" ");

        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContrastCommand.MESSAGE_USAGE));
        }

        if (parsed.length < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContrastCommand.MESSAGE_USAGE));
        }

        if (parsed.length == 2) {
            contrastValue = OptionalDouble.of(Double.parseDouble(parsed[0]));
            if (contrastValue.getAsDouble() < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContrastCommand.MESSAGE_USAGE));
            }
            fileName = parsed[1];
        } else {
            contrastValue = OptionalDouble.empty();
            fileName = parsed[0];
        }

        File directory = new File(ASSETS_FILEPATH + fileName);

        if (!directory.exists()) {
            throw new ParseException(String.format(MESSAGE_INVALID_PATH, ContrastCommand.MESSAGE_USAGE));
        }

        return new ContrastCommand(contrastValue, fileName);
    }
}