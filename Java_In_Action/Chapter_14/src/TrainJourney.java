public class TrainJourney {

    public TrainJourney(int price, TrainJourney onward) {
        this.price = price;
        this.onward = onward;
    }

    public int price;

    public TrainJourney onward;


}
