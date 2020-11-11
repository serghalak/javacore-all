package golovach.io.decoradapter;

import java.io.*;

public class TestEntityIO {

    public static void main(String[] args) throws IOException {
        Person person=new Person("Serg Khalak",46);

        FileOutputStream fileOutputStream = new FileOutputStream("./person.data");
        EntityOutput entityOutput=new EntityOutputStream(fileOutputStream);
        entityOutput.writePerson(person);
        fileOutputStream.flush();
        fileOutputStream.close();

        System.out.println("Person: " + person + " was written!!!");
        System.out.println("read Person");

        FileInputStream fileInputStream = new FileInputStream("./person.data");
        EntityInput entityInput=new EntityInputStream(fileInputStream);
        Person person1 = entityInput.readPerson();
        fileInputStream.close();

        System.out.println("Person "+ person1+" was read");


    }
}
