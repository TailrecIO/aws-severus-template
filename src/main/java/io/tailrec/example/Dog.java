package io.tailrec.example;

import java.util.Optional;
/**
 * @author Hussachai Puripunpinyo
 */
public class Dog {
    private String name;

    private Optional<String> color;

    public Dog(){}

    public Dog(String name, Optional<String> color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getColor() {
        return color;
    }

    public void setColor(Optional<String> color) {
        this.color = color;
    }
}


