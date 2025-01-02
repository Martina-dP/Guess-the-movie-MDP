import java.io.Serializable;

public class delPinoMartinaPlayer implements Serializable {

    private int points;
    private String name;

    public delPinoMartinaPlayer(String name, int points) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }
    public String getName() {
        return name;
    }

    public void addPoints(int points) {
        this.points += points;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "delPinoMartinaPlayer{" +
                "points=" + points +
                ", name='" + name + '\'' +
                '}';
    }
}
