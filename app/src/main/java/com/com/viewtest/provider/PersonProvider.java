package com.com.viewtest.provider;

import java.util.ArrayList;
import java.util.List;
import com.com.viewtest.model.Person;

public class PersonProvider {

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Person("이름"+i, "0101111"));
        }
        return persons;
    }

}
