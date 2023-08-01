import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TransactionQuestion {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> collect = transactions.stream().filter(it -> it.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        //collect.stream().forEach(it -> System.out.println(it.getValue()));

       // 交易员都在哪些不同的城市工作过
        List<String> cityList = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity).distinct().collect(Collectors.toList());

        // 第二种方法
        cityList = transactions.stream().map(it -> it.getTrader().getCity()).distinct().collect(Collectors.toList());
        //cityList.stream().forEach(System.out::println);

        // 第三种方法 ，使用set
        Set<String> citySet = transactions.stream().map(it -> it.getTrader().getCity())
                .collect(Collectors.toSet());
        //citySet.stream().forEach(System.out::println);

        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream().filter(it -> "Cambridge".equals(it.getTrader().getCity()))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);

        //返回所有交易员的姓名字符串，按字母顺序排序
        String nameLong = transactions.stream().map(it -> it.getTrader().getName())
                .distinct().sorted().reduce("", String::concat);
        System.out.println(nameLong);
        // 另外一种方法
        nameLong = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().collect(Collectors.joining());
        System.out.println(nameLong);

        //有没有交易员是在米兰工作的
        Optional<Transaction> any = transactions.stream().filter(it -> "Milan".equals(it.getTrader().getCity()))
                .findAny();

        boolean anyMatch = transactions.stream().anyMatch(it -> it.getTrader().getCity().equals("Milan"));

        transactions.stream().filter(it -> it.getTrader().getCity().equals("Milan"))
                .map(Transaction::getValue).forEach(System.out::println);

        //所有交易中，最高的交易额是多少
        Optional<Integer> reduce = transactions.stream().map(Transaction::getValue).reduce(Integer::max);

        // 最小的交易数额
        int first = transactions.stream().map(Transaction::getValue).sorted().findFirst().get();
        System.out.println(first);

        // 或者
        transactions.stream().map(Transaction::getValue).reduce((t1, t2) -> t1 > t2 ? t1 : t2);

        transactions.stream().min(Comparator.comparing(Transaction::getValue));

        /**
         * 数值流
         */
        int sum = Dish.menu.stream().map(Dish::getCalories)
                .reduce(0, Integer::sum);

        // 我们可以转化，使用特定方式的流处理
        // 1. mapToInt mapToDouble mapToLong
        sum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();

        // 2. 回传
        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        // 使用boxed 将特殊流转换为原始流
        Stream<Integer> boxed = intStream.boxed();

        // 3. 默认值 OptionInt
        OptionalInt max = Dish.menu.stream().mapToInt(Dish::getCalories).max();
        int maxNum = max.orElse(1);

        // 3 .数值范围 range  包含起始值， 不包含结束值
        // rangeClose 包含起始值 ，包含结束值
        IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);
        evenNumbers = IntStream.rangeClosed(1,100).filter(n -> n % 2 == 0);




    }
}
