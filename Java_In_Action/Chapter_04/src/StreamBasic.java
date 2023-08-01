import javax.xml.bind.annotation.XmlEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String[] args) {
        List<Dish> lowCaloricDishes = new ArrayList<>();

        // before
        List<String> java7 = getLowCaloricDishesNamesInJava7(lowCaloricDishes);
        //after
        List<String> java8 = getLowCaloricDishesNamesInJava8(lowCaloricDishes);

        // 注意， 链条中的操作，在没有遇到短路操作的收，都没有调用menu菜单里面的数据
        List<String> threadHighCaloricDishNames = Dish.menu.stream().filter(d -> d.getCalories() > 150)
                .map(Dish::getName).limit(3).collect(toList());
        System.out.println(threadHighCaloricDishNames);

        List<String> names = Dish.menu.stream().filter(d -> {
                                                    System.out.println(d.getName());
                                                    return  d.getCalories() > 300;
                                                }).map(d -> {
                                                    System.out.println(d.getName());
                                                    return  d.getName();
                                                }).collect(toList());

        // 终端操作
        // foreach  stream -> void
        Dish.menu.stream().forEach(System.out::println);

        /**
         * 使用流：
         * 1. 一个数据源 ，执行查询
         * 2. 一个中间操作链，形成新的流水线
         * 3. 一个终端操作，生成结果；
         */






    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
