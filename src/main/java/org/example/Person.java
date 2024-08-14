package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;
    public String Email;


    // Constructor
    public Person(int id, String name, int age, String Email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.Email = Email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    // toString method
    @Override
    public String toString() {
        return "id=" + "Person{" + id + ", name='" + name + '\'' + ", age=" + age + ", Email='" + Email + '\'' + '}';
    }

    // equals method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(name, person.name) && Objects.equals(Email, person.Email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, Email);
    }
}