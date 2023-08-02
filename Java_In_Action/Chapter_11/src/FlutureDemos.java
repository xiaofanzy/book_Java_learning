import java.util.concurrent.*;

public class FlutureDemos {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return dosomeLongComputation();
            }
        });
        dosomethingElse();
        
        try {
            Double rsult = future.get(1, TimeUnit.SECONDS);
        }catch (ExecutionException ee){
            System.out.println(ee);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void dosomethingElse() {
    }

    private static Double dosomeLongComputation() {
        return  1.1;
    }
}
