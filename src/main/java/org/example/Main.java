package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final String RESOURCES = "src/main/org.example";
    public static final Path PATH = Paths.get(RESOURCES + "persons.txt");

    public static void main(String[] args) {
      Person person = new Person(1,"Nicat",30,"nicat@gmail.com");
      ObjectMapper objectMapper = new ObjectMapper();
            try {
                String string=objectMapper.writeValueAsString(person);
                System.out.println("binci "  + string);
                Files.writeString(PATH,string);
                String read=Files.readString(PATH);
                System.out.println("ikinci" + read);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
