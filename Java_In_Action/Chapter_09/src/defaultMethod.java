import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public class defaultMethod {

    public static void main(String[] args) {

        // 默认方法：
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        //Comparator.naturalOrder() 返回一个Comparator 对象并对自然序列中的元素进行排序
        numbers.sort(Comparator.naturalOrder());


    }
}
