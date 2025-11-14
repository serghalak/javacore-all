package javafoundations.model;

import java.time.LocalDate;

public class Dog extends Pet {
    public void bark() {
        System.out.println("Ruff, ruff");
    }

    public Dog() {
    }

    public Dog(String name, LocalDate bod) {
        super(name, bod);
    }
}
