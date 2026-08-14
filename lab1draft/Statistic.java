import java.util.Arrays;
import java.util.Random;

/**
 * Cài đặt cụ thể của AStats, thao tác trên một tập số thực ngẫu nhiên
 * thuộc đoạn [0, 1].
 */
public class Statistic extends AStats {

    private double[] data;

    @Override
    public double[] generateRandomNumbers(int count) {
        Random randomGenerator = new Random();
        data = new double[count];
        for (int index = 0; index < count; index++) {
            data[index] = randomGenerator.nextDouble(); // giá trị trong [0.0, 1.0)
        }
        return data;
    }

    @Override
    public double mean() {
        requireData();
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    @Override
    public double variance() {
        requireData();
        double meanValue = mean();
        double sum = 0.0;
        for (double value : data) {
            sum += (value - meanValue) * (value - meanValue);
        }
        return sum / data.length;
    }

    @Override
    public double median() {
        requireData();
        double[] sortedData = data.clone();
        Arrays.sort(sortedData);
        int size = sortedData.length;
        if (size % 2 == 0) {
            return (sortedData[size / 2 - 1] + sortedData[size / 2]) / 2.0;
        }
        return sortedData[size / 2];
    }

    @Override
    public int[] freq() {
        requireData();
        int[] bucketCounts = new int[10];
        for (double value : data) {
            int bucketIndex = (int) (value * 10);
            if (bucketIndex == 10) { // trường hợp value == 1.0
                bucketIndex = 9;
            }
            bucketCounts[bucketIndex]++;
        }
        return bucketCounts;
    }

    /**
     * Trả về tập số đã sinh (dùng cho hiển thị/kiểm tra nếu cần).
     */
    public double[] getData() {
        return data;
    }

    private void requireData() {
        if (data == null) {
            throw new IllegalStateException(
                    "Chưa có dữ liệu. Hãy gọi generateRandomNumbers(n) trước.");
        }
    }
}
