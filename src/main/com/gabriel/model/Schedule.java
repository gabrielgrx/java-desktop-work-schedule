package main.com.gabriel.model;

import javafx.beans.property.ObjectProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Person> person;
    private ObjectProperty<LocalDate> date;
}
