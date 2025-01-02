import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class delPinoMartinaRanking {
   private String ranking_file = "ranking.dat";
    ArrayList<delPinoMartinaPlayer> ranking;

    public delPinoMartinaRanking() {
        ranking = new ArrayList<>();
        loadRanking();
    }

    public String getRanking_file() {
        return ranking_file;
    }

    public void setRanking_file(String ranking_file) {
        this.ranking_file = ranking_file;
    }

    public void loadRanking() {
        File file = new File(ranking_file);
        if (file.length() == 0) {
            System.out.println("Archivo vacío, inicializando ranking vacío.");
            ranking = new ArrayList<>();
            return;
        }

        try {
            FileInputStream fileIn = new FileInputStream(ranking_file);
            ObjectInputStream dataRanking = new ObjectInputStream(fileIn);

            ranking = (ArrayList<delPinoMartinaPlayer>) dataRanking.readObject();

            dataRanking.close();
            fileIn.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar el ranking: " + e.getMessage(), e);
        }
    }

    public void saveRanking() {
        try {
            FileOutputStream fileOut = new FileOutputStream(ranking_file);
            ObjectOutputStream dataOut = new ObjectOutputStream(fileOut);
            dataOut.writeObject(ranking);
            dataOut.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el ranking: " + e.getMessage(), e);
        }
    }

    public void addPlayerToRanking(delPinoMartinaPlayer player) {
        if (ranking.stream().noneMatch(p -> p.getName().equals(player.getName()))) {
            ranking.add(player);

            ranking.sort(Comparator.comparingInt(delPinoMartinaPlayer::getPoints).reversed());

            if (ranking.size() > 5) {
                ranking.remove(ranking.size() - 1);
            }
            saveRanking();
        } else {
            System.out.println("El nickname ya existe en el ranking.");
        }
    }

    public void displayRanking() {
        System.out.println("Ranking de puntuaciones:");
        for (int i = 0; i < ranking.size(); i++) {
            delPinoMartinaPlayer player = ranking.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints() + " puntos");
        }
    }
}

