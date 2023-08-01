import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStream {

    public static void main(String[] args) {
        /*System.out.println("interativeSum sum done in:" +
              measureSumPerf(ParallelStream::interativeSum,10_000_000) + " msecs"  );
*/
        System.out.println("sideEffectParallelSum sum done in:" +
                measureSumPerf(ParallelStream::sideEffectParallelSum,10_000_000) + " msecs"  );


        /**
         * 高效使用并行流
         * 1. 测量，看使用时长
         * 2. 装箱拆箱成本，使用特殊流来规避
         * 3. 操作本身的影响， Limit 和 findFirst 这种依赖于顺序的操作；代价非常大；截取前n个数据采用无序流比采用有序流影响大
         * 4. N * Q  = 总数  * 通过流水线的大致处理成本；
         * 5. 数据量少，不建议考虑并行，因为并行还有开销；
         * 6. 考虑六分解的数据结构是否易于分解；arraylist 拆分效率比Linklist高得多，前者不用遍历，后者可以平均拆分；
         * 7. 流自身的特单，都会改变分解过程的性能；
         * 8. 终端合并操作的代价是大还是晓；
         * 可分解性：
         * ArrayList > HashSet = TreeSet > LinkedList = Stream.iterate > IntStream.range（因为有装箱拆箱）
         */

    }

    public static long sequentialSum(long n){
        /*return Stream.iterate(1L,i -> i + 1)
                .limit(n).reduce(0L,Long::sum);*/

        // 改成并行方法
        /*return Stream.iterate(1L,i -> i + 1)
                .limit(n).parallel().reduce(0L,Long::sum);*/

        // 返回成顺序流  开销增大了 继续换方法
       /* return Stream.iterate(1L,i -> i + 1)
                // 注意到这一步， sequential 和 parallel 谁最后调用谁影响，所以sequential 可以不调用；
                .sequential().limit(n).parallel()
                .reduce(0L,Long::sum);*/

       // 更加针对性的方法
        return LongStream.rangeClosed(1,n).parallel()
                .reduce(0L,Long::sum);
    }

    public static long interativeSum(long n){
        long sum = 0L;
        for (long i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }

    public static long measureSumPerf(Function<Long,Long> adder,long n){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long durration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("result : " + sum);
            if (durration < fastest) fastest = durration;
        }
        return fastest;
    }

    /**
     * 这种方式有点问题，第一个是 Accumulator 这种方法就是顺序执行的，第二个就是total都会出现数据竞争的；
     * 而且最大的问题是 MD出错了，结果都不是500050000
     * @param n
     * @return
     */
    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }

}


