import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectorsDemo {
    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {



        // 汇总
        Long howmanyDishes = Dish.menu.stream().collect(counting());

        howmanyDishes = Dish.menu.stream().count();

        // 比较卡路里大小的comparator对象
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        //传入对象
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(minBy(dishCaloriesComparator));
        // 一步到位
        mostCalorieDish = Dish.menu.stream().collect(minBy(Comparator.comparing(Dish::getCalories)));

        //汇总
        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));

        // 平均
        Double avgCalories = Dish.menu.stream().collect(averagingInt(Dish::getCalories));

        // 一次性操作 获取所有的值，包括最大最小值， 汇总平均值
        IntSummaryStatistics collect = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collect);
        System.out.println(collect.getMax());

        /**
         * 连接字符串
         */
        // 将所有菜肴名称连接起来
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
        //Dish.menu.stream().collect(joining())

        //reducing 起始值 转换函数， 汇总函数
        int totalCalorie = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        Dish.menu.stream().collect(reducing(0,Dish::getCalories,Integer::max));
        Dish.menu.stream().collect(reducing((d1,d2) -> d2.getCalories() > d2.getCalories() ? d1 : d2));

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        stream.reduce(new ArrayList<>(),(List<Integer> l,Integer e) -> {
            l.add(e);
            return l;
        },(List<Integer> l1,List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;
        });

        // 但是这一步可能有问题，就是get可能存在为空的情况。
        int totalCaloriess = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        // 最简单的方法
        int sum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();


        /**
         * 建议: 使用最专门化的一个，
         */
        String collect1 = Dish.menu.stream().map(Dish::getName).collect(joining());
        String collect2 = Dish.menu.stream().map(Dish::getName).collect(reducing("",String::concat));
        System.out.println(collect1);
        System.out.println(collect2);
        String collect3 = Dish.menu.stream().collect(reducing("", Dish::getName, String::concat));
        System.out.println(collect3);

        // 分类 Dish::getCalories函数，把结果作为映射值；
        Map<Integer, List<Dish>> collect4 = Dish.menu.stream().collect(groupingBy(Dish::getCalories));
        System.out.println(collect4);

        // 多级分组
        // 第一级分组通过type 分组，第二级分组通过CaloricLevel分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect5 = Dish.menu.stream().collect(groupingBy(Dish::getType, groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.FAT;
                    } else {
                        return CaloricLevel.NORMAL;
                    }
                }

        )));

        // 按照分类组，在进行汇总
        Map<Dish.Type, Long> collect6 = Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(collect5);
        System.out.println(collect6);

        Map<Dish.Type, Optional<Dish>> collect7 = Dish.menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(collect7); //{MEAT=Optional[pork], FISH=Optional[salmon], OTHER=Optional[pizza]}
        // 这里的optional没用，所以我们通过collectingAndThen方法去除
        Map<Dish.Type, Dish> collect8 = Dish.menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect8);

        Map<Dish.Type, Integer> collect9 = Dish.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(collect9);

        //还有一个方法就是通过mapping方法；
        // mapping 第一个参数是把元素做变换，第二个参数是将变换的参数再做操作；
        Map<Dish.Type, Set<CaloricLevel>> collect10 = Dish.menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toSet())));

        // 通过toCollection 更多的控制
        Map<Dish.Type, HashSet<CaloricLevel>> collect11 = Dish.menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toCollection(HashSet::new))));

        //分区 partitioningBy y / n
        Map<Boolean, List<Dish>> collect12 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(collect12);
        // 通过get方法可以获取
        List<Dish> dishes = collect12.get(true);

        // 分区的优势，是对于数据的在处理 第二个参数，是对参数的在处理
        Map<Boolean, Map<Dish.Type, List<Dish>>> collect13 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        Map<Boolean, Dish> collect14 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));

        // 将数字按照质数和非质数分区

        // 我们使用TOListCollector 类替代toList类是可以的，但是我们需要进行new 操作；
        List<Dish> collect15 = Dish.menu.stream().collect(new ToListCollector<Dish>());
        collect15 = Dish.menu.stream().collect(toList());

        // 我们可以通过直接提供参数的方式，但是这种方式吧，可读性差
        Dish.menu.stream().collect(ArrayList::new,List::add,List::addAll);


        // 比较收集器的性能；
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(fastest);

        /*IntStream.rangeClosed(2, 100).boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());
                        }},
                        (acc, candidate) -> {
                            acc.get( isPrime(acc.get(true), candidate) )
                                    .add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });*/

        IntStream.rangeClosed(2,100).boxed()
                .collect(
                        () -> new HashMap<Boolean,List<Integer>>(){
                            {
                                put(true,new ArrayList<Integer>());
                                put(false,new ArrayList<Integer>());
                            }
                        },
                        (acc,candidate) -> {
                            acc.get(isPrime(acc.get(true),candidate))
                                    .add(candidate);
                        },
                        (map1,map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map2.get(false).addAll(map2.get(false));
                        }

                );



    }


    public static <T> Collector<T,?,Long> counting(){
        return reducing(0L,e -> 1L,Long::sum);
    }

    /**
     * 质数
     * @param candidate
     * @return
     */
    public boolean isPrime(int candidate){
        return IntStream.range(2,candidate).noneMatch(i -> candidate % i == 0);
    }

    public Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(partitioningBy(ca -> isPrime(ca)));
    }


    /**
     * 收集器接口 : 生成一个累加器实例，供手机过程使用；
     */
    public <T> Supplier<List<T>> supplier(){
        return ArrayList::new;
    }

    /**
     * 规约操作，将规约结果 list 和 当前元素本省item 进行规约，知道规约结束；
     * @param <T>
     * @return
     */
    public <T> BiConsumer<List<T>,T> accumulator(){
        return (list,item) -> list.add(item);
    }

    /**
     * 对于各个子部分并行处理的时候，各个子部分的结果要进行合并，
     * @param <T>
     * @return
     */
    public <T> BinaryOperator<List<T>> combiner(){
        return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 当调用到最后一个函数的时候，使用finisher函数，将整个上面的list对象，
     * 转换为最终结果；
     * @param <T>
     * @return
     */
    public <T> Function<List<T>,List<T>> finisher(){
        return Function.identity();
    }

    // 真个逻辑是这样，一个标准流，在并行过程中，会拆分成子流，子流当进一步拆分为非的时候
    // 所有子流都进行并行处理，没每个子流应用规约算法，最后，施工combiner函数，将函数进行合并；
    // tips : 先Supplier 在 accumulator ，在combiner 各个流进行合并，最后进行 finisher 规约

    /**
     * UNORDERED : 规约结果不受到流中的项目遍历和顺序的影响；
     * CONCURRENT : accumulator函数可以从多个线程同时调用，且该收集器可以并行规约，如果没有标志为UNORDERED 拿他仅在用于无序数据源时才进行规约；
     * IDENTITY_FINISH: 表明完成器方法返回的函数是一个恒等函数，可以通过；累加器A 不检查转换为结果R是安全的；
     *
     */


    // 自定义构造器
    public static Map<Boolean,List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(new PrimeNumberCollector());
    }


    public static boolean isPrime(List<Integer> prime,int candidate){
        double candidateRoot = Math.sqrt((double) candidate);
        return takeWhile(prime,i -> i <= candidateRoot).stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item :
                list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

}
