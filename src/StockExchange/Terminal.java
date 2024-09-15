package StockExchange;

import java.util.*;

public class Terminal extends Wallet {
    private final Map<String, Float> exchangeRates;
    private final User user;

    public Terminal(float rub, float usd, float eur, float usdt, float btc, User user) {
        super(rub, usd, eur, usdt, btc);

        exchangeRates = new HashMap<>();
        exchangeRates.put("USD/RUB", 90f);
        exchangeRates.put("EUR/RUB", 100f);
        exchangeRates.put("EUR/USD", 1.1f);
        exchangeRates.put("USD/USDT", 1f);
        exchangeRates.put("BTC/USD", 60000f);

        this.user = user;
    }

    public void exchange(String fromCurrency, String toCurrency, float amountFrom) {
        try {
            checkCurrency(fromCurrency);
            checkCurrency(toCurrency);

            boolean exchangeProcessed = false;
            for (Map.Entry<String, Float> entry : exchangeRates.entrySet()) {
                List<String> splittedKey = Arrays.stream(entry.getKey().split("/")).toList();
                if (splittedKey.contains(fromCurrency) && splittedKey.contains(toCurrency)) {
                    float amountTo;
                    if (splittedKey.getFirst().equals(fromCurrency)) {
                        amountTo = amountFrom * entry.getValue();
                    } else {
                        amountTo = amountFrom * (1 / entry.getValue());
                    }

                    if (balance.get(toCurrency) < amountTo) {
                        throw new IllegalArgumentException("На балансе терминала недостаточно средств");
                    }

                    chargeCurrency(toCurrency, amountTo);
                    depositCurrency(fromCurrency, amountFrom);
                    user.chargeCurrency(fromCurrency, amountFrom);
                    user.depositCurrency(toCurrency, amountTo);

                    exchangeProcessed = true;
                    break;
                }
            }

            if (!exchangeProcessed) {
                throw new IllegalArgumentException("Указана несуществующая валютная пара");
            }

            updateExchangeRates();
        } catch (IllegalArgumentException e) {
            System.out.printf("%s%n%n", e.getMessage());
        }
    }

    public void getExchangeRates() {
        System.out.printf("%nТекущий обменный курс всех пар:%n");
        exchangeRates.forEach((key, value) -> System.out.printf("%s: %s%n", key, value));
        System.out.println();
    }

    private void updateExchangeRates() {
        exchangeRates.forEach((key, exchangeRate) -> exchangeRates.put(key, exchangeRate * (0.95f + (1.05f - 0.95f) * new Random().nextFloat())));
    }

    private void checkCurrency(String currency) {
        if (!balance.containsKey(currency)) {
            throw new IllegalArgumentException("Данная валюта не найдена: " + currency);
        }
    }
}