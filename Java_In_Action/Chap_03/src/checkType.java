import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class checkType {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.stream().filter((Apple a) -> a.getWeight() > 150);

        List<String> list = Arrays.asList();
        Predicate<String> p = s -> list.add(s);

        /*这个很神奇的一点是 如果返回值是void 那么函数主体是一个表达式，尽然可以运行*/
        Consumer<String> b = s -> list.add(s);

        Runnable r = () -> {
            System.out.println("tricky example!");
        };

        int portNumber = 1337;
        Runnable r1 = () -> System.out.println(portNumber);
        //但是如果使用局部变量，这个变量就不允许修改了

        //方法引用
        inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));

        List<String> str = Arrays.asList("a", "b");
        str.sort(String::compareToIgnoreCase);

        Function<String,Integer> stringToIntgeer = Integer::parseInt;

        BiPredicate<List<String>,String> contains = List::contains;

        /*Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();*/

        Function<Integer,Apple> c2 = Apple::new;
        Apple a2 = c2.apply(10);

        List<Integer> weights = Arrays.asList(1, 2, 3, 4, 5);
        List<Apple> apples = map(weights,Apple::new);

    }

    public static List<Apple> map(List<Integer> list,Function<Integer,Apple> f){
        List<Apple> result = new ArrayList<>();
        for (Integer weight :
                list) {
            result.add(f.apply(weight));
        }
        return result;
    }


}
