//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int count = 100;

        AStats stats = new Statistic();
        stats.generateRandomNumbers(count);

        System.out.println("Số lượng phần tử sinh ra: " + count);
        System.out.printf("Trung bình (mean):      %.6f%n", stats.mean());
        System.out.printf("Phương sai (variance):  %.6f%n", stats.variance());
        System.out.printf("Trung vị (median):      %.6f%n", stats.median());

        int[] freq = stats.freq();
        System.out.println("\nBảng tần suất theo 10 khoảng trong [0, 1]:");

        GraphicsA graphics = new GraphicsA();
        graphics.draw(freq);
    }
}