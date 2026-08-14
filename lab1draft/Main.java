/**
 * Chương trình chính: sinh một tập số ngẫu nhiên trong [0, 1], in ra các
 * giá trị thống kê (trung bình, phương sai, trung vị) và vẽ biểu đồ tần suất.
 *
 * Có thể truyền số lượng phần tử cần sinh qua tham số dòng lệnh, ví dụ:
 *   java Main 5000
 * Nếu không truyền, mặc định sinh 1000 số.
 */
public class Main {

    public static void main(String[] args) {
        int count = 1000;
        if (args.length > 0) {
            try {
                count = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Tham số không hợp lệ, dùng giá trị mặc định count = 1000");
            }
        }

        AStats stats = new Statistic();
        stats.generateRandomNumbers(count);

        System.out.println("Số lượng phần tử sinh ra: " + count);
        System.out.printf("Trung bình (mean):     %.6f%n", stats.mean());
        System.out.printf("Phương sai (variance):  %.6f%n", stats.variance());
        System.out.printf("Trung vị (median):      %.6f%n", stats.median());

        int[] freq = stats.freq();
        System.out.println("\nBảng tần suất theo 10 khoảng trong [0, 1]:");
        for (int i = 0; i < freq.length; i++) {
            System.out.printf("[%.1f - %.1f): %d%n", i * 0.1, (i + 1) * 0.1, freq[i]);
        }

        GraphicsA graphics = new GraphicsA();
        graphics.drawText(freq);
        graphics.draw(freq);
    }
}
