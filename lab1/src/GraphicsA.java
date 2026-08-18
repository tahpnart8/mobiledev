public class GraphicsA {
    public void draw(int[] freq){
        for (int i = 0; i < freq.length; i++){
            if (i < freq.length - 1) {
                System.out.printf("[%.1f - %.1f): ", (i/10f), ((i+1)/10f));
            } else {
                System.out.printf("[%.1f - %.1f]: ", (i/10f), ((i+1)/10f));
            }

            for (int j = 0; j < freq[i]; j++) {
                System.out.print('@');
            }
            System.out.println(" (" + freq[i] + ") ");
        }
    }
}