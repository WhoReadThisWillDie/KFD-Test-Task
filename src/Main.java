import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final User user = new User(1000000, 0, 0, 0, 0);
    private static final Terminal terminal = new Terminal(10000f, 1000f, 1000f, 1000f, 1.5f, user);


    public static void main(String[] args) {
        int userSelection;
        do {
            printMenu();
            userSelection = in.nextInt();
            switch (userSelection) {
                case 1:
                    user.getBalance();
                    break;
                case 2:

                case 3:
                    System.out.println("Введите валютную пару и сумму в формате: Валюта1 / Валюта 2");
                case 0:
                    break;
                default:
                    System.out.printf("Невалидный ввод, пожалуйста, введите число от 0 до 3%n%n");
                    break;
            }
        } while (userSelection != 0);
    }

    public static void printMenu() {
        System.out.println("1. Посмотреть баланс");
        System.out.println("2. Посмотреть курсы валют");
        System.out.println("3. Совершить обмен");
        System.out.println("0. Выйти");
    }
}
