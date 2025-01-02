import java.io.File;
import java.util.*;

public class delPinoMartinaGame {

    Scanner input = new Scanner(System.in);
    private final String FILE_PATH= "dataNamesMovies.txt";
    private final int TRIES = 10;
    private int triesLeft ;
    private String movieTitle;
    private StringBuilder guessedTitle;
    private ArrayList<Character> correctas = new ArrayList<>();
    private ArrayList<Character> incorrectas = new ArrayList<>();
    private ArrayList<Character> used = new ArrayList<>();

    public delPinoMartinaGame() {
        this.movieTitle = "";
        this.triesLeft = TRIES;
        this.guessedTitle = new StringBuilder();
    }

    public int getTries() {
        return triesLeft;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public StringBuilder getGuessedTitle() {
        return guessedTitle;
    }

    public ArrayList<Character> getIncorrectas() {
        return incorrectas;
    }

    public void getRandomMovie() {
        try {
            File file = new File(FILE_PATH);
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

    public boolean guessLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if (used.contains(letter)) {
            System.out.println("Ya intentaste esta letra. Intenta con otra.");
            return false;
        }
        used.add(letter);
        String movieTitleLower = movieTitle.toLowerCase();
        if (movieTitleLower.indexOf(letter) >= 0) {
            correctas.add(letter);
            updateLetterTitle(letter);
            System.out.println("¡Correcto!");
            triesLeft--;
            return true;
        } else {
            incorrectas.add(letter);
            triesLeft--;
            System.out.println("Incorrecto. La letra no está en el título.");
            return false;
        }
    }

    private void updateLetterTitle(char letter) {
        for (int i = 0; i < movieTitle.length(); i++) {
            if (movieTitle.charAt(i) == letter) {
                guessedTitle.setCharAt(i, letter);
            }
        }
    }

    public boolean guessTitle(String title) {
        String normalizedTitle = title.trim().toLowerCase();
        String normalizedMovieTitle = movieTitle.trim().toLowerCase();
        if (normalizedTitle.equals(normalizedMovieTitle)) {
            guessedTitle = new StringBuilder(movieTitle);
            triesLeft = 0;
            return true;
        } else {
            triesLeft = 0;
            return false;
        }
    }

}
