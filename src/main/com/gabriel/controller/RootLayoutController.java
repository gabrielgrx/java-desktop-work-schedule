package main.com.gabriel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import main.com.gabriel.Main;

import java.io.File;

public class RootLayoutController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleNew(){
        main.getPersonData().clear();
        main.setPersonFilePath(null);
    }

    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null){
            main.loadPersonDataFromFile(file);
        } else {
            System.out.println("é nulo");
        }
    }

    @FXML
    private void handleSave(){
        File personFile = main.getPersonFilePath();
        if (personFile != null){
            main.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        if (file != null){
            if (!file.getPath().endsWith(".xml")){
                file = new File(file.getPath() + ".xml");
            }
            main.savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Escala de Plantões");
        alert.setHeaderText("Informações sobre o autor");
        alert.setContentText("Projeto desenvolvido por: Gabriel Xavier \ngithub: gabrielgrx");
    }

    @FXML
    private void handleExit(){
        System.exit(0);
    }
}
