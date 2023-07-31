import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class PredicateClass {

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T s :
                list) {
            result.add(s);
        }
        return result;
    }

    public static void main(String[] args) {
        
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfString = Arrays.asList("1","2");
        List<String> nonEmpry = filter(listOfString, nonEmptyStringPredicate);

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        inventory.sort(Comparator.comparing(a -> a.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));


        //比较器复合
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        //比较器链
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        /**
         * 谓词复合
         */
        //negate


    }


}
