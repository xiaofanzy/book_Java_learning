import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.function.Consumer;

public abstract class OnlineBanking {

    public static void main(String[] args) {
        Feed f = new Feed();
        /*f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")){
                System.out.println();
            }
        });*/
    }


    public void processCustomer(int id){

    }
}


interface ObServer{
    void notify(String tweet);
}

interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}

class NYTimes implements ObServer{

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}

class Guardian implements ObServer{

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}

class LeMonde implements ObServer{

    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}

class Feed implements Subject{

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        //observers.forEach(o -> o.notify(tweet));
    }
}

