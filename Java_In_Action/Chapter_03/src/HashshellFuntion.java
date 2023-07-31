import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class HashshellFuntion {

    public static void main(String[] args) {

        // Predicate
        List<String> listOfString = Arrays.asList("1");
        List<String> empty = filter(listOfString, (Predicate<String>) s -> s.isEmpty());

        //Consumer 消费者
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);
        forEach(numberList,s -> System.out.println(s));

        //Function 方法，肯定是拿一个，返回一个
        List<String> upperList = map(Arrays.asList("lambda", "in", "action"), String::toUpperCase);
        //如果想要异常
        Function<BufferedReader,String> f = (bufferedReader -> {
            try {
                return bufferedReader.readLine();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        });

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //类型推断
        filter(inventory,a -> "green".equals(a.getColor()));

        //使用局部变量
        // 我们可以使用自由变量，但是如果使用外部变量，外部变量必须定义成final;也就是不能修改的
        /**
         * why? 实力变量放在堆中，局部放在栈上，如果lambda可以直接访问局部变量，那么意味着lambda在线程运行过程中，
         *  当线程进行回收之后，lambda可能会继续访问该变量，而这个变量此时已经被回收了；
         *
         *  lambda 本质上其实是对值封闭，对变量其实是不封闭的；
         */
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);

        //方法引用
        inventory.sort(Comparator.comparing(Apple::getWeight));

        /**
         * 方法引用：
         * 1. 指向静态方法的引用 Integer::parseInt
         * 2.指向任意实例类型的方法引用 String::length
         * 3. 指向现有对象的实例方法引用 expensiveTransaction::getValue
         */










    }


    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T t :
                list) {
            if (p.test(t)){
                result.add(t);
            }
        }
        return  result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> p){
        List<T> result = new ArrayList<>();
        for (T t :
                list) {
            p.accept(t);
        }
    }

    public static <T,R> List<R>  map(List<T> list, Function<T,R> p){
        List<R> result = new ArrayList<>();
        for (T t :
                list) {
            result.add(p.apply(t));
        }
        return result;
    }

    /**
     * Lambda 表达式不支持检出异常，你可以自己定义检出异常的办法
     */
    @FunctionalInterface
    public interface BufferReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
}
