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
package Utilities.PromptAlert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract interface AlertUtil {
    public static boolean generateWarningWindow(String warningTitle, String warningMessage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(warningTitle);
        alert.setHeaderText(warningMessage);
        Optional<ButtonType> confirmationResponse = alert.showAndWait();
        try{
            if(confirmationResponse.get() == ButtonType.OK) return true;
            else return false;
        }catch(Exception e){
            return false;
        }
    }

    public static void generateErrorWindow(String errorTitle, String operationType, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setHeaderText(operationType + " failed due to following reason:\n" + errorMessage);
        alert.showAndWait();
    }
}
