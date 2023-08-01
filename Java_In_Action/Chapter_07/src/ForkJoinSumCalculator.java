import java.util.concurrent.RecursiveTask;

//继承 RecursiveTask 创建可以用于分支/合并的框架任务
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /**
     * 求和的数组
     */
    private final long[] numbers;
    /**
     * 子任务处理的数组的起始和终止位置
     */
    private final int start;
    /**
     * 终止位置
     */
    private final int end;

    // 不再将任务分解成子任务的数组大小
    public static final long THRESHOLD = 10_000;

    /**
     * 公共构造函数用于创建主任务
     * @param numbers
     */
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    /**
     * 私有构造函数用于以递归方式为主创建子任务
     * @param numbers 需要处理的数量
     * @param start 开始执行的任务起始
     * @param end 结束执行的任务
     */
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        int length = end -start;    // 负责求和部分的大小
        if (length < THRESHOLD){    // 如果小于阈值，则按顺序计算结果
            return computeSequentially();
        }
        //为前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start,start + length / 2);
        // 利用另外一个子任务来为数组的前一半求和
        leftTask.fork();
        //创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务，可能会允许进一步递归划分
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        // 两个的结果求和
        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
