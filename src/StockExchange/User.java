package StockExchange;

public class User extends Wallet{

    public User(float rub, float usd, float eur, float usdt, float btc) {
        super(rub, usd, eur, usdt, btc);
    }

    public void getBalance() {
        System.out.printf("%nБаланс вашего счёта:%n");
        balance.forEach((key, value) -> System.out.printf("%s %s%n", value, key));
        System.out.println();
    }
}