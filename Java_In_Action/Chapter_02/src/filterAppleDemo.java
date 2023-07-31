import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class filterAppleDemo {

    //策略 1 筛选绿苹果
    public static List<Apple> filterGreenApple(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return  result;
    }

    // 策略 2 抽取颜色参数
    public static List<Apple> filterAppleByColor(List<Apple> inventory,String color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    //策略三, 多种属性筛选
    public static List<Apple> filterApple(List<Apple> inventory,String color,int weigth,boolean flag){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (flag && apple.getColor().equals(color) || !flag && apple.getWeight().equals(weigth))
            result.add(apple);
        }

        return result;
    }

    // 策略4 抽取属性，
    public interface ApplePredicate{
        boolean test(Apple apple);
    }

    public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void filterApple(List<Apple> inventory, ApplePredicate applePredicate) {

    }



    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples = filterAppleByColor(inventory, "green");

        //策略3 但是这个flase是什么意思？？
        List<Apple> apples = filterApple(inventory, "red", 150, false);


        // 策略5 使用匿名内部类
        filterApple(inventory,new ApplePredicate(){

            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        //策略 6  使用lambda表达式
        filterApple(inventory,a -> "green".equals(a.getColor()));

        //策略7 List 抽象化
        filter(inventory,(Apple a) -> "green".equals(a.getColor()));

        // 这样我们也可以抽取其余的
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        filter(numbers,(Integer i) -> i % 2 == 0);
    }

    // 策略7
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e :
                list) {
            if (p.test(e)){
                result.add(e);
            }
        }
        return result;
    }




}


class AppleHeaverWeightPredicate implements filterAppleDemo.ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

class AppleGreenColorPredicate implements filterAppleDemo.ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}