package golovach.io.decoradapter;



import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EntityOutputStream implements EntityOutput {

    private DataOutput dataOutput;

    public EntityOutputStream(OutputStream os) {
        this.dataOutput = new DataOutputStream(os);
    }

    public EntityOutputStream() {    }

    @Override
    public void writePerson(Person person) throws IOException {

        dataOutput.writeUTF(person.getName());
        dataOutput.writeInt(person.getAge());

    }


}