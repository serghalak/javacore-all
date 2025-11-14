package javafoundations;

import javafoundations.model.Dog;
import javafoundations.model.Person;

import java.time.LocalDate;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Person tom = new Person("Tom", "Smith", LocalDate.of(1984, 6, 15));
        Person janet = new Person("Janet", "Jackson", LocalDate.of(1985, 12, 3));
        tom.setSpouse(janet);
        //janet.setSpouse(tom);
        Dog fido = new Dog("Fido", LocalDate.of(2018, 1, 1));
        tom.setPet(fido);
        fido.bark();
        System.out.println(tom);

    }
}
