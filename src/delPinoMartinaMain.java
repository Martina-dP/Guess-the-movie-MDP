import java.io.File;
import java.time.LocalDate;
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
            game.hiddenTitle();
            System.out.println("Bienvenido a Guess the movie" );
            saveNickName();
            Menu();
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo de películas: " + e.getMessage());
        }
    }

    public void Menu() {
        int opcion ;
        boolean end = false;

        while (!end) {

            System.out.println("Título actual: " + game.getGuessedTitle());
            System.out.println("Puntos actuales: " + player.getPoints());
            System.out.println("Letras incorrectas: " + game.getIncorrectas());
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el título completo");
            System.out.println("[3] Salir");
            System.out.print("Elige una opción: ");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.print("Introduce una letra: ");
                    char letter = input.nextLine().toLowerCase().charAt(0);

                    if (game.guessLetter(letter)) {
                        player.addPoints(10);
                    }
                    else {
                        player.addPoints(-10);
                    }
                    break;
                }
                case 2: {
                    System.out.print("Introduce el título completo: ");
                    String title = input.nextLine();

//                    if (game.guessTitle(title)) {
//                        System.out.println("¡Correcto! Has adivinado el título.");
//                        player.addPoints(20);
//                        return true;
//                    } else {
//                        System.out.println("Incorrecto. Ese no es el título.");
//                        player.addPoints(-20);
//                        return true;
//                    }
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

    public void saveNickName() {
        System.out.print("Introduce un nickname para el juego: ");
        String name = input.nextLine().trim();
        player.setName(name);
        System.out.println("Nickname guardado: " + player.getName());
    }

}