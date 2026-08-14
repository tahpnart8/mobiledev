/**
 * Lớp trừu tượng định nghĩa các thao tác thống kê trên một tập số thực
 * ngẫu nhiên thuộc đoạn [0, 1].
 */
public abstract class AStats {

    /**
     * Sinh ra một mảng gồm count số thực ngẫu nhiên, mỗi số thuộc đoạn [0, 1].
     */
    public abstract double[] generateRandomNumbers(int count);

    /**
     * Trả về giá trị trung bình của tập số đã sinh.
     */
    public abstract double mean();

    /**
     * Trả về phương sai của tập số đã sinh.
     */
    public abstract double variance();

    /**
     * Trả về trung vị của tập số đã sinh.
     */
    public abstract double median();

    /**
     * Chia đoạn [0, 1] thành 10 khoảng bằng nhau (0-0.1, 0.1-0.2, ..., 0.9-1)
     * và trả về số lượng phần tử rơi vào từng khoảng.
     */
    public abstract int[] freq();
}
