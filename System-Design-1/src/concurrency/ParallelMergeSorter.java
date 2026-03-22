package concurrency;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.Arrays;

public class ParallelMergeSorter {
    public static void parallelSort(int[] array) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool(); // Get the common thread pool
        forkJoinPool.invoke(new MergeSortTask(array, 0, array.length - 1));
    }

    static class MergeSortTask extends RecursiveAction {
        private final int[] array;
        private final int left;
        private final int right;
        private static final int THRESHOLD = 8192; // Switch to sequential sort below this size

        public MergeSortTask(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left + 1 <= THRESHOLD) {
                Arrays.sort(array, left, right + 1); // Use built-in sequential sort
                return;
            }

            int mid = left + (right - left) / 2;
            MergeSortTask leftTask = new MergeSortTask(array, left, mid);
            MergeSortTask rightTask = new MergeSortTask(array, mid + 1, right);

            // Invoke tasks in parallel and wait for them to finish
            invokeAll(leftTask, rightTask);

            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }

    // Standard sequential merge method (requires temporary space)
    private static void merge(int[] array, int left, int mid, int right) {
        // ... (standard merge logic using a temporary array) ...
        int[] temp = Arrays.copyOfRange(array, left, right + 1);
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);
    }
}