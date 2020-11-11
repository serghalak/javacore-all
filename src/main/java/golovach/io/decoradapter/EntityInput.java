package golovach.io.decoradapter;


import java.io.IOException;

public interface EntityInput {
    Person readPerson() throws IOException;
}
