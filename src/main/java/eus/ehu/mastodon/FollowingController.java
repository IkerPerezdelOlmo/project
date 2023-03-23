package eus.ehu.mastodon;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import social.bigbone.api.entity.Account;
import social.bigbone.api.method.AccountMethods;

public class FollowingController{
    private ArrayList<Account> list;
    private Type accountList;
    private JsonArray jsonArray;
    private String body;
    private Gson gson;
    private int accountPosition=0;

    private AccountMethods accountMethods;

    @FXML
    private TextField authorNameValue;

    @FXML
    private CheckBox boost;

    @FXML
    private ImageView avatarImage;
    @FXML
    private Label errorLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private TextField acccountName;


    @FXML
    private Button disableFollowingBut;


    @FXML
    void disableFollowing(ActionEvent event) {
        Account quitedFollowing= list.get(accountPosition);
        String id ="testing";
        body = HttpMethods.request("accounts/"+id+"/following");

    }

    @FXML
    void nextButtonPressed(ActionEvent event) {
        if (accountPosition<list.size()-1){
            accountPosition++;
            showAccount();
            errorLabel.setText("");
        }
        else
        errorLabel.setText("This is the last toot");
        errorLabel.setStyle("-fx-text-fill: #ef0505");
    }

    @FXML
    void previousButtonPressed(ActionEvent event) {
        if (accountPosition>0){
            accountPosition--;
            showAccount();
            errorLabel.setText("");
        }
        else
            errorLabel.setText("This is the first toot");
        errorLabel.setStyle("-fx-text-fill: #ef0505");
    }

    void showAccount(){
        authorNameValue.setText(list.get(accountPosition).getUsername());
        avatarImage.setImage(new Image(list.get(accountPosition).getAvatar()));
        acccountName.setText(list.get(accountPosition).getDisplayName());


    }
    @FXML
    void initialize() {
        String id = "109897230504677704";
        accountMethods.getFollowing(id);
        body = HttpMethods.request("accounts/"+id+"/following");
        gson = new Gson();
        jsonArray=gson.fromJson(body, JsonArray.class);
        accountList= new TypeToken<ArrayList<Account>>() {}.getType();
        list=new ArrayList<Account>(gson.fromJson(jsonArray.getAsJsonArray(), accountList));
        showAccount();
    }

}

