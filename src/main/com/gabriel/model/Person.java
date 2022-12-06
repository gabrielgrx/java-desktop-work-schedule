package main.com.gabriel.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty name;
    private StringProperty telephone;
    private StringProperty email;

    public Person() {
        this.name = null;
        this.telephone = null;
        this.email = null;
    }

    public Person(String name, String telephone, String email) {
        this.name = new SimpleStringProperty(name);
        this.telephone = new SimpleStringProperty(telephone);
        this.email = new SimpleStringProperty(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", telephone=" + telephone +
                ", email=" + email +
                '}';
    }
}
