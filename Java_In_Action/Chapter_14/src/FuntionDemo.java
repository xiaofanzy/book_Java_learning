import java.util.Comparator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class FuntionDemo {
    public static void main(String[] args) {

        Function<String,Integer> strToInt = Integer::new;

        /**
         * 接受一个参数，返回另外一个参数
         */
        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight);

        // 柯里化

        // 我们可以将方法转换为调用返回自身的方法，这样每次偶都可以调用
        DoubleUnaryOperator operator = curriedConverter(9.0 / 5, 32);
        double v = operator.applyAsDouble(1000);




    }

    static DoubleUnaryOperator curriedConverter(double f,double b){
        return (double x) -> x * f + b;
    }



}
