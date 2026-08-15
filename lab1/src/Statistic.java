import java.util.Arrays;
import java.util.Random;

public class Statistic extends AStats {
    private double[] data;

    @Override
    public double[] generateRandomNumbers(int count) {
        Random randomGenerator = new Random();
        data = new double[count];
        for (int index = 0; index < count; index++) {
            data[index] = randomGenerator.nextDouble();
        }
        return data;
    }

    @Override
    public double mean() {
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    @Override
    public double variance() {
        double meanValue = mean();
        double sum = 0.0;
        for (double value : data) {
            sum += (value - meanValue) * (value - meanValue);
        }
        return sum / data.length;
    }

    @Override
    public double median() {
        double[] sortedData = data.clone();
        Arrays.sort(sortedData);
        int size = sortedData.length;
        if (size % 2 == 0) {
            return (sortedData[size / 2 - 1] + sortedData[size / 2]) / 2.0;
        }
        return sortedData[size / 2];
    }

    @Override
    public int[] freq(){
        int[] bucketCounts = new int[10];
        for (double value : data){
            int bucketIndex = (int) (value*10);
            if (bucketIndex == 10) {
                bucketIndex = 9;
            }
            bucketCounts[bucketIndex]++;
        }
        return bucketCounts;
    }
}
