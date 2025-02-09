/*
 * Copyright (c) 2025 Arthroverse Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Organization: Arthroverse Laboratory
 * Author: Vinh Dinh Mai
 * Contact: business@arthroverse.com
 *
 *
 * @author ducksabervn
 */
package UIControllers.AdminUIsControllers;

import Database.MainDB.Beans.Classifications;
import Database.MainDB.Beans.Questions;
import Database.MainDB.Beans.Topics;
import UIs.Navigator;
import Utilities.PromptAlert.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Database.MainDB.Beans.Topics.topicsQuestionView;
import static Database.MainDB.Beans.Classifications.classQuestionView;

public class QBankUpdateUIController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxSelectTopic;

    @FXML
    private ChoiceBox<String> choiceBoxCorrectAns;

    @FXML
    private Button btnChooseImagePath;

    @FXML
    private TextField txtFieldImagePath;

    @FXML
    private ChoiceBox<String> choiceBoxSelectClass;

    @FXML
    private TextArea txtxAreaQChoice2;

    @FXML
    private TextArea txtxAreaQChoice1;

    @FXML
    private TextArea txtAreaQStatement;

    @FXML
    private TextArea txtxAreaQChoice4;

    @FXML
    private TextArea txtxAreaQChoice3;

    @FXML
    private Button btnResetField;

    @FXML
    private Button btnUpdateCurrentQuestion;

    @FXML
    private Button btnAddTopic;

    @FXML
    private Button btnRemoveTopic;

    @FXML
    private TableView<Topics> tableViewSelectedTopic;

    @FXML
    private TableColumn<Topics, String> tableColSelectedTopicName;

    private static ArrayList<String> topicName = new ArrayList<>();

    private static ArrayList<String> className = new ArrayList<>();

    private static StringBuilder errorMessage = new StringBuilder();

    private static Questions updateQuestion;

    private static ArrayList<Topics> selectedTopics = new ArrayList<>();

    @FXML
    void btnChooseImagePathClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateCurrentQuestionClick(ActionEvent event) throws IOException {
        ArrayList<String> selectedTopicNames = new ArrayList<>();
        for(Topics t: selectedTopics){
            selectedTopicNames.add(t.getTopicName());
        }
        ArrayList<Integer> selectedTopicIds = Topics.findingTopicIds(selectedTopicNames);
        updateQuestion.setForeignKeyTopicId(selectedTopicIds);
        updateQuestion.setForeignKeyClassificationId(
                Classifications.searchClassificationByName(
                        choiceBoxSelectClass.getValue()
                )
        );
        updateQuestion.setQuestionStatement(txtAreaQStatement.getText());
        updateQuestion.setChoice1(txtxAreaQChoice1.getText());
        updateQuestion.setChoice2(txtxAreaQChoice2.getText());
        updateQuestion.setChoice3(txtxAreaQChoice3.getText());
        updateQuestion.setChoice4(txtxAreaQChoice4.getText());
        updateQuestion.setCorrectAnswer(choiceBoxCorrectAns.getValue());
        if(inputValidation())
            Questions.update(updateQuestion);
        if(errorMessage.toString().isEmpty()){
            Navigator.getInstance().closeSecondStage();
            Navigator.getInstance().goToQBankIndex();
            selectedTopics = new ArrayList<>();
        }
        errorMessage = new StringBuilder();
    }

    @FXML
    void btnResetFieldClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topicName = new ArrayList<>();
        className = new ArrayList<>();
        for(Topics t: topicsQuestionView){
            topicName.add(t.getTopicName());
        }
        topicName.add(0, "");
        ArrayList<String> topicNameUi = new ArrayList<>(topicName);
        topicNameUi.remove(0);
        choiceBoxSelectTopic.setItems(FXCollections.observableArrayList(topicNameUi));

        for(Classifications c: classQuestionView){
            className.add(c.getClassification());
        }
        className.add(0, "");
        ArrayList<String> classNameUi = new ArrayList<>(className);
        classNameUi.remove(0);
        choiceBoxSelectClass.setItems(FXCollections.observableArrayList(classNameUi));

        choiceBoxCorrectAns.setItems(FXCollections.observableArrayList(
                "Choice 1", "Choice 2", "Choice 3", "Choice 4"
        ));
    }

    public void initialize(Questions q){
        updateQuestion = q;
        ArrayList<Integer> listTopicIds = q.getForeignKeyTopicId();
        ArrayList<Topics> listTopics = Topics.findingTopics(listTopicIds);
        selectedTopics = listTopics;
        tableViewSelectedTopic.setItems(FXCollections.observableArrayList(listTopics));
        tableColSelectedTopicName.setCellValueFactory(
                (topic) -> {
                    return topic.getValue().getTopicNameProperty();
                }
        );
        btnAddTopic.disableProperty().set(true);
        btnRemoveTopic.disableProperty().set(true);
        tableViewSelectedTopic.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, newSelection) -> {
                    if(newSelection != null) btnRemoveTopic.disableProperty().set(false);
                }
        );
        choiceBoxSelectTopic.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, newSelection) -> {
                    if(newSelection != null) btnAddTopic.disableProperty().set(false);
                }
        );
        choiceBoxSelectClass.setValue(
                Classifications.searchClassificationById(
                        q.getForeignKeyClassificationId()
                )
        );
        txtAreaQStatement.setText(q.getQuestionStatement());
        txtxAreaQChoice1.setText(q.getChoice1());
        txtxAreaQChoice2.setText(q.getChoice2());
        txtxAreaQChoice3.setText(q.getChoice3());
        txtxAreaQChoice4.setText(q.getChoice4());
        choiceBoxCorrectAns.setValue(q.getCorrectAnswer());
    }

    private boolean inputValidation(){
        ObservableList<Topics> temp = tableViewSelectedTopic.getItems();
        if(temp.size() == 0) errorMessage.append("A question must be associated with a topic !\n");
        if(choiceBoxSelectClass.getValue() == null) errorMessage.append("A question must be associated with a classification !\n");
        if(txtAreaQStatement.getText() == null) errorMessage.append("A question must have a question statement !\n"); //TO DO: CHANGE "== NULL" TO .ISEMPTY()
        if(txtxAreaQChoice1.getText() == null
                || txtxAreaQChoice2.getText() == null || txtxAreaQChoice3.getText().isEmpty()
                || txtxAreaQChoice4.getText() == null) errorMessage.append("A question must have 4 answers !\n");
        if(choiceBoxCorrectAns.getValue() == null) errorMessage.append("You must select 1 correct answer !\n");

        if(!errorMessage.toString().isEmpty()){
            AlertUtil.generateErrorWindow("Update new question failed", "Update new question",
                    errorMessage.toString());
            return false;
        }
        return true;
    }

    @FXML
    void btnAddTopicClick(ActionEvent event) throws IOException {
        if(!selectedTopics.contains(topicsQuestionView.get(topicName.indexOf(choiceBoxSelectTopic.getValue()) - 1))){
            selectedTopics.add(topicsQuestionView.get(topicName.indexOf(choiceBoxSelectTopic.getValue()) - 1));
            Navigator.getInstance().closeSecondStage();
            Navigator.getInstance().goToQBankUpdate(this.generateTempQuestionObject());
        }else{
            AlertUtil.generateErrorWindow("Add new topic failed", "Add new topic",
                    "Topic has been added before !, please choose other topics");
        }
    }

    @FXML
    void btnRemoveTopicClick(ActionEvent event) throws IOException {
        Topics t = tableViewSelectedTopic.getSelectionModel().getSelectedItem();
        selectedTopics.remove(t);
        tableViewSelectedTopic.getItems().remove(t);
        Navigator.getInstance().closeSecondStage();
        Navigator.getInstance().goToQBankUpdate(this.generateTempQuestionObject());
    }

    private Questions generateTempQuestionObject(){
        Questions quest = new Questions();
        quest.setQuestionId(updateQuestion.getQuestionId());
        quest.setForeignKeyClassificationId(
                Classifications.searchClassificationByName(
                        choiceBoxSelectClass.getValue()
                )
        );
        quest.setQuestionStatement(txtAreaQStatement.getText());
        quest.setChoice1(txtxAreaQChoice1.getText());
        quest.setChoice2(txtxAreaQChoice2.getText());
        quest.setChoice3(txtxAreaQChoice3.getText());
        quest.setChoice4(txtxAreaQChoice4.getText());
        quest.setCorrectAnswer(choiceBoxCorrectAns.getValue());
        ArrayList<String> selectedTopicNames = new ArrayList<>();
        for(Topics topic: selectedTopics){
            selectedTopicNames.add(topic.getTopicName());
        }
        ArrayList<Integer> selectedTopicIds = Topics.findingTopicIds(selectedTopicNames);
        quest.setForeignKeyTopicId(selectedTopicIds);
        return quest;
    }

}
