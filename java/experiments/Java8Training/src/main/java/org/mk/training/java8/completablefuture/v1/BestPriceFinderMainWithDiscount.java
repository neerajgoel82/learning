package org.mk.training.java8.completablefuture.v1;

import org.mk.training.java8.completablefuture.Discount;
import org.mk.training.java8.completablefuture.Quote;
import org.mk.training.java8.completablefuture.ShopWithDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neeraj on 18/03/16.
 */
public class BestPriceFinderMainWithDiscount {

    List<ShopWithDiscount> shops ;
    private final Executor executor;
    BestPriceFinderMainWithDiscount() {
        shops = new ArrayList<ShopWithDiscount>();
        shops.add(new ShopWithDiscount("Shop 1"));
        shops.add(new ShopWithDiscount("Shop 2"));
        shops.add(new ShopWithDiscount("Shop 3"));
        shops.add(new ShopWithDiscount("Shop 4"));
        shops.add(new ShopWithDiscount("Shop 5"));
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
        return shops.stream().map(shop -> shop.getName() + "price is " + shop.getPrice(product)).collect(Collectors.toList());
    }

    private List<String> getPricesParallel(String product) {
        return shops.parallelStream().map(shop -> shop.getName() + "price is " + shop.getPrice(product)).collect(Collectors.toList());
    }

    private List<String> getPricesFutures(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop->CompletableFuture.supplyAsync(()-> shop.getName() + "price is " + shop.getPrice(product), executor)).collect(Collectors.toList());

        List<String> prices = priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return prices;
    }

    private List<String> getPricesFutures2(String product) {
        return shops.stream().map(shop->CompletableFuture.supplyAsync(()-> shop.getName() + "price is " + shop.getPrice(product), executor).join()).collect(Collectors.toList());
    }

    private Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop-> CompletableFuture.supplyAsync(()-> shop.getName() + "price is " + shop.getPrice(product), executor))
                .map(future-> future.thenApply(Quote::parse))
                .map(future-> future.thenCompose(
                        quote-> CompletableFuture.supplyAsync(
                                ()-> Discount.applyDiscountRemoteService(quote),executor
                        )
                ));
    }
/*
    public void printPricesStream(String product) {
        long start = System.nanoTime();
         shops.stream()
                .map(shop-> CompletableFuture.supplyAsync(()-> shop.getPrice(product), executor))
                .map(future-> future.thenApply(Quote::parse))
                .map(future-> future.thenCompose(
                        quote-> CompletableFuture.supplyAsync(
                                ()-> Discount.applyDiscountRemoteServiceRandomDelay(quote),executor
                        )
                ))
                .map(f-> f.thenAccept(s -> System.out.print()))
         ;
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for printPricesStream " + invocationTime
                + " msecs");
    }
*/

    public static void main(String[] args) {
        BestPriceFinderMainWithDiscount finder = new BestPriceFinderMainWithDiscount();
        /*long start = System.nanoTime();
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
*/
        long start = System.nanoTime();
        List<CompletableFuture<String>> discountedPricesFutures = finder.findPricesStream("product1").collect(Collectors.toList());
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for findPricesStream " + invocationTime
                + " msecs");

        start = System.nanoTime();
        List<String> prices = discountedPricesFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation time for applying the futures " + invocationTime
                + " msecs");

    }
}
