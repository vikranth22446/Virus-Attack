
public class Point implements Locatable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public double getDistance(Locatable other) {
        return Math.sqrt(Math.pow((Math.abs(getX()-other.getX())),2)+Math.pow((Math.abs(getY()-other.getY())),2));
    }
}
