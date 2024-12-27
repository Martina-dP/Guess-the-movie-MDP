import java.io.File;
import java.util.Scanner;

public class delPinoMartinaMain {

    Scanner input = new Scanner(System.in);
    String filePath = "./src/dataNamesMovies.txt";
    delPinoMartinaGame game = new delPinoMartinaGame();
    delPinoMartinaPlayer player  = new delPinoMartinaPlayer();

    public static void main(String[] args) {
        delPinoMartinaMain program = new delPinoMartinaMain();
        program.inicio();
    }

    public void inicio() {
        try {
            game.getRandomMovie();
            System.out.println("Bienvenido a Guess the movie" );
            Menu();
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo de películas: " + e.getMessage());
        }
    }

    public void Menu() {
        int opcion ;
        boolean end = false;

        while (!end) {

            System.out.println("Puntos actuales: " + player.getPoints());
            System.out.println("Título actual: " + game.getGuessedTitle());
            System.out.println("Letras incorrectas: " + game);
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1 = Adivinar una letra");
            System.out.println("2 = Adivinar el título de la película");
            System.out.println("3 = Salir");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.print("Introduce una letra: ");
                    String inputLetter = input.nextLine().toLowerCase();
                    game.guessLetter(inputLetter);
                    break;
                }
                case 2: {
                    System.out.println("Adivinar el titulo de la pelicula");
                    break;
                }
                case 3: {
                    System.out.println("Salir");
                    end = true;
                    break;
                }
                default: {
                    System.out.println("Opción no válida. Por favor, elige 1, 2, o 3");
                    break;
                }
            }
        }
    }


}