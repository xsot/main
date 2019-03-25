package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CARDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.ADDITION;
import static seedu.address.testutil.TypicalCards.getTypicalTopDeck;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.CardNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTopDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTopDeck(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        CardNameContainsKeywordsPredicate firstPredicate =
                new CardNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CardNameContainsKeywordsPredicate secondPredicate =
                new CardNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different card -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCardFound() {
        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 0);
        CardNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredList());
    }

    @Test
    public void execute_multipleKeywords_multipleCardsFound() {
        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 1);
        CardNameContainsKeywordsPredicate predicate = preparePredicate("+ - *");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ADDITION), model.getFilteredList());
    }

    /**
     * Parses {@code userInput} into a {@code CardNameContainsKeywordsPredicate}.
     */
    private CardNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CardNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
