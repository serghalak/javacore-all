package golovach.io.decoradapter;



import java.io.*;

public class EntityInputStream implements EntityInput {

    private DataInput dataInput;

    public EntityInputStream(InputStream is) {
        this.dataInput = new DataInputStream(is);
    }

    @Override
    public Person readPerson() throws IOException {
        String name=dataInput.readUTF();
        int age=dataInput.readInt();
        return new Person(name,age);
    }
}
