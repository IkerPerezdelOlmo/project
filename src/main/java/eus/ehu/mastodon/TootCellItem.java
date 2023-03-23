package eus.ehu.mastodon;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import social.bigbone.api.entity.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import social.bigbone.api.entity.Status;


public class TootCellItem  extends ListCell<Status> {
    private FXMLLoader loader;

    @FXML
    private TextField authorNameValue;

    @FXML
    private CheckBox boost;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private TextField publicationDateField;

    @FXML
    private WebView toot;
    @FXML
    private Label errorLabel;


    @Override
    protected void updateItem(Status item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);

            return;
        }

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        toot.getEngine().getLoadWorker().stateProperty().addListener(new HyperLinkRedirectListener(toot));
        showToot(item);


        setText(null);
        setGraphic(anchorPane);
    }
    void showToot(Status item){
        Status status=item;
        if( status.getReblog()!=null) {
            boost.setSelected(true);
            status = getToot(status);

        }
        else
            boost.setSelected(false);
        publicationDateField.setText(status.getCreatedAt());
        toot.getEngine().loadContent(status.getContent());
        authorNameValue.setText(status.getAccount().getDisplayName());


    }
    Status getToot(Status status){
        if( status.getReblog()!=null){
            return status.getReblog();
        }
        return status;
    }
}
