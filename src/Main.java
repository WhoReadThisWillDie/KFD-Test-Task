import StockExchange.Terminal;
import StockExchange.User;

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
                    terminal.getExchangeRates();
                    break;
                case 3:
                    System.out.printf("%nВведите валюту, которую хотите обменять: ");
                    String currencyFrom = in.next();
                    System.out.print("Введите валюту, которую хотите получить: ");
                    String currencyTo = in.next();
                    System.out.printf("Введите количество %s к обмену: ", currencyFrom);
                    float amountFrom = in.nextFloat();

                    terminal.exchange(currencyFrom, currencyTo, amountFrom);
                case 0:
                    break;
                default:
                    System.out.printf("Выбрана несуществующая опция. Пожалуйста, введите число от 0 до 3%n%n");
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