package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class DetailedPersonDisplay extends UiPart<Region> {
    public static final String FXML = "DetailedPersonDisplay.fxml";

    @FXML
    private StackPane personDisplayPane;

    public DetailedPersonDisplay() {
        super(FXML);
    }
}
