import java.util.Scanner;

public class delPinoMartinaMain {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        delPinoMartinaMain program = new delPinoMartinaMain();
        program.inicio();
    }

    public void inicio() {
    }

    public void Menu() {
        int opcion ;
        boolean end = false;

        System.out.println("Bienvenido a Guess the movie : " );

        while (true) {

            System.out.println(" Seleccione una opción: ");
            System.out.println("1 = Adivinar una letra");
            System.out.println("2 = Adivinar el titulo de la pelicula");
            System.out.println("3 = Salir");

            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("Adivinar una letra" );
                    break;
                }
                case 2: {
                    System.out.println("Adivinar el titulo de la pelicula");
                    break;
                }
                case 3: {
                    System.out.println("Salir");
                    end = true;
                    return;
                }
                default: {
                    System.out.println("Opción no válida. Por favor, elige 1, 2, o 3");
                    break;
                }
            }
        }
    }


}