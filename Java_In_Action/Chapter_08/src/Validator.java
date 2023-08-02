/**
 * 策略模式
 */
public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s){
        return strategy.execute(s);
    }


    public static void main(String[] args) {

        Validator numbericValidator = new Validator(new IsNumberic());
        boolean b1 = numbericValidator.validate("aaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbb");
        System.out.println(b1 + " " + b2);


        // 我们意识到这个ValidationStrategy 是一个函数式接口，
        numbericValidator = new Validator(it -> it.matches("[a-z]+"));
        lowerCaseValidator = new Validator(it -> it.matches("\\d+"));
        b1 = numbericValidator.validate("aaa");
        b2 = lowerCaseValidator.validate("bbb");
        System.out.println(b1 + " " + b2);

    }
}

@FunctionalInterface
interface ValidationStrategy{
    boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy{

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}

class IsNumberic implements  ValidationStrategy{

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}