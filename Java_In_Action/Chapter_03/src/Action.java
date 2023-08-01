import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Action {

    public static void main(String[] args) {

        // 实战
        //策略2 匿名内部类
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });


        //策略3
        inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //策略4 方法引用
        inventory.sort(Comparator.comparing(Apple::getWeight));

        // 复合方法
        //比较器复合 Comparator
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        // 逆序
        Comparator<Apple> reversed = c.reversed();

        // 比较器链
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor);


        // 2. 谓词复合 Predicate
        List<Apple> redApples = filter(inventory, i -> "red".equals(i.getColor()));
        // 谓词
        Predicate<Apple> redApple = (Apple a) -> "red".equals(a.getColor());
        // ！
        Predicate<Apple> negate = redApple.negate();

        // &&
        Predicate<Apple> applePredicate = redApple.and(apple -> apple.getWeight() > 150);

        // ||
        Predicate<Apple> applePredicate1 = redApple.or(apple -> apple.getWeight() > 150);

        // 3. 函数复合 Function
        Function<Integer,Integer> f = x -> x + 1;
        Function<Integer,Integer> g = x -> x * 2;
        // andThen 先执行前面的，在执行后面的 g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);

        // compose  f(g(x))
        Function<Integer, Integer> h1 = f.compose(g);
        int integer = h1.apply(1);
        System.out.println(result+ "-" + integer);




    }

    public static <T> List<T> filter(List<T> t, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T test:
                t) {
            if (p.test(test)){
                result.add(test);
            }
        }
        return result;
    }


}


//策略1
class AppleComparator implements Comparator<Apple>{

    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}


