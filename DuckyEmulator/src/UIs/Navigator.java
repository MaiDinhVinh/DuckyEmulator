package UIs;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    /**
     * If we change the position of the {@code .fxml} files<p>
     * to another position, then we have to add the new package name<p>
     * where the fxml file reside !*/
    private static final String HOME_FXML = "AdminUIs/HomePageUI.fxml";

    private static final String QBANK_INDEX = "AdminUIs/QBankIndexUI.fxml";

    private static final String QBANK_ADD = "AdminUIs/QBankAddUI.fxml";

    private static final String QBANK_UPDATE = "AdminUIs/QBankUpdateUI.fxml";

    private static final String TOPICS_CLASS_INDEX = "AdminUIs/TopicsClassIndexUI.fxml";

    private static final String TOPICS_INDEX_ADD = "AdminUIs/TopicsAddUI.fxml";

    private static final String TOPICS_INDEX_UPDATE = "AdminUIs/TopicsUpdateUI.fxml";

    private static final String CLASS_INDEX_ADD = "AdminUIs/ClassificationsAddUI.fxml";

    private static final String CLASS_INDEX_UPDATE = "AdminUIs/ClassificationsUpdateUI.fxml";

    private Stage stage;

    private static Navigator nav = null;

    private FXMLLoader loader;

    private Navigator(){}

    public static Navigator getInstance(){
        if(Navigator.nav == null){
            Navigator.nav = new Navigator();
            return Navigator.nav;
        }
        return Navigator.nav;
    }

    public Stage getStage() {
        return this.stage;
    }

   public FXMLLoader getLoader(){
        return this.loader;
   }

   public void setStage(Stage stage){
        this.stage = stage;
   }
   private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(fxml)));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.generateNewWindow(fxml, scene);
   }

    /**
     * <pre>{@code
     * Rectangle2D screenBound = Screen.getPrimary().getVisualBounds();
     * this.stage.setX(screenBound.getMinX());
     * this.stage.setY(screenBound.getMinY());
     * this.stage.setWidth(screenBound.getWidth());
     * this.stage.setHeight(screenBound.getHeight());}</pre><p>
     * The first line is used to get the general shape of your screen (also gather your screen's resolution)<p>
     * The next 2 lines is to set the initial position of the stage at the minimum positon<p>
     * The last 2 lines is to set the stage size using the resolution extracted from the shape gathed in the first line<p>
     * <p>
     * <pre>
     * {@code
     * qBank.initModality(Modality.WINDOW_MODAL);
     * qBank.initOwner(this.stage);
     * if(qBank != null) qBank.requestFocus();}
     * </pre><p>
     * In order to generate 2 windows at once, we will need 2 separate stages<p>
     * The first step is just basic, create separate stage, scene for our UIs.<p>
     * However, in order for the second windows to work perfectly and also block any interactions<p>
     * with our main windows, we will neeed additional 3 lines:<p>
     * {@code qBank.initModality(Modality.WINDOW_MOAL);} these line indicate that we will block all<p>
     * interactions with the primary windows (which we will set which one in the next line)<p>
     * {@code qBank.initOwner(this.stage);} this line means that we will set the primary windows is the main windows<p>
     * we are using right now (in this case either TopicClassIndex or QuestionBank index)<p>
     * {@code if(qBank != null) qBank.requestFocus();} this line will determine whether our secondary window has shown in the screen or not.<p>
     * If not, we will ask it to bring it to the front of the screen*/

    private void generateNewWindow(String fxml, Scene currentScene) throws IOException {
       if(!fxml.equals(QBANK_ADD) & !fxml.equals(QBANK_UPDATE)
               & !fxml.equals(TOPICS_INDEX_ADD) & !fxml.equals(TOPICS_INDEX_UPDATE)
               & !fxml.equals(CLASS_INDEX_ADD) & !fxml.equals(CLASS_INDEX_UPDATE)){
           Rectangle2D screenBound = Screen.getPrimary().getVisualBounds();
           this.stage.setX(screenBound.getMinX());
           this.stage.setY(screenBound.getMinY());
           this.stage.setWidth(screenBound.getWidth());
           this.stage.setHeight(screenBound.getHeight());
           this.stage.setScene(currentScene);
       }else{
           Stage qBank = new Stage();
           Parent secondRoot = null;
           if(fxml.equals(QBANK_ADD))
               secondRoot = FXMLLoader.load(getClass().getResource(QBANK_ADD));
           else if(fxml.equals(QBANK_UPDATE))
               secondRoot = FXMLLoader.load(getClass().getResource(QBANK_UPDATE));
           else if(fxml.equals(TOPICS_INDEX_ADD))
               secondRoot = FXMLLoader.load(getClass().getResource(TOPICS_INDEX_ADD));
           else if(fxml.equals(TOPICS_INDEX_UPDATE))
               secondRoot = FXMLLoader.load(getClass().getResource(TOPICS_INDEX_UPDATE));
           else if(fxml.equals(CLASS_INDEX_ADD))
               secondRoot = FXMLLoader.load(getClass().getResource(CLASS_INDEX_ADD));
           else
               secondRoot = FXMLLoader.load(getClass().getResource(CLASS_INDEX_UPDATE));
           Scene qBankAddScene = new Scene(secondRoot);
           qBank.setScene(qBankAddScene);
           qBank.initModality(Modality.WINDOW_MODAL);
           qBank.initOwner(this.stage);
           if(qBank != null) qBank.requestFocus();
           qBank.show();
       }
   }

   public void goToHome() throws IOException {
        this.goTo(HOME_FXML);
   }

   public void goToQBankIndex() throws IOException {
        this.goTo(QBANK_INDEX);
   }

   public void goToQBankAdd() throws IOException {
        this.goTo(QBANK_ADD);
   }

   public void goToQBankUpdate() throws IOException {
        this.goTo(QBANK_UPDATE);
   }

   public void goToTopicClassIndex() throws IOException {
        this.goTo(TOPICS_CLASS_INDEX);
   }

   public void goToTopicIndexAdd() throws IOException{
       this.goTo(TOPICS_INDEX_ADD);
   }

   public void goToTopicIndexUpdate() throws IOException {
        this.goTo(TOPICS_INDEX_UPDATE);
   }

   public void goToClassIndexAdd() throws IOException{
       this.goTo(CLASS_INDEX_ADD);
   }

    public void goToClassIndexUpdate() throws IOException{
        this.goTo(CLASS_INDEX_UPDATE);
    }
}
