import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pythagorean_triple {

    public static void main(String[] args) {
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                ).filter(t -> t[2] % 1 == 0);

        // 创建流
        Stream<String> stream = Stream.of("java 8", "in", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<Object> emptyStream = Stream.empty();

        // 由数组创建流
        int[] numbers = {2,3,5,7,9};
        int sum = Arrays.stream(numbers).sum();

        // 由文件生成流
        long uniqueWords = 0;
        /*try {
            Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
            // flatMap 生成一个扁平流
            uniqueWords = lines.flatMap(l -> Arrays.stream(l.split(""))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // 生成无限流；
        // iterate 依次叠加生成无限流
        /*Stream.iterate(0,n -> n + 2)
                .limit(10).forEach(System.out::println);*/

        /*Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));*/

        // generate 生成无限流 选哟一个supplier
      /*  Stream.generate(Math::random).limit(5)
                .forEach(System.out::println);*/

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 0;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int oldCurrent = this.current;
                this.previous = this.current;
                this.current = oldCurrent + oldCurrent;
                return  oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);


    }
}
