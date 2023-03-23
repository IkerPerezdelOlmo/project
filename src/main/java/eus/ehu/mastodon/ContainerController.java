package eus.ehu.mastodon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.entity.Account;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.AccountMethods;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ContainerController {

    private ArrayList<Account> list;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Account> listView;


    void showList(){
        MastodonClient client = new MastodonClient.Builder("mastodon.social").accessToken(System.getenv("TOKEN")).build();
        try{
            Pageable<Account> timeline=client.accounts().getFollowing("109897230504677704").execute();
            List<Account>following=timeline.getPart();
            ObservableList<Account> items= FXCollections.observableList(following);
            if(listView!=null){
                listView.setItems(items);
                listView.setCellFactory(param ->{
                    var cell=new FollowingCellItem();
                    return cell;
                });
            }
        }
        catch(BigBoneRequestException e){
            throw new RuntimeException(e);
        }

    }
    @FXML
    void initialize() {
        String id = "109897230504677704";

        String body = HttpMethods.request("accounts/"+id+"/following");
        Gson gson = new Gson();
        JsonArray jsonArray=gson.fromJson(body, JsonArray.class);
        Type accountList= new TypeToken<ArrayList<Account>>() {}.getType();
        list=new ArrayList<Account>(gson.fromJson(jsonArray.getAsJsonArray(), accountList));
        showList();


    }

}
