package solidvolosatov.io;

import java.util.Scanner;

public class ReaderConsole implements IReader {

    private Scanner scanner;

    public ReaderConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int read() {
        return scanner.nextInt();
    }
}
