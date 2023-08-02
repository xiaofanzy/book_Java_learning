import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProdectFactory {

    final static Map<String, Supplier<Product>> map = new HashMap<>();

    /*static {
        map.put("loan",Load::new);
    }*/

    public static void main(String[] args) {

        /*map.put("loan",Load::new);*/

    }
}

class Product{

    private String name;



}

class Load{

    private String name;
    public Load() {
    }

    public Load(String name) {
        this.name = name;
    }
}
