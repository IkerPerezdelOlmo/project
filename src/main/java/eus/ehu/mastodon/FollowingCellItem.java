package eus.ehu.mastodon;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import social.bigbone.api.entity.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class FollowingCellItem  extends ListCell<Account> {
    private FXMLLoader loader;

    @FXML
    private TextField acccountName;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField authorNameValue;

    @FXML
    private ImageView avatarImage;

    @FXML
    private Button disableFollowingBut;

    @FXML
    private Label errorLabel;

    @FXML
    void disableFollowing(ActionEvent event) {
    }
    @Override
    protected void updateItem(Account item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);

            return;
        }

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("followings-view.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        authorNameValue.setText(item.getDisplayName());
        avatarImage.setImage(new Image(item.getAvatar()));
        acccountName.setText("@"+item.getUsername());

        setText(null);
        setGraphic(anchorPane);
    }
}
