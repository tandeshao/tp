package seedu.address.ui;

import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Person;

public class DetailedPersonDisplay extends UiPart<Region> {
    public static final String FXML = "DetailedPersonDisplay.fxml";
    public static final String DEFAULT_EMPTY_MESSAGE = "-";

    @FXML
    private StackPane personDisplayPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label lastContactLabel;

    @FXML
    private Label memoLabel;

    @FXML
    private FlowPane tagsPane;

    public DetailedPersonDisplay() {
        super(FXML);
    }

    public ChangeListener<? super Person> getListener() {
        return (ChangeListener<Person>) (observable, oldPerson, newPerson) -> {
            if (newPerson != null) {
                String newName = newPerson.getName().toString();
                nameLabel.setText(newName);

                String newPhone = newPerson.getPhone().toString();
                phoneLabel.setText(newPhone);

                String newEmail = newPerson.getEmail().toString();
                emailLabel.setText(newEmail);

                String newAddress = newPerson.getAddress().toString();
                addressLabel.setText(newAddress);

                String newLastContact = newPerson.getContactedDate().toString();
                lastContactLabel.setText(newLastContact);

                String newMemo = DEFAULT_EMPTY_MESSAGE;
                if (!newPerson.isMemoEmpty()) {
                    newMemo = newPerson.getMemo().toString();
                }
                memoLabel.setText(newMemo);

                tagsPane.getChildren().clear();
                newPerson.getTags().stream()
                        .sorted(Comparator.comparing(tag -> tag.tagName))
                        .forEach(tag -> tagsPane.getChildren().add(new Label(tag.toString())));
            } else {
                nameLabel.setText(DEFAULT_EMPTY_MESSAGE);
                phoneLabel.setText(DEFAULT_EMPTY_MESSAGE);
                emailLabel.setText(DEFAULT_EMPTY_MESSAGE);
                addressLabel.setText(DEFAULT_EMPTY_MESSAGE);
                memoLabel.setText(DEFAULT_EMPTY_MESSAGE);
                lastContactLabel.setText(DEFAULT_EMPTY_MESSAGE);
                tagsPane.getChildren().clear();
            }
        };
    }
}
