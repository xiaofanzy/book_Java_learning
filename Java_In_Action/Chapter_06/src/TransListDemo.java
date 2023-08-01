import java.util.*;
import java.util.stream.Collectors;


public class TransListDemo {

    public static List<Transaction> transactions = Arrays.asList(new Transaction(Currency.getInstance("EUR"),1500.0),
            new Transaction(Currency.getInstance("USD"),2300.0),
            new Transaction(Currency.getInstance("GBP"),9900.0),
            new Transaction(Currency.getInstance("EUR"),1100.0),
            new Transaction(Currency.getInstance("JPY"),7800.0),
            new Transaction(Currency.getInstance("CHF"),6700.0),
            new Transaction(Currency.getInstance("EUR"),5600.0),
            new Transaction(Currency.getInstance("USD"),4500.0),
            new Transaction(Currency.getInstance("CHF"),3400.0),
            new Transaction(Currency.getInstance("GBP"),3200.0),
            new Transaction(Currency.getInstance("USD"),4600.0),
            new Transaction(Currency.getInstance("JPY"),5700.0),
            new Transaction(Currency.getInstance("EUR"),6800.0));

    public static void main(String[] args) {
        // 以前的方法
        Map<Currency, List<Transaction>> transactionByCurrencies = new HashMap<>();

        for (Transaction t:transactions
             ) {
            Currency currency = t.getCurrency();
            List<Transaction> transactionsForCurrency = transactionByCurrencies.get(currency);
            if (transactionsForCurrency == null){
                transactionsForCurrency = new ArrayList<>();
                transactionByCurrencies.put(currency,transactionsForCurrency);
            }
            transactionsForCurrency.add(t);
        }

        //now
        Map<Currency, List<Transaction>> collect = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));



    }
}
