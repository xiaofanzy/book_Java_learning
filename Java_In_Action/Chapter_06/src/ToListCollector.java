import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {

    // 创建集合操作的起始点
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    // 累计遍历过的项目，原位修改累加器
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }


    @Override
    public BinaryOperator<List<T>> combiner() {
        return ((list, list2) -> {
           list.addAll(list2);
           return list;
        });
    }

    // 恒等函数
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.UNORDERED));
    }
}
