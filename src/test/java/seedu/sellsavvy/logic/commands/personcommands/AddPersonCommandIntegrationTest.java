package seedu.sellsavvy.logic.commands.personcommands;

import static seedu.sellsavvy.logic.commands.personcommands.PersonCommandTestUtil.assertCommandFailure;
import static seedu.sellsavvy.logic.commands.personcommands.PersonCommandTestUtil.assertCommandSuccess;
import static seedu.sellsavvy.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sellsavvy.logic.Messages;
import seedu.sellsavvy.model.Model;
import seedu.sellsavvy.model.ModelManager;
import seedu.sellsavvy.model.UserPrefs;
import seedu.sellsavvy.model.person.Person;
import seedu.sellsavvy.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPersonCommand}.
 */
public class AddPersonCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddPersonCommand(validPerson), model,
                String.format(AddPersonCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddPersonCommand(personInList), model,
                AddPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

}