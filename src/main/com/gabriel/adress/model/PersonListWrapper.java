package main.com.gabriel.adress.model;

import main.com.gabriel.model.Person;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement (name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonListWrapper {

    @XmlElement(name = "person")
    private List<Person> persons;

    public PersonListWrapper() {
    }


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "PersonListWrapper{" +
                "persons=" + persons +
                '}';
    }
}
