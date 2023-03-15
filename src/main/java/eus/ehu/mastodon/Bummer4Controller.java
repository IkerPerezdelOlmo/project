package eus.ehu.mastodon;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class Bummer4Controller {
    private ArrayList<Status> list;
    private Type statusList;
    private JsonArray jsonArray;
    private String body;
    private Gson gson;
    private int tootPosition=0;

    @FXML
    private TextField authorNameValue;

    @FXML
    private CheckBox boost;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private TextField publicationDateField;

    @FXML
    private WebView toot;
    @FXML
    private Label errorLabel;
    @FXML
    void nextButtonPressed(ActionEvent event) {
        if (tootPosition<list.size()-1){
            tootPosition++;
            showToot();
            errorLabel.setText("");
        }
        else
            errorLabel.setText("This is the last toot");
            errorLabel.setStyle("-fx-text-fill: #ef0505");

    }

    @FXML
    void previousButtonPressed(ActionEvent event) {
        if (tootPosition>0){
            tootPosition--;
            showToot();
            errorLabel.setText("");
        }
        else
            errorLabel.setText("This is the first toot");
            errorLabel.setStyle("-fx-text-fill: #ef0505");
    }
    void showToot(){
        Status status=list.get(tootPosition);
        if( status.reblog!=null) {
            boost.setSelected(true);
            status = getToot(status);

        }
        else
            boost.setSelected(false);
        publicationDateField.setText(status.created_at);
        toot.getEngine().loadContent(status.content);
        authorNameValue.setText(status.account.display_name);


    }
    Status getToot(Status status){
        if( status.reblog!=null){
            return status.reblog;
        }
        return status;
    }
    @FXML
    void initialize() {
        String id = "109897230504677704";
        body = HttpMethods.request("accounts/"+id+"/statuses");
        gson = new Gson();
        jsonArray=gson.fromJson(body, JsonArray.class);
        statusList= new TypeToken<ArrayList<Status>>() {}.getType();
        list=new ArrayList<Status>(gson.fromJson(jsonArray.getAsJsonArray(), statusList));
        toot.getEngine().getLoadWorker().stateProperty().addListener(new HyperLinkRedirectListener(toot));
        showToot();

    }

}
