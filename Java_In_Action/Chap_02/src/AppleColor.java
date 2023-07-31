import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleColor {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        filterAppleByColor(inventory,"green");
    }

    public static List<Apple> filterAppleByColor(List<Apple> inventory,String color){
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (apple.getColor().equals(color)){
                result.add(apple);
            }
        }

        return result;
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

class AppleHeaveyWeightPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

class AppleColorPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}