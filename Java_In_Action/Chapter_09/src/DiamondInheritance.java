public class DiamondInheritance {


}

class C implements A,B{

    public static void main(String[] args) {

        new C().hello();
    }

    @Override
    public void hello() {
        B.super.hello();
    }
}

interface A{
    default void hello(){
        System.out.println("hello from a");
    }
}


interface B{
    default void hello(){
        System.out.println("hello from b");
    }
}

