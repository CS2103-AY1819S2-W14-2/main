package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Config.ASSETS_FILEPATH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATH;

import java.io.File;
import java.util.OptionalDouble;

import seedu.address.logic.commands.BrightnessCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * This class parses the contrast command.
 */
public class BrightnessCommandParser implements Parser<BrightnessCommand> {

    /**
     * Parses the Contrast Command.
     * @param args
     * @return a ContrastCommand object
     * @throws ParseException
     */
    public BrightnessCommand parse(String args) throws ParseException {
        String fileName;
        OptionalDouble brightnessValue;
        requireNonNull(args);
        args = args.trim();
        String[] parsed = args.split(" ");

        if (parsed.length < 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BrightnessCommand.MESSAGE_USAGE));
        }

        if (parsed.length == 2) {
            brightnessValue = OptionalDouble.of(Double.parseDouble(parsed[0]));
            if (brightnessValue.getAsDouble() < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BrightnessCommand.MESSAGE_USAGE));
            }
            fileName = parsed[1];
        } else {
            brightnessValue = OptionalDouble.empty();
            fileName = parsed[0];
        }

        File directory = new File(ASSETS_FILEPATH + fileName);

        if (!directory.exists()) {
            throw new ParseException(String.format(MESSAGE_INVALID_PATH, BrightnessCommand.MESSAGE_USAGE));
        }

        return new BrightnessCommand(brightnessValue, fileName);
    }
}
