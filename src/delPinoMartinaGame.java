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

    public delPinoMartinaGame() {
        this.movieTitle = "";
        this.tries = 10;
        this.guessedTitle = new StringBuilder();
    }

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

    public void hiddenTitle() {
        guessedTitle.setLength(0);
        for (char c : movieTitle.toCharArray()) {
            if (Character.isLetter(c)) {
                guessedTitle.append('*');
            } else {
                guessedTitle.append(c);
            }
        }
    }

    public void guessLetter(String letter) {
        letter = letter.toLowerCase();
        if (correctas.contains(letter.charAt(0)) || incorrectas.contains(letter.charAt(0))){
            System.out.println("Esa letra ya la has dicho, intenta con otra!");
            return;
        }
        if (movieTitle.contains(letter)) {
            correctas.add(letter.charAt(0));

            System.out.println("Correcto");
        } else {
            incorrectas.add(letter.charAt(0));
            tries--;
            System.out.println("Incorrecto. Intentos restantes: " + tries);
        }
    }

}
