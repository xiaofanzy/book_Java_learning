import java.util.Optional;

public class Person {

    /**
     * Optional 的作用就是这里允许为空；
     */
    private Optional<Car> car;
    public Optional<Car> getCar(){return car;}

    private Integer age;

    public Integer getAge() {
        return age;
    }
}

class Car{
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance{
    private String name;

    public String getName() {
        return name;
    }
}

class Test{
    public String getCarInsuranceName(Person person){
        // person.getCar -> 可能会null -> null.getInsurance就会导致空指针
        return person.getCar().get().getInsurance().get().getName();
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge){
        return person.filter(p -> p.getAge() > minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unkonwn");
    }





}
