package main.com.gabriel.model;

import javafx.beans.property.ObjectProperty;
import main.com.gabriel.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Person> person;
    private ObjectProperty<LocalDate> date;

    public ArrayList<Person> getPerson() {
        return person;
    }
@XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}
