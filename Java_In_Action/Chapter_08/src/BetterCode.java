import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BetterCode {

    public static void main(String[] args) {
        // 匿名内部类-》 lambda
        // 1 匿名内部类里面可以屏蔽外部变量，但是后者不行
        int a = 10;
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                int a = 2;
                System.out.println("Hello");
            }
        };

        Runnable r2 = () ->{
//            int a = 2;  // 变量已被声明
            System.out.println("Hello");
        };

        // pro2  当有方法重载的时候，有时候无法判断使用了哪个，我们这时候可以使用强制类型的方式
        // 当是匿名类的时候
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!");
            }
        });

        // 但是使用labmda的时候 ,但是貌似idea 智能提示了。
        doSomething((Task) () -> System.out.println("Danger!"));


        // 代码抽取成方法；
        // 操作被抽取成方法 Dish::getCaloricLevel
        Map<Dish.CaloricLevel, List<Dish>> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));

        // 我们使用collector 获取值，比采用底层规约操作获取要直观
        Dish.menu.stream().map(Dish::getCalories).reduce(0,Integer::sum);
        // 不如改成
        Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        //从命令式的数据处理切换到 Stream
        List<String> dishNames = new ArrayList<>();
        for(Dish dish: Dish.menu){
            if(dish.getCalories() > 300){
                dishNames.add(dish.getName());
            }
        }

        // 可以转换为
        int collect1 = Dish.menu.stream().filter(d -> d.getCalories() > 300)
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect1);
        int integer = Dish.menu.stream().filter(d -> d.getCalories() > 300)
                .map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(integer);

        //
        //Logger logger = new Logger();
//        logger.log(Level.FINER,"Problem");

        // 使用函数时编程，调用重载方法即可；
       // public void log(Level level, Supplier<String> msgSupplier)
        //logger.log(Level.FINER, () -> "Problem");

        // 环绕执行


    }

    interface Task{
        public void execute();
    }

    public static void doSomething(Runnable r){r.run();}
    public static void doSomething(Task t){t.execute();}


}
