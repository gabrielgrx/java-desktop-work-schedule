package main.com.gabriel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.com.gabriel.Main;
import main.com.gabriel.model.Person;
import main.com.gabriel.util.TelephoneNumberUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonController implements Initializable {

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonInsert;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> emailColumn;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> telephoneColumn;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField telephoneTextField;

    private Main main;

    public PersonController() {
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            nameTextField.setText(person.getName());
            telephoneTextField.setText(person.getTelephone());
            emailTextField.setText(person.getEmail());
        } else {
            clearTextFields();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        telephoneColumn.setCellValueFactory(
                cellData -> cellData.getValue().telephoneProperty());
        emailColumn.setCellValueFactory(
                cellData -> cellData.getValue().emailProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(Main main) {
        this.main = main;
        personTable.setItems(main.getPersonData());
    }


    public void handeButtonAction(ActionEvent actionEvent) {
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            buttonDelete.setDisable(false);
        }
        clearTextFields();
    }

    @FXML
    public void handleInsetPerson() {
        if (!nameTextField.getText().equals("")) {
            String telephoneNumber = TelephoneNumberUtil.formatTelephone(telephoneTextField.getText());
            Person newPerson = new Person(
                    nameTextField.getText(), telephoneNumber, emailTextField.getText());
            ;
            personTable.getItems().add(newPerson);
        } else {
            buttonInsert.setDisable(false);
        }
        clearTextFields();
    }

    @FXML
    public void handleUpdatePerson() {
        Person selectedItem = personTable.getSelectionModel().getSelectedItem();
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedItem != null) {
            System.out.println(selectedIndex);
            Person updatePerson = new Person(
                    nameTextField.getText(), telephoneTextField.getText(), emailTextField.getText());
            personTable.getItems().remove(selectedItem);
            personTable.getItems().add(updatePerson);
            System.out.println(selectedIndex);
        }
        buttonInsert.setDisable(false);
        clearTextFields();
    }

    private void clearTextFields() {
        nameTextField.setText("");
        telephoneTextField.setText("");
        emailTextField.setText("");
    }
}

