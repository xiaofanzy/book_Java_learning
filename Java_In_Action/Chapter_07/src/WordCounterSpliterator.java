import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int cuuuentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 让字符串接受accept操作
     * @param action
     * @return
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(cuuuentChar++));    //处理当前字符
        return cuuuentChar < string.length();   // 如果还有字符处理，则返回true;
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - cuuuentChar;
        if (currentSize < 10){
            return null;    // 标识已处理的字段足够小，可以按照顺序处理
        }
        // 二分法
        for (int splitPos = currentSize /2 + cuuuentChar; splitPos < string.length(); splitPos++) {
            //将拆分位置前进到下一个空格；
            if (Character.isWhitespace(string.charAt(splitPos))){
                // 创建又给新的对象，解析开始到拆分位置的部分；
                WordCounterSpliterator splitrator = new WordCounterSpliterator(string.substring(cuuuentChar, splitPos));
                cuuuentChar = splitPos; // 将这个对象的起始位置设置为拆分位置；
                return splitrator;
            }
        }
        return  null;
    }

    // 这个就是总长度和当前遍历的位置的差
    @Override
    public long estimateSize() {
        return string.length() - cuuuentChar;
    }

    /**
     * ORDERED  按照顺序
     * SIZED 返回值是精确的
     * SUBSIZED 创建的Spliterator 对象有确切大小，
     * nonnull string中不能有为null的character
     * immutable 不能再添加character,因为string本生就是一个不可变类；
     * @return
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
