import java.util.*;

public class Terminal {
    private final Map<String, Float> balance;
    private final Map<String, Float> exchangeRates;
    private final User user;

    public Terminal(float rubles, float usd, float eur, float usdt, float btc, User user) {
        balance = new HashMap<>();
        balance.put("RUB", rubles);
        balance.put("USD", usd);
        balance.put("EUR", eur);
        balance.put("USDT", usdt);
        balance.put("BTC", btc);

        exchangeRates = new HashMap<>();
        exchangeRates.put("USDT / RUB", 90f);
        exchangeRates.put("EUR / RUB", 100f);
        exchangeRates.put("EUR / USD", 1.1f);
        exchangeRates.put("USD / USDT", 1f);
        exchangeRates.put("BTC / USD", 60000f);

        this.user = user;
    }

    public void exchange(String fromCurrency, String toCurrency, float amountFrom) {
        checkCurrency(fromCurrency);
        checkCurrency(toCurrency);

        exchangeRates.forEach((key, exchangeRate) -> {
            List<String> splittedKey = Arrays.stream(key.split(" / ")).toList();
            if (splittedKey.contains(fromCurrency) && splittedKey.contains(toCurrency)) {
                user.chargeCurrency(fromCurrency, amountFrom);

                if (splittedKey.getFirst().equals(fromCurrency)) {
                    user.depositCurrency(toCurrency, amountFrom * exchangeRate);
                }
                else {
                    user.depositCurrency(toCurrency, amountFrom * (1 / exchangeRate));
                }
            }
        });

        updateExchangeRates();
    }

    private void updateExchangeRates() {
        exchangeRates.forEach((key, exchangeRate) -> exchangeRates.put(key, exchangeRate  * (0.95f + (1.05f - 0.95f) * new Random().nextFloat())));
    }

    private void checkCurrency(String currency) {
        if (!balance.containsKey(currency)) {
            throw new IllegalArgumentException("Данная валюта не найдена");
        }
    }
}


