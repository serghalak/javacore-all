package bobo.tdd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadWholeFile {

    public static String readWholeFile(String fileName){
        Path path = createPathFromFileName(fileName);
        try(Stream<String>fileLinesStream=openFileLinesStream(path)){
            return fileLinesStream.collect(Collectors.joining("\n"));
        }
    }

    private static Stream<String> openFileLinesStream(Path filePath){

        try {
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new FileReaderException("Cannot create stream of file lines!", e);
        }

    }

    private static Path createPathFromFileName(String fileName){
        Objects.requireNonNull(fileName);
        URL fileUrl = FileReader.class.getClassLoader().getResource(fileName);
        try{
            return Paths.get(fileUrl.toURI());
        }catch(URISyntaxException e){
            throw new FileReaderException("Invalid file URL", e);
        }
    }
}
