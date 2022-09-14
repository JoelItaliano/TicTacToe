package tictactoe;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] tablero = {{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}};

        System.out.println("Game Start: ");
        imprimirTablero(tablero);
        gameStart(scanner, tablero);

    }

    public static void gameStart(Scanner scanner, String[][] tablero) {
        int contador = 1;
        boolean condicional = true;
        while (condicional) {
            System.out.println("Ingrese coordenadas dentro del rango 1-3: ej 2 2");
            String user = contador % 2 == 1 ? "X" : "O";
            System.out.println("Turno del usuario: " + user);
            movimientoUser(scanner, tablero, user);
            imprimirTablero(tablero);
            condicional = wins(tablero, user);
            if (!condicional) {
                System.out.println(user + " wins");
                System.out.println("Game Over");
            }
            if (condicional){
                condicional = draw(tablero);
            }

            contador++;
        }
    }

    /*
     * Metodo para imprimir el tablero
     * */
    public static void imprimirTablero(String[][] tablero) {
        lineaPunteada();
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.printf("|%n");
        }
        lineaPunteada();
    }

    /*
     * metodo que imprime una linea punteada
     * */
    public static void lineaPunteada() {
        System.out.println("---------");
    }

    /*
     * Funcion que transforma el ingreso en un arrays de chars para
     * comprobar que el usuario ingrese solo numeros
     * */
    public static void movimientoUser(Scanner scanner, String[][] tablero, String user) {
        boolean bucle = true;
        int posUno = 0;
        int posDos = 0;
        while (bucle) {
            String movimiento = scanner.nextLine();
            boolean condicional = contieneLetras(movimiento);
            if (condicional) {
                String[] coordenadas = movimiento.split(" ");
                if (coordenadas.length != 2) {
                    System.out.println("You must write only two numbers separated by a space!");
                    condicional = false;
                } else {
                    posUno = Integer.parseInt(coordenadas[0]);
                    posDos = Integer.parseInt(coordenadas[1]);
                    condicional = rango(posUno, posDos);
                }
            }
            if (condicional) {
                condicional = compruebaPosicion(posUno, posDos, tablero, user);
            }

            bucle = condicional == true ? false : true;
        }
    }

    /*
     * Funcion que transforma el ingreso en un arrays de chars para
     * comprobar que el usuario ingrese solo numeros
     * */
    public static boolean contieneLetras(String movimiento) {
        char[] array = movimiento.toCharArray();
        for (char num : array) {
            if (num == ' ') {
                continue;
            } else if (!(Character.isDigit(num))) {
                System.out.println("You should enter numbers! " + num + 1);
                return false;
            }
        }
        return true;
    }

    /*
     * funcion que comprueba que las coordenas sean entre uno y tres
     * */
    public static boolean rango(int posUno, int posDos) {
        if (posUno > 3 || posDos > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else {
            return true;
        }
    }

    /*
     * comprueba que los valores ingresados no este ocupado por otra letra
     * */
    public static boolean compruebaPosicion(int a, int b, String[][] tablero, String user) {
        a -= 1;
        b -= 1;
        if (tablero[a][b].equals("X") || tablero[a][b].equals("O")) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            tablero[a][b] = user;
            return true;
        }
    }

    /*
     * Metodo que comprueba si gano alguna de las letras
     */
    public static boolean wins(String[][] tablero, String user) {
        boolean userWins = true;
        int counterOne = 0;
        int counterTwo = 0;
        int counterThree = 0;
        int counterFour = 0;

        for (int i = 0; i < tablero.length; i++) {

            if (user.equals(tablero[i][i])) {
                counterOne++;
            }

            if (user.equals(tablero[i][tablero.length - i - 1])) {
                counterTwo++;
            }

            if (counterOne == 3 || counterTwo == 3) {
                userWins = false;
                break;
            }
            for (int j = 0; j < tablero[i].length; j++) {
                if (user.equals(tablero[i][j])) {
                    counterThree++;
                }

                if (user.equals(tablero[j][i])) {
                    counterFour++;
                }

                if (counterThree == 3 || counterFour == 3) {
                    userWins = false;
                    break;
                }
            }
            counterThree = 0;
            counterFour = 0;
        }
        return userWins;
    }

    /*
     * Metodo para comprobora que haya espacios disponibles, si no hay se considera empate
     */
    public static boolean draw(String[][] tablero) {
        for(int a = 0; a < tablero.length; a++) {
            for (int j = 0; j < tablero[a].length; j++) {
                if (tablero[a][j].equals("_")) {
                    return true;
                }
            }
        }
        System.out.println("Draw");
        return false;
    }

}




