import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleDemo {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        // 筛选绿色苹果
        //使用Lambda表达式的时候；
        filterApples(inventory,AppleDemo::isGreenApple);
        filterApples(inventory,AppleDemo::isHeaveyApple);

        filterApples(inventory,(Apple a) -> "green".equals(a.getColor()));

        filterApples(inventory,(Apple a) -> a.getWeight() > 150);







    }

    //1 筛选绿色苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple:inventory){
            if ("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    //2 筛选大于150g的苹果
    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple:inventory){
            if (apple.getWeight() > 150){
                result.add(apple);
            }
        }
        return result;
    }

    // 3. 抽取方法
    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeaveyApple(Apple apple){
        return apple.getWeight() > 150;
    }

    public interface Predicate<T>{
        boolean test(T t);
    }

    static List<Apple> filterApples(List<Apple> inventory,Predicate<Apple> p){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (p.test(apple)){
                result.add(apple);
            }
        }
        return  result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
