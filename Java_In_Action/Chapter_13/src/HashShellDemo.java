import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashShellDemo {

    static List<List<Integer>> subsets(List<Integer> list){
        // 如果输入为空，则返回空子集，既空列表本身
        if (list.isEmpty()){
            List<List<Integer>>  ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        int first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subsets(rest);
        List subans2 = insertAll(first,subans);
        return concat(subans,subans2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans, List subans2) {
//        subans.addAll(subans2);
//        return subans;/**/

        // 这种就是函数时编程，没有副作用，不然上面的会影响subans的值；
        List<List<Integer>> r = new ArrayList<>(subans);
        r.addAll(subans2);
        return r;
    }

    private static List<List<Integer>> insertAll(int first, List<List<Integer>> lists) {
        List<List<Integer>>  result = new ArrayList<>();
        for (List<Integer> list :
                lists) {
            List<Integer> copyList = new ArrayList<>();
            //copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;

    }

    public static void main(String[] args) {

    }

    static long factorialTailRecursive(long n){
        return factorialHelper(1,n);
    }

    static long factorialHelper(long acc,long n){
        return  n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }
}
