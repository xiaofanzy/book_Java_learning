import java.util.concurrent.*;

public class Futures {

    public static void main(String[] args) {
        // 使用future 以异步的方式执行一个耗时的操作

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }

            private Double doSomeLongComputation() {
                return  1.1;
            }
        });

        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        }catch (Exception ee){

        }


    }

    private static void doSomethingElse() {
    }
}
