package seedu.address.logic.commands;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.StudyView;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new HelpCommandResult(SHOWING_HELP_MESSAGE);
        assertCommandSuccess(new HelpCommand(), model, commandHistory, expectedCommandResult, expectedModel);
    }
}
