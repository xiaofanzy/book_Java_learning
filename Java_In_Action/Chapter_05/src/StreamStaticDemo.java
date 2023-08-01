import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamStaticDemo {

    public static void main(String[] args) {
        List<Dish> vegetarianDishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());

        /**
         * 筛选和切片
         */
        //Predicate
        List<Dish> vegetarianMemu = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());

        //筛选不同元素 distinct
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        // 截取 limit
        List<Dish> dishes = Dish.menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(toList());

        //skip 跳过前多少条数据；不足n个则返回空
        dishes = Dish.menu.stream().filter(d -> d.getCalories() > 300).skip(4).collect(toList());
        dishes.forEach(System.out::println);


        /**
         * map
         */

        // 1. map
        List<Integer> distCalories = Dish.menu.stream().map(Dish::getCalories).collect(toList());
        List<String> words = Arrays.asList("Java 8", " in ", " action");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());

        // 2. flatmap 将所有的流元素形成一个整体的流，
        List<String> wordList = Arrays.asList("Hello", "world");
        // distinct 需要放在最后，不然因为map是独立的，所以没有起到筛选的作用
        List<String> collect = wordList.stream().map(it -> it.split(""))
                .flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println(collect);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> intList = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i,j})).collect(toList());
        intList.stream().forEach(i -> System.out.println(i[0] + " " + i[1]));
        List<int[]> threeNumbers = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());

        // 查找和匹配
        // 1. 检查谓词 至少有一个匹配
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The memu is vegetarian friendly!");
        }

        // 检查是否包含所有元素
        if (Dish.menu.stream().allMatch(Dish::isVegetarian)){
            System.out.println("The memu is vegetarian friendly!");
        }

        // 没有匹配元素
        if (Dish.menu.stream().noneMatch(Dish::isVegetarian)){
            System.out.println("The memu is vegetarian friendly!");
        }

        // 第一个匹配的元素
        Optional<Dish> anyVegetarians = Dish.menu.stream().filter(Dish::isVegetarian).findAny();
        Optional<Dish> firstVegetarians = Dish.menu.stream().filter(Dish::isVegetarian).findFirst();

        //isPresent representative anyVegetarians is alive
        anyVegetarians.isPresent();

        Dish.menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();

        //规约 reduce 初始值 总值
        // lambda 标识反复结合某个值，然后被规约成一个值
        numbers.stream().reduce(0,(a,b) -> a + b);
        numbers.stream().reduce(0,Integer::sum);
        // 如果没有初始值，就会返回一个Optional对象
        Optional<Integer> reduce = numbers.stream().reduce(Integer::sum);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        // 多少道菜呢？
        int count = Dish.menu.stream().map(d -> 1).reduce(0, Integer::sum);
        long counts = Dish.menu.stream().count();






    }
}
