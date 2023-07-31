import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleColorAndWeight {

    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeaverApple(Apple apple){
        return apple.getWeight() > 150;
    }

    static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple :
                inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public interface Runnable{
        public void run();
    }


    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        filterApple(inventory,AppleColorAndWeight::isGreenApple);
        // 或者
        filterApple(inventory,(Apple apple) -> apple.getWeight() > 150);

        List<Apple> apples = inventory.stream().filter(a -> a.getWeight() > 150).collect(Collectors.toList());

        List<Apple> heaverApple = inventory.stream().filter(a -> a.getWeight() > 150).collect(Collectors.toList());
        List<Apple> pallelHeaverApple = inventory.parallelStream().filter(a -> a.getWeight() > 150).collect(Collectors.toList());

        inventory.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //Runnable
        Thread t = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

        //等同于
        Thread thread = new Thread(() -> System.out.println("hello world"));



    }
}
