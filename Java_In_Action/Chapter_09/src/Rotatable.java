public interface Rotatable {

    void setRotationAngle(int angleInDegrees);

    int getRotationAngle();

    default void rotateBy(int angleInDegree){
        setRotationAngle((getRotationAngle() + angleInDegree) % 360);
    }
}

interface Moveable{
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    default void moveHorizontally(int distance){
        setX(getX() + distance);
    }

    default void moveVertically(int distance){
        setY(getY() + distance);
    }
}

interface Resizable{
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeigth(int heigth);
    void setAbsoluteSize(int width,int height);
    default void setRelativeSize(int wFactor,int hFactor){
        setAbsoluteSize(getWidth() / wFactor,getHeight() / hFactor);
    }

}

class Moster implements Rotatable,Moveable,Resizable{

    @Override
    public void setRotationAngle(int angleInDegrees) {

    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeigth(int heigth) {

    }

    @Override
    public void setAbsoluteSize(int width, int height) {

    }

    public static void main(String[] args) {
        Moster m = new Moster();
        m.rotateBy(180);
    }
}
