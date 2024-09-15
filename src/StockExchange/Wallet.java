package StockExchange;

import java.util.HashMap;
import java.util.Map;

public abstract class Wallet {
    protected final Map<String, Float> balance;

    public Wallet(float rub, float usd, float eur, float usdt, float btc) {
        balance = new HashMap<>();
        balance.put("RUB", rub);
        balance.put("USD", usd);
        balance.put("EUR", eur);
        balance.put("USDT", usdt);
        balance.put("BTC", btc);
    }

    protected void chargeCurrency(String currency, float amount) {
        float currentAmount = balance.get(currency);
        if (currentAmount < amount) {
            throw new IllegalArgumentException("Недостаточно средств на счёту");
        }

        balance.put(currency, currentAmount - amount);
    }

    protected void depositCurrency(String currency, float amount) {
        balance.put(currency, amount + balance.get(currency));
    }
}