package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.deck.QuestionContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all cards in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCardCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all cards whose questions contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final QuestionContainsKeywordsPredicate predicate;

    public FindCardCommand(QuestionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_CARDS_LISTED_OVERVIEW, model.getFilteredList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCardCommand // instanceof handles nulls
            && predicate.equals(((FindCardCommand) other).predicate)); // state check
    }
}