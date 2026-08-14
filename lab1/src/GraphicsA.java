import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphicsA {
    public void draw(int[] frequencies){
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("\n(Không phát hiện màn hình đồ họa - bỏ qua việc mở cửa sổ biểu đồ)");
            return;
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ phân bố tần suất");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 480);
            frame.setLocationRelativeTo(null);
            frame.add(new HistogramPanel(frequencies));
            frame.setVisible(true);
        });
    }

    public  void drawText(int[] frequencies) {
        int maxCount = 0;
        for (int value : frequencies) {
            maxCount = Math.max(maxCount, value);
        }
        if (maxCount == 0) {
            maxCount = 1;
        }

        int maxBarLength = 50;
        System.out.println("\nBiểu đồ phân bố tần suất (dạng văn bản):");
        for (int bucketIndex = 0; bucketIndex < frequencies.length; bucketIndex++) {
            String label = String.format("[%.1f - %.1f)", bucketIndex * 0.1, (bucketIndex + 1) * 0.1);
            int barLength = (int) Math.round((double) frequencies[bucketIndex] / maxCount * maxBarLength);
            StringBuilder bar = new StringBuilder();
            for (int j = 0; j < barLength; j++) {
                bar.append('#');
            }
            System.out.printf("%-14s | %-" + maxBarLength + "s %d%n", label, bar, frequencies[bucketIndex]);
        }
    }

    private static class HistogramPanel extends JPanel {
        private final int[] frequencies;

        HistogramPanel(int[] frequencies) {
            this.frequencies = frequencies;
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int marginLeft = 60;
            int marginBottom = 60;
            int marginTop = 50;
            int marginRight = 20;
            int chartWidth = width - marginLeft - marginRight;
            int chartHeight = height - marginTop - marginBottom;

            int maxFreq = 0;
            for (int value : frequencies) {
                maxFreq = Math.max(maxFreq, value);
            }
            if (maxFreq == 0) {
                maxFreq = 1;
            }

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
            g2.drawString("Phân bố tần suất số ngẫu nhiên trong [0, 1]", marginLeft, 25);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12f));

            g2.drawLine(marginLeft, marginTop, marginLeft, marginTop + chartHeight);
            g2.drawLine(marginLeft, marginTop + chartHeight, marginLeft + chartWidth, marginTop + chartHeight);

            int barCount = frequencies.length;
            int gap = 12;
            int barWidth = (chartWidth - gap * (barCount + 1)) / barCount;
            FontMetrics fm = g2.getFontMetrics();

            for (int barIndex = 0; barIndex < barCount; barIndex++) {
                int barHeight = (int) Math.round((double) frequencies[barIndex] / maxFreq * (chartHeight - 30));
                int x = marginLeft + gap + barIndex * (barWidth + gap);
                int y = marginTop + chartHeight - barHeight;

                g2.setColor(new Color(70, 130, 180));
                g2.fillRect(x, y, barWidth, barHeight);
                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(x, y, barWidth, barHeight);

                String valueLabel = String.valueOf(frequencies[barIndex]);
                g2.drawString(valueLabel, x + barWidth / 2 - fm.stringWidth(valueLabel) / 2, y - 5);

                String rangeLabel = String.format("%.1f-%.1f", barIndex * 0.1, (barIndex + 1) * 0.1);
                g2.drawString(rangeLabel, x + barWidth / 2 - fm.stringWidth(rangeLabel) / 2,
                        marginTop + chartHeight + 18);
            }
        }
    }
}
