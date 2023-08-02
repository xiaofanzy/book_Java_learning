import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Debugging {

    public static void main(String[] args) {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
    }

    @Test
    public void testComparing() throws Exception{
        Point p1 = new Point(5, 5);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        Assert.assertEquals(-1,result);
    }


    @Test
    public void testFilter() throws Exception{
        List<Integer> number = Arrays.asList(1, 2, 3, 4);
        List<Integer> even = filter(number, i -> i % 2 == 0);
        List<Integer> smallThanthree = filter(number, i -> i < 3);
        Assert.assertEquals(Arrays.asList(2,4),even);
        Assert.assertEquals(Arrays.asList(1,2),smallThanthree);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        list.forEach(i -> {
            if (p.test(i)){
                result.add(i);
            }
        });
        return result;
    }

    private static class Point{
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        private final static Comparator<Point> compareByXAndThenY = Comparator.comparing(Point::getX).thenComparing(Point::getY);
    }
}
