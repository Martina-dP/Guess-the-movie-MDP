import java.util.Scanner;

public class delPinoMartinaMain {

    Scanner input = new Scanner(System.in);
    delPinoMartinaGame game = new delPinoMartinaGame();
    delPinoMartinaPlayer player;
    delPinoMartinaRanking ranking = new delPinoMartinaRanking();

    public static void main(String[] args) {
        delPinoMartinaMain program = new delPinoMartinaMain();
        program.inicio();
    }

    public void inicio() {
        try {
            game.getRandomMovie();
            game.hiddenTitle();
            saveNickName();
            System.out.println("Bienvenido a Guess the movie" );
            Menu();

            checkRanking();
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo de películas: " + e.getMessage());
        }
    }

    public void Menu() {
        int opcion ;
        boolean end = false;

        while (!end && game.getTries() > 0) {

            System.out.println("Título actual: " + game.getGuessedTitle());
            System.out.println("Intentos: " + game.getTries());
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
                    oneLetter();
                    break;
                }
                case 2: {
                    guessedTitle();
                    end = true;
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
        String nickname = input.nextLine().trim();

        while (nickname.isEmpty()) {
            System.out.print("El nickname no puede estar vacío. Introduce un nickname: ");
            nickname = input.nextLine().trim();
        }

        if (player == null) {
            player = new delPinoMartinaPlayer(nickname, 0);
        }

        boolean existe = false;
        for (delPinoMartinaPlayer p : ranking.ranking) {
            if (p.getName().equals(nickname)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            ranking.addPlayerToRanking(player);
            System.out.println("Nickname guardado: " + player.getName());
        } else {
            System.out.println("Ese nickname ya está en el ranking. Por favor, elige otro.");
        }
    }

    public void oneLetter() {
        System.out.print("Introduce una letra: ");
        char letter = input.nextLine().toLowerCase().charAt(0);
        if (!Character.isAlphabetic(letter)) {
            System.out.print("El caracter introducido no es una letra del abecedario");
            System.out.print("Vuelve a introduce una letra: ");
            letter = input.nextLine().toLowerCase().charAt(0);
        }

        if (game.guessLetter(letter)) {
            player.addPoints(10);
        }
        else {
            player.addPoints(-10);
        }
    }

    public void guessedTitle() {
        System.out.print("Introduce el título completo: ");
        String title = input.nextLine();

        if (game.guessTitle(title)) {
            System.out.println("¡Correcto! Has adivinado el título.");
            System.out.println("Juego finalizado.");
            player.addPoints(20);
            System.out.println("Tus puntos finales: " + player.getPoints());
        } else {
            System.out.println("Incorrecto. Ese no es el título.");
            System.out.println("Juego finalizado, el titulo era: " + game.getMovieTitle());
            player.addPoints(-20);
            System.out.println("Tus puntos finales: " + player.getPoints());
        }

    }

    public void checkRanking() {
        System.out.println("Verificando si entras en el ranking...");

        if (ranking.ranking.size() < 5 || player.getPoints() > ranking.ranking.get(ranking.ranking.size() - 1).getPoints()) {
            boolean existe = false;
            for (delPinoMartinaPlayer p : ranking.ranking) {
                if (p.getName().equals(player.getName())) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                ranking.addPlayerToRanking(player);
                System.out.println("¡Felicidades! Has entrado en el ranking.");
            } else {
                System.out.println("¡Ya estás en el ranking!");
            }
        } else {
            System.out.println("No has entrado en el ranking. ¡Mejor suerte la próxima vez!");
        }

        ranking.displayRanking();
    }

}