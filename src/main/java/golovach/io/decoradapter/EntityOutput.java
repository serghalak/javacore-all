package golovach.io.decoradapter;



import java.io.IOException;

public interface EntityOutput {

    void writePerson(Person person) throws IOException;
}
