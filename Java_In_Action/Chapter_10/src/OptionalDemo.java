import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;

import java.util.Optional;
import java.util.Properties;

public class OptionalDemo {

    public static void main(String[] args) {

        // 创建对象
        // 1. 声明空对象
        Optional<Car> optCar = Optional.empty();
        // 2. 依据一个非空值创建 Optional
        Car car = new Car();
        // 这里如果car为null,立马就会报错，而不是使用的时候报错；
        Optional<Car> optcars = Optional.of(car);

        // 3.可接受null的Optional
        Optional<Car> car1 = Optional.ofNullable(car);

        // 使用 map 从 Optional 对象中提取和转换值
        Insurance instance = new Insurance();
        Optional<Insurance> optInsurance = Optional.of(instance);
        // map 和流的stream一致
        Optional<String> name = optInsurance.map(Insurance::getName);

        //使用 flatMap 链接 Optional 对象
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        String names = optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        // get  有值的时候调用，没有值则抛出异常
        // orElse() 如果没有值，则使用这个值；
        // orElseGet 如果没有值，则调用什么方法；
        // orElseThrow 遭遇null 抛出异常，异常自己定义
        // ifPresent 如果存在，则执行传入的方法；


        // filter 如果filter里面内容不存在，则返回一个空的Optional对象；
        optInsurance.filter(insurance -> "CambridgeInsurance".equals(instance.getName()))
                .ifPresent(System.out::println);

        // 异常

        // 对于基础类型的optional 不推荐使用




    }

    public int readDuration(Properties props,String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalDemo::stringtoInt)
                .filter(o -> o > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringtoInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();    // 如果为空，则返回一个空流；
        }
    }

    public String getCarInsuranceName(Optional<Person> person){
        // 为什么使用flapmap 因为 如果使用map -> Optional<Optional<Car>>  --》 而 getInsurance -》
        // 的对象是 Car 而不是Optional<Car>
        // flap 简单来说就是把一个空流和一个car流合并，
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unkonwm");
    }

    /**
     * 神奇的方法，使用类似三元运算符的方式操作
     * @return
     */

    public Optional<Object> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car){
        /**
         * 解释一下逻辑：
         * 1. 如果person为空，则不会执行括号里面的语句，返回一个空的Optional对象
         * 2. 如果person不为空，则会执行括号里面的语句，那么对于括号里面也是通过flatMap 判断是否为空
         *      如果不为空，则执行findCheapestInsurance 操作，这样下来，就是把person  car 都为空的情况摘除了；
         */
        return person.flatMap(p -> car.flatMap(c -> findCheapestInsurance(p,c)));
    }

    private Optional<Object> findCheapestInsurance(Person p, Car c) {
        return Optional.empty();
    }
}
