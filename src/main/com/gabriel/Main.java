package main.com.gabriel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.com.gabriel.adress.model.PersonListWrapper;
import main.com.gabriel.controller.PersonController;
import main.com.gabriel.controller.RootLayoutController;
import main.com.gabriel.model.Person;

import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.prefs.Preferences;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main() {
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Escala de Plantões");
        this.primaryStage.getIcons().add(new Image("main/com/gabriel/images/logo.jpg"));

        initRootLayout();

        showPersonOverview();
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("controller/Person.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonController personController = loader.getController();
            personController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);

        if (file != null) {
            prefs.put("filePath", file.getPath());

            primaryStage.setTitle("ScheduleApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            primaryStage.setTitle("ScheduleApp");
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            System.out.println(file.getName());
            System.out.println(personData);
            System.out.println(wrapper);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            System.out.println(wrapper);
            System.out.println("após adicionar lista wrapper");
            System.out.println(personData);

            setPersonFilePath(file);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro no arquivo salvo");
            alert.setContentText("Não foi possível carregar dados do arquivo: " + file.getPath());
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            m.marshal(wrapper, file);

            setPersonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao salvar o arquivo");
            alert.setContentText("Não foi possível salvar o arquivo: " + file.getPath());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("controller/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

        File file = getPersonFilePath();
        if (file != null){
            loadPersonDataFromFile(file);
        }
    }
}
