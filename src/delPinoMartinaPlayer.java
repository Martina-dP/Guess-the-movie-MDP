public class delPinoMartinaPlayer {

    private int points;
    private String name;

    public delPinoMartinaPlayer() {
        this.points = 0;
        this.name = "";
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
