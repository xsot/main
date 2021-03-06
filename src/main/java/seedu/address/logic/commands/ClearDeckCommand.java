package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.TopDeck;

/**
 * Clears TopDeck.
 */
public class ClearDeckCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TopDeck has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.setTopDeck(new TopDeck());
        model.commitTopDeck();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
