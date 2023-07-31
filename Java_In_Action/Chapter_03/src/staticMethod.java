import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class staticMethod {

    public static void main(String[] args) throws Exception {

        // 行为参数化
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        String fileStringLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());




    }

    /**
     * 行为参数化，就是将行为当作参数传输， b 就代表这个操作。操作作为参数传递；
     * @param b params
     * @return String
     * @throws IOException exception
     */
    public static String processFile(BufferReaderProcessor b) throws IOException{
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
            return b.process(br);
        }
    }

    /*public static String processFile() throws IOException {
        try (BufferedReader br =
                       new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }*/

    interface BufferReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
}
