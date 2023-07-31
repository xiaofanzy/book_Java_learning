import java.util.Comparator;

public class simpleLambda {

    public static void main(String[] args) {

        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };

        Comparator<Apple> byWeights = (a1,a2) -> a1.getWeight().compareTo(a2.getWeight());
        byWeight = Comparator.comparing(Apple::getWeight);

        Runnable r1 = () -> System.out.println("hello world !");

        r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world 2");
            }
        };

        process(r1);
        process(() -> System.out.println("hello world 3!"));


    }

    public static void process(Runnable r){
        r.run();
    }







}
