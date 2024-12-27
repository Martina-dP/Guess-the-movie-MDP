import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class delPinoMartinaGame {

    Scanner input = new Scanner(System.in);
    String filePath = "./src/dataNamesMovies.txt";
    private int tries = 10;
    private String movieTitle;
    private StringBuilder guessedTitle;
    private ArrayList<Character> correctas = new ArrayList<>();
    private ArrayList<Character> incorrectas = new ArrayList<>();

    public int getTries() {
        return tries;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public StringBuilder getGuessedTitle() {
        return guessedTitle;
    }

    public void getRandomMovie() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            List<String> movies = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movies.add(line);
            }

            scanner.close();

            if (!movies.isEmpty()) {
                Random random = new Random();
                movieTitle = movies.get(random.nextInt(movies.size()));
            } else {
                System.err.println("El archivo está vacío o no contiene películas.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String hiddenTitle() {
        guessedTitle = new StringBuilder();

        for (char x : movieTitle.toCharArray()) {
            if (Character.isLetter(x)) {
                guessedTitle.append('*');
            } else {
                guessedTitle.append(x);
            }
        }
        return guessedTitle.toString();
    }

    public void guessLetter(String letter) {

    }

}
