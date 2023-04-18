package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //task1
        List<Transaction> transactions1 = transactions.stream()
                .filter(item -> item.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        //task2
        List<String> cities = transactions.stream()
                .map(item -> item.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        //task3
        List<Trader> fromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        //task4
        List<String> allTradersSorted = transactions.stream()
                .map(item -> item.getTrader().getName())
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        //task5
        boolean fromMilan = transactions.stream()
                .anyMatch(item -> item.getTrader().getCity().equals("Milan"));
        //task6
        transactions.stream().map(Transaction::getValue).forEach(System.out::println);

        //task7
        Integer highestValue = transactions.stream().map(Transaction::getValue).reduce((a, b) -> a > b ? a : b).get();

        //task8
        Integer smallestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min).get();


        System.out.println(allTradersSorted);
    }
}