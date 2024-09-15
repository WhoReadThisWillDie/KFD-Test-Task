import java.util.HashMap;
import java.util.Map;

public class User {
    private final Map<String, Float> balance;

    public User(float rubles, float usd, float eur, float usdt, float btc) {
        balance = new HashMap<>();
        balance.put("RUB", rubles);
        balance.put("USD", usd);
        balance.put("EUR", eur);
        balance.put("USDT", usdt);
        balance.put("BTC", btc);
    }

    public void chargeCurrency(String currency, float amount) {
        float currentAmount = balance.get(currency);
        if (currentAmount < amount) {
            throw new IllegalArgumentException("Недостаточно средств на счёте");
        }

        balance.put(currency, currentAmount - amount);
    }

    public void depositCurrency(String currency, float amount) {
        balance.put(currency, amount + balance.get(currency));
    }

    public void getBalance() {
        System.out.println("Баланс вашего счёта:");
        balance.forEach((key, value) -> {
            System.out.printf("%s %s%n", value, key);
        });
        System.out.println();
    }
}
