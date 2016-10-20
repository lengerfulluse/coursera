import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * demonstrate basic usage of concurrency programming in practise.
 */
public class ConCurrency {

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();
    public void ThreadDemo() {
        Runnable a = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Run thread: " + name);
        };
        Thread thread = new Thread(a);
        thread.start();
        thread.run();
    }

    /**
     * The Concurrency API introduces the concept of an ExecutorService as a
     * higher level replacement for working with threads directly. Executors
     * are capable of running asynchronous tasks and typically manage a pool
     * of threads, so we don't have to create new thread manually.
     */
    public void ExecutorServiceDemo() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Run thread: " + Thread.currentThread().getName());
        });
        try {
            System.out.println("attempt to shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    public void CallableDemo() throws Exception {

        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(2);
            return 123;
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);
        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
        executorService.shutdownNow();
    }

    public void scheduledExecutors() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executorService.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms\n", remainingDelay);
    }

    void increment() {
        count = count + 1;
    }

    synchronized void incrementSync() {
        count = count + 1;
    }

    void incrementSyncWithBlock() {
        synchronized (this) {
            count = count + 1;
        }
    }

    /**
     * Internally, Java uses a so called monitor also known as monitor lock or intrinsic lock
     * in order to manage synchronization. This monitor bind to an object, eg. when using
     * synchronized methods each method share the same monitor of the corresponding object.
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html
     */
    public void incrementExample() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        /**
         * pass a object method reference.
         */
        IntStream.range(0, 10000)
                .forEach(i -> executorService.submit(this::incrementSyncWithBlock));

        stop(executorService);
        System.out.println(count);
    }

    void incrementWithReentrantLock() {
        lock.lock();
        /*
         * it's important to wrap your code into a try/finally block to ensure unlocking in case of
         * exceptions. This method is thread-safe just like the synchronized block.
         */
        try {
            count = count+ 1;
        } finally {
            lock.unlock();
        }
    }
    public void reentrantLockDemo() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });
        stop(executor);
    }

    /**
     * The interface ReadWriteLock specifies another type of lock maintaining
     * a pair of locks for read and write access. The idea behind read-write
     * locks is that it's usually safe to read mutable variables concurrently
     * as long as nobody is writing to this variable. So the read-lock can be
     * held simultaneously by multiple threads as long as no threads hold the
     * write-lock. This can improve performance and throughput in case that
     * reads are more frequent than writes.
     */
    public void readAndWriteLockDemo() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        executorService.submit(()-> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("Write lock: " + Thread.currentThread().getName());
                map.put("foo", "bar");
            }finally {
                readWriteLock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                readWriteLock.readLock().unlock();
            }
        };
        executorService.submit(readTask);
        executorService.submit(readTask);
        stop(executorService);
    }

    /**
     * java 8 ships with a new kind of lock called StampedLock which also support read
     * and write locks just like the ReadWriteLock. In contrast to ReadWriteLock the
     * locking methods of a StampedLock return a stamp represented by a long value.
     * You can use these stamps to either release a lock or to check if the lock is
     * still valid. Additionally stamped locks support another lock mode called
     * optimistic locking.
     */
    public void stampedLockDemo() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write lock: " + Thread.currentThread().getName());
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);


        /**
         * optimistic locking example
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        StampedLock stampedLock = new StampedLock();
        executorService.submit(()-> {
            /**
             * in contrast to normal read locks an optimistic lock doesn't prevent
             * other threads to obtain a write lock instantaneously. After sending
             * the first thread to sleep for one second the second thread obtains
             * a write lock without waiting for the optimistic read lock to be released.
             * From this point the optimistic read lock is no longer valid. Even when
             * the write lock is released the optimistic read locks stays invalid.
             */
            long stamp = stampedLock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });
        executorService.submit(() -> {
            long stamp = stampedLock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        stop(executorService);
    }

    /**
     * In addition to locks the Concurrency API also supports counting
     * semaphores. Whereas locks usually grant exclusive access to variables
     * or resources, a semaphore is capable of maintaining whole sets of
     * permits. This is useful in different scenarios where you have to
     * limit the amount concurrent access to certain parts of your application.
     */
    public void semaphoresDemo() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };
        IntStream.range(0, 10)
                .forEach(i -> executor.submit(longRunningTask));

        stop(executor);


    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        ConCurrency conCurrency = new ConCurrency();
        //conCurrency.ThreadDemo();
        //conCurrency.ExecutorServiceDemo();
        //conCurrency.CallableDemo();
        //conCurrency.scheduledExecutors();
        //conCurrency.incrementExample();
        //conCurrency.reentrantLockDemo();
        //conCurrency.readAndWriteLockDemo();
        conCurrency.stampedLockDemo();
    }
}