package seedu.sellsavvy.logic.commands.ordercommands;

import seedu.sellsavvy.commons.core.index.Index;
import seedu.sellsavvy.logic.Messages;
import seedu.sellsavvy.logic.commands.Command;
import seedu.sellsavvy.logic.commands.CommandResult;
import seedu.sellsavvy.logic.commands.exceptions.CommandException;
import seedu.sellsavvy.model.Model;
import seedu.sellsavvy.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.sellsavvy.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all orders under a specified person to the user.
 */
public class ListOrderCommand extends Command {
    public static final String COMMAND_WORD = "listOrder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List all orders under the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_LIST_ORDER_SUCCESS = "Listed all orders under %1$s";

    private final Index index;

    /**
     * Creates a {@code ListOrderCommand} to list all orders for a specified person.
     *
     * @param index The index of the person in the filtered person list whose orders will be displayed.
     */
    public ListOrderCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person selectedPerson = lastShownList.get(index.getZeroBased());
        model.updateSelectedPerson(selectedPerson);
        return new CommandResult(String.format(MESSAGE_LIST_ORDER_SUCCESS, selectedPerson.getName().fullName));
    }

}
