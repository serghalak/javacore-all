package javafoundations.model;

import java.time.LocalDate;

public class Pet {
    private String name;
    private LocalDate bod;

    public Pet() {
    }

    public Pet(String name, LocalDate bod) {
        this.name = name;
        this.bod = bod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBod() {
        return bod;
    }

    public void setBod(LocalDate bod) {
        this.bod = bod;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", bod=" + bod +
                '}';
    }
}
