import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpliteratorDemo {

    public static void main(String[] args) {

        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Found " + countWordIteratively(SENTENCE) + " words");



        //函数时编程
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        System.out.println("Found " + WordCounter.countWords(stream) + " words");

        System.out.println("Found " + WordCounter.countWords(stream.parallel()) + " words");



    }

    public static int countWordIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for (char c :
                s.toCharArray()) {
            if (Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
