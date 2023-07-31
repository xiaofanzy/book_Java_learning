import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;

public class FilesDemo {

    public static void main(String[] args) throws IOException {
        //String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());




    }

    @FunctionalInterface
    public interface BufferReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }

    public static String processFile(BufferReaderProcessor p) throws IOException{
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    /*private static String processFile() throws IOException{
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }

    }*/
}
