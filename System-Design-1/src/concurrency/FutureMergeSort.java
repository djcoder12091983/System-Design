package concurrency;

import java.util.Arrays;
import java.util.concurrent.*;

public class FutureMergeSort {

    // Optimization: Sequential sort is faster for small arrays to avoid thread overhead
    private static final int THRESHOLD = 500;
    private final ExecutorService executor;

    public FutureMergeSort(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    /**
     * Primary entry point for sorting.
     */
    public int[] sort(int[] array) throws InterruptedException, ExecutionException {
        if (array == null || array.length <= 1) return array;
        // Submit the first task and block until the entire recursion completes
        return sortRecursive(array).get();
    }

    /**
     * Divides the array and submits sub-tasks to the executor.
     */
    private Future<int[]> sortRecursive(int[] array) {
        return executor.submit(() -> {
            int n = array.length;

            // Switch to sequential sort if below the threshold
            if (n <= THRESHOLD) {
                return sequentialSort(array);
            }

            int mid = n / 2;
            int[] leftPart = Arrays.copyOfRange(array, 0, mid);
            int[] rightPart = Arrays.copyOfRange(array, mid, n);

            // Create asynchronous tasks for both halves
            Future<int[]> leftFuture = sortRecursive(leftPart);
            Future<int[]> rightFuture = sortRecursive(rightPart);

            // get() blocks the current thread until the sub-task results are ready
            return merge(leftFuture.get(), rightFuture.get());
        });
    }

    /**
     * standard single-threaded merge sort logic for small segments.
     */
    private int[] sequentialSort(int[] array) {
        if (array.length <= 1) return array;
        int mid = array.length / 2;
        int[] left = sequentialSort(Arrays.copyOfRange(array, 0, mid));
        int[] right = sequentialSort(Arrays.copyOfRange(array, mid, array.length));
        return merge(left, right);
    }

    /**
     * Merges two sorted arrays into one.
     */
    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            result[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) throws Exception {
        int[] data = {38, 27, 43, 3, 9, 82, 10, 15, 22, 60, 5, 1};
        System.out.println("Original: " + Arrays.toString(data));

        // Use a pool matching the number of available CPU cores
        FutureMergeSort sorter = new FutureMergeSort(Runtime.getRuntime().availableProcessors());
        int[] sortedData = sorter.sort(data);

        System.out.println("Sorted:   " + Arrays.toString(sortedData));
        sorter.shutdown();
    }
}
