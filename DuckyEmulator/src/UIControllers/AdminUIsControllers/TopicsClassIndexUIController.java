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
import Database.MainDB.Beans.Topics;
import UIs.Navigator;
import Utilities.PromptAlert.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopicsClassIndexUIController implements Initializable{

    @FXML
    private TableColumn<Topics, String> tableTopicNameCol;

    @FXML
    private TableColumn<Topics, String> tableTopicDesc;

    @FXML
    private Button btnTopicViewUpdate;

    @FXML
    private Button tableClassViewUpdate;

    @FXML
    private Button btnMenuHome;

    @FXML
    private Button btnMenuQuestBank;

    @FXML
    private Button btnTopicViewDelete;

    @FXML
    private Button tableClassViewAdd;

    @FXML
    private TableColumn<Classifications, String> tableClassificationCol;

    @FXML
    private TableView<Topics> tableTopicView;

    @FXML
    private TableView<Classifications> tableClassView;

    @FXML
    private Button btnMenuTopicClass;

    @FXML
    private Button btnTopicViewAdd;

    @FXML
    private Button tableClassViewDelete;

    @FXML
    private Pagination tableViewClassPagination;

    @FXML
    private Pagination tableViewTopicPageination;

    @FXML
    private TableColumn<Classifications, String> tableClassificationDesc;

    private static int topicsMaxPageNum;

    private static int classessMaxPageNum;

    private static int currentTopicPageIndex;

    private static int currentClassPageIndex;

    private static int topicsOffset;

    private static int classesOffset;

    public static int getTopicsOffset(){
        return topicsOffset;
    }

    public static int getTopicsMaxPageNum(){
        return topicsMaxPageNum;
    }

    public static void setTopicsOffset(int topicsOffset){
        TopicsClassIndexUIController.topicsOffset = topicsOffset;
    }

    public static void setTopicsMaxPageNum(int topicsMaxPageNum){
        TopicsClassIndexUIController.topicsMaxPageNum = topicsMaxPageNum;
    }

    public static int getClassesOffset(){
        return topicsOffset;
    }

    public static void setClassessOffset(int classesOffset){
        TopicsClassIndexUIController.classesOffset = classesOffset;
    }

    public static void setClassessMaxPageNum(int classessMaxPageNum){
        TopicsClassIndexUIController.classessMaxPageNum = classessMaxPageNum;
    }


    @FXML
    void btnTopicViewAddClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTopicIndexAdd();
    }

    @FXML
    void btnTopicViewUpdateClick(ActionEvent event) throws IOException {
        Topics selectedTop = tableTopicView.getSelectionModel().getSelectedItem();
        if(selectedTop == null){
            AlertUtil.generateErrorWindow("Update topic failed", "Update topic",
                    "A topic must be selected to perform this operation !");
        }
        else{
            currentTopicPageIndex = tableViewTopicPageination.getCurrentPageIndex();
            Navigator.getInstance().goToTopicIndexUpdate(selectedTop);
        }
    }

    @FXML
    void btnTopicViewDeleteClick(ActionEvent event) throws IOException {
        Topics selectedTop = tableTopicView.getSelectionModel().getSelectedItem();
        if(selectedTop == null){
            AlertUtil.generateErrorWindow("Delete topic failed", "Delete topic",
                    "A topic must be selected to perform this operation !");
        }
        else{
            if(AlertUtil.generateWarningWindow(
                    "Delete topic confirmation",
                    "Are you sure you want to delete the selected topic ?"
            )){
                if(Topics.delete(selectedTop)){
                    currentTopicPageIndex = tableViewTopicPageination.getCurrentPageIndex();
                    tableTopicView.getItems().remove(selectedTop);
                    Navigator.getInstance().goToTopicClassIndex();
                }
            }
        }
    }

    @FXML
    void tableClassViewAddClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToClassIndexAdd();
    }

    @FXML
    void tableClassViewUpdateClick(ActionEvent event) throws IOException {
        Classifications selectedClass = tableClassView.getSelectionModel().getSelectedItem();
        if(selectedClass == null){
            AlertUtil.generateErrorWindow("Update classification failed", "Update classification",
                    "A classification must be selected to perform this operation !");
        }
        else{
            currentClassPageIndex = tableViewClassPagination.getCurrentPageIndex();
            Navigator.getInstance().goToClassIndexUpdate(selectedClass);
        }
    }

    @FXML
    void tableClassViewDeleteClick(ActionEvent event) throws IOException {
        Classifications selectedClass = tableClassView.getSelectionModel().getSelectedItem();
        if(selectedClass == null){
            AlertUtil.generateErrorWindow("Delete topic failed", "Delete",
                    "A topic must be selected to perform this operation !");
        }
        else{
            if(AlertUtil.generateWarningWindow(
                    "Delete topic confirmation",
                    "Are you sure you want to delete the selected topic ?"
            )){
                if(Classifications.delete(selectedClass)){
                    currentClassPageIndex = tableViewClassPagination.getCurrentPageIndex();
                    tableClassView.getItems().remove(selectedClass);
                    Navigator.getInstance().goToTopicClassIndex();
                }
            }
        }
    }

    @FXML
    void btnMenuHomeClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToHome();
    }

    @FXML
    void btnMenuQuestBankClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToQBankIndex();
    }

    @FXML
    void btnMenuTopicClassClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToTopicClassIndex();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Topics.setPage();
        Classifications.setPage();
        tableViewTopicPageination.setPageCount(topicsMaxPageNum);
        tableViewClassPagination.setPageCount(classessMaxPageNum);
        tableViewTopicPageination.setCurrentPageIndex(currentTopicPageIndex);
        tableViewClassPagination.setCurrentPageIndex(currentClassPageIndex);
        this.topicInitialDeployment(currentTopicPageIndex);
        this.classInitialDeployment(currentClassPageIndex);
        btnTopicViewDelete.disableProperty().set(true);
        btnTopicViewUpdate.disableProperty().set(true);
        tableClassViewDelete.disableProperty().set(true);
        tableClassViewUpdate.disableProperty().set(true);
        tableViewTopicPageination.currentPageIndexProperty().addListener(
                (observable, oldIndex, newIndex) -> {
                    int pageIndex = newIndex.intValue();
                    currentTopicPageIndex = pageIndex;
                    this.topicViewInitialize(pageIndex);
                }
        );
        tableViewClassPagination.currentPageIndexProperty().addListener(
                (observable, oldIndex, newIndex) -> {
                    int pageIndex = newIndex.intValue();
                    currentClassPageIndex = pageIndex;
                    this.classViewInitalize(pageIndex);
                }
        );

        tableTopicView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelected, newSelected) -> {
                    if(newSelected != null){
                        btnTopicViewDelete.disableProperty().set(false);
                        btnTopicViewUpdate.disableProperty().set(false);

                    }
                }
        );

        tableClassView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelected, newSelected) -> {
                    if(newSelected != null){
                        tableClassViewDelete.disableProperty().set(false);
                        tableClassViewUpdate.disableProperty().set(false);
                    }
                }
        );
    }

    private void topicViewInitialize(int pageIndex){
        topicsOffset = pageIndex * 10;
        tableTopicView.setItems(Topics.select(topicsOffset));

        tableTopicNameCol.setCellValueFactory((topic) -> {
            return topic.getValue().getTopicNameProperty();
        });

        tableTopicDesc.setCellValueFactory((topic) -> {
            return topic.getValue().getTopicDescriptionProperty();
        });
    }

    private void classViewInitalize(int pageIndex){
        classesOffset = pageIndex * 10;
        tableClassView.setItems(Classifications.select(classesOffset));

        tableClassificationCol.setCellValueFactory((classification) -> {
            return classification.getValue().getClassificationProperty();
        });

        tableClassificationDesc.setCellValueFactory((classification) -> {
            return classification.getValue().getClassificationDescriptionProperty();
        });
    }

    private void topicInitialDeployment(int currentPageIndex){
        this.topicViewInitialize(currentPageIndex);
    }

    private void classInitialDeployment(int currentPageIndex){
        this.classViewInitalize(currentPageIndex);
    }
}