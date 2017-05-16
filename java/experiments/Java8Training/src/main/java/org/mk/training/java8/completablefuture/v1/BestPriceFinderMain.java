package org.mk.training.java8.completablefuture.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by neeraj on 18/03/16.
 */
public class BestPriceFinderMain {

    List<Shop> shops ;
    private final Executor executor;
    BestPriceFinderMain() {
        shops = new ArrayList<Shop>();
        shops.add(new Shop("Shop 1"));
        shops.add(new Shop("Shop 2"));
        shops.add(new Shop("Shop 3"));
        shops.add(new Shop("Shop 4"));
        shops.add(new Shop("Shop 5"));
        executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
    }

    private List<String> getPricesSequential(String product) {
        return shops.stream().map(shop -> shop.getName() + "price is " + shop.getPriceRemoteService(product)).collect(Collectors.toList());
    }

    private List<String> getPricesParallel(String product) {
        return shops.parallelStream().map(shop -> shop.getName() + "price is " + shop.getPriceRemoteService(product)).collect(Collectors.toList());
    }

    private List<String> getPricesFutures(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop->CompletableFuture.supplyAsync(()-> shop.getName() + "price is " + shop.getPriceRemoteService(product), executor)).collect(Collectors.toList());

        List<String> prices = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return prices;
    }

    private List<String> getPricesFutures2(String product) {
        return shops.stream().map(shop->CompletableFuture.supplyAsync(()-> shop.getName() + "price is " + shop.getPriceRemoteService(product), executor).join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        BestPriceFinderMain finder = new BestPriceFinderMain();
        long start = System.nanoTime();
        finder.getPricesSequential("product1");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for getPricesSequential " + invocationTime
                + " msecs");

        start = System.nanoTime();
        finder.getPricesParallel("product1");
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for getPricesParallel " + invocationTime
                + " msecs");

        start = System.nanoTime();
        finder.getPricesFutures("product1");
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for getPricesFutures " + invocationTime
                + " msecs");

        start = System.nanoTime();
        finder.getPricesFutures2("product1");
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for getPricesFutures2 " + invocationTime
                + " msecs");
    }
}
