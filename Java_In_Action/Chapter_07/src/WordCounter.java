import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounter{

    private final int counter;

    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c){
        if (Character.isWhitespace(c)){
            return lastSpace ? this : new WordCounter(counter,true);
        }else {
            return lastSpace ? new WordCounter(counter + 1,false) : this;
        }
    }

    public int getCounter(){
        return counter;
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter,wordCounter.lastSpace);
    }

    public static int countWords(Stream<Character> stream){
       return stream.reduce(new WordCounter(0,true),
               WordCounter::accumulate,WordCounter::combine).getCounter();
    }

}
