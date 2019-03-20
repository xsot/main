package seedu.address.logic;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ShowAnswerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;

public class StudyView implements ViewState {
    private final Model model;
    public final List<Card> listOfCards;
    private final SimpleObjectProperty<Card> selectedCard = new SimpleObjectProperty<>();
    private final Deck activeDeck;

    public StudyView(Model model, Deck deck) {
        this.model = model;
        this.activeDeck = deck;
        listOfCards = deck.getCards().internalList;
    }

    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {

        if (model.getCurrentStudyState() == Model.studyState.QUESTION) {
            return new ShowAnswerCommand();
        } else {
            return new GenerateQuestionCommand();
        }
        switch (commandWord) {
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand();
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }




}
