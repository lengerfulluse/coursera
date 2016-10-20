package basic.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * demonstrate ForkJoinPool usage.
 */
public class ForkAndJoinDemo {
    class MyRecursiveAction extends RecursiveAction {
        private long workLoad = 0;

        public MyRecursiveAction(long workLoad) {
            this.workLoad = workLoad;
        }
        @Override
        protected void compute() {

            //if work is above threshold, break tasks up into smaller tasks
            if(this.workLoad > 16) {
                System.out.println("Splitting workLoad : " + this.workLoad);

                List<MyRecursiveAction> subtasks =
                        new ArrayList<MyRecursiveAction>();

                subtasks.addAll(createSubtasks());
                subtasks.forEach((subtask -> subtask.fork()));
            } else {
                System.out.println("Doing workLoad myself: " + this.workLoad);
            }
        }

        private List<MyRecursiveAction> createSubtasks() {
            List<MyRecursiveAction> subtasks = new ArrayList<>();

            MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
            MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);

            subtasks.add(subtask1);
            subtasks.add(subtask2);

            return subtasks;
        }
    }

    class MyRecursiveTask extends RecursiveTask<Long> {

        private long workLoad = 0;

        public MyRecursiveTask(long workLoad) {
            this.workLoad = workLoad;
        }

        protected Long compute() {

            //if work is above threshold, break tasks up into smaller tasks
            if(this.workLoad > 16) {
                System.out.println("Splitting workLoad : " + this.workLoad);

                List<MyRecursiveTask> subtasks =
                        new ArrayList<MyRecursiveTask>();
                subtasks.addAll(createSubtasks());

                subtasks.forEach(subtask->subtask.fork());

                long result = 0;
                for(MyRecursiveTask subtask : subtasks) {
                    result += subtask.join();
                }
                return result;

            } else {
                System.out.println("Doing workLoad myself: " + this.workLoad);
                return workLoad * 3;
            }
        }

        private List<MyRecursiveTask> createSubtasks() {
            List<MyRecursiveTask> subtasks =
                    new ArrayList<>();

            MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
            MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

            subtasks.add(subtask1);
            subtasks.add(subtask2);

            return subtasks;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        ForkAndJoinDemo forkAndJoinDemo = new ForkAndJoinDemo();
        /* great example of initializing inner class. */
        MyRecursiveAction myRecursiveAction = forkAndJoinDemo.new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);

        MyRecursiveTask myRecusiveTask = forkAndJoinDemo.new MyRecursiveTask(24);
        System.out.println(forkJoinPool.invoke(myRecusiveTask));
    }
}
