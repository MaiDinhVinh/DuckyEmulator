package Database.MainDB.Beans;

import Database.DBService.MySQLService;
import Utilities.PromptAlert.AlertUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Topics {
    private ObjectProperty<Integer> topicId;
    private StringProperty topicName;
    private StringProperty topicDescription;

    //for question table view
    public static ArrayList<Topics> topicsQuestionView;

    public Topics(){
        topicId = new SimpleObjectProperty<Integer>(null);
        topicName = new SimpleStringProperty();
        topicDescription = new SimpleStringProperty();
    }

    public Integer getTopicId(){
        return this.topicId.get();
    }

    public String getTopicName(){
        return this.topicName.get();
    }

    public String getTopicDescription(){
        return this.topicDescription.get();
    }

    public ObjectProperty<Integer> getTopicIdProperty(){
        return this.topicId;
    }

    public StringProperty getTopicNameProperty(){
        return this.topicName;
    }

    public StringProperty getTopicDescriptionProperty(){
        return this.topicDescription;
    }

    public void setTopicId(int id){
        this.topicId.set(id);
    }

    public void setTopicName(String name) {
        this.topicName.set(name);
    }

    public void setTopicDescription(String description){
        this.topicDescription.set(description);
    }

    public static ObservableList<Topics> selectAll(){
        topicsQuestionView = new ArrayList<>();
        ObservableList<Topics> topics = FXCollections.observableArrayList();
        try(
                Connection conn = MySQLService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Topics;");
                ){
            while(rs.next()){
                Topics top = new Topics();
                top.setTopicId(rs.getInt("TopicId"));
                top.setTopicName(rs.getString("TopicName"));
                top.setTopicDescription(rs.getString("Description"));
                topics.add(top);
                topicsQuestionView.add(top);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return topics;
    }

    public static boolean delete(Topics top){
        String sqlTopicDel = "DELETE FROM Topics WHERE TopicId = ?;";
        try(
                Connection conn = MySQLService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlTopicDel);
        ){
            stmt.setInt(1, top.getTopicId());
            boolean isDeleted = stmt.executeUpdate() == 1 ? true : false;
            if(isDeleted) return true;
            else return false;
        }catch(Exception e){
            AlertUtil.generateErrorWindow("Topic deletion failed",
                    "Topic deletion",
                    "There are questions that are depending on this topic type, please delete the question first !");
            return false;
        }
    }

    public static void insert(Topics newTopic){
        String sqlInsert = "INSERT INTO Topics(TopicName, Description) "
                + "VALUES(?, ?);";
        try(
                Connection conn = MySQLService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlInsert);
                ){
            stmt.setString(1, newTopic.getTopicName());
            stmt.setString(2, newTopic.getTopicDescription());
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
