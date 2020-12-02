package solidvolosatov.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderFile implements IReader {

    BufferedReader reader;

    public ReaderFile(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int read() {
        int number=0;
        try {
            number=Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }
}
