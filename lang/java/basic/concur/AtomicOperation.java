package basic.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * The package java.concurrent.atomic contains many useful classes to perform
 * atomic operations. An operation is atomic when you can safely perform in
 * parallel on multiple thread using synchronized keyword.
 * Internally, the atomic classes make heavy use of compare-and-swap (CAS),
 * an atomic instruction directly supported by most modern CPUs. Those
 * instructions usually are much faster than synchronizing via locks. So my
 * advice is to prefer atomic classes over locks in case you just have to change
 * a single mutable variable concurrently.
 */
public class AtomicOperation {
    public void atomicIntegeter() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInteger::incrementAndGet)); // n -> n+1;
        System.out.println(atomicInteger.get()); // 1000

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = ()-> atomicInteger.updateAndGet(n -> n + 2);
                    executor.submit(task);
                });
        System.out.println(atomicInteger.get()); // 2000

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = ()->atomicInteger.accumulateAndGet(i, (m, n) -> m + n);
                    executor.submit(task);
                });
        System.out.println(atomicInteger.get()); // 499500

    }

    public void longAdderAndAccumulate() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        LongAdder longAdder = new LongAdder();
        IntStream.range(0, 1000)
                .forEach(i ->
                    executorService.submit(longAdder::increment)
        );
        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator = new LongAccumulator((m, n)-> m + n, 1L);
        IntStream.range(0, 10)
                .forEach(i -> executorService.submit(()->longAccumulator.accumulate(i)));

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(longAccumulator.getThenReset());
    }

    public static void main(String[] args) throws Exception {
        AtomicOperation atomicOperation = new AtomicOperation();
        //atomicOperation.atomicIntegeter();
        atomicOperation.longAdderAndAccumulate();
    }
}
