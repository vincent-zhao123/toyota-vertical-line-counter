import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    private static final int BLACK_THRESHOLD = 80;

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Invalid number of arguments. Usage: java Main <absolute_image_path>");
                return;
            }

            BufferedImage image = ImageIO.read(new File(args[0]));

            if (image == null) {
                System.out.println("Unable to read image file.");
                return;
            }

            int count = countVerticalLines(image);
            System.out.println(count);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int countVerticalLines(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        boolean insideLine = false;
        int lineCount = 0;

        for (int x = 0; x < width; x++) {
            boolean columnHasBlackLine = isBlackColumn(image, x, height);

            if (columnHasBlackLine && !insideLine) {
                lineCount++;
                insideLine = true;
            } else if (!columnHasBlackLine) {
                insideLine = false;
            }
        }

        return lineCount;
    }

    private static boolean isBlackColumn(BufferedImage image, int x, int height) {
        int blackPixels = 0;

        for (int y = 0; y < height; y++) {
            int rgb = image.getRGB(x, y);

            int red = (rgb >> 16) & 0xff;
            int green = (rgb >> 8) & 0xff;
            int blue = rgb & 0xff;

            if (red < BLACK_THRESHOLD && green < BLACK_THRESHOLD && blue < BLACK_THRESHOLD) {
                blackPixels++;
            }
        }

        return blackPixels >= height * 0.10;
    }
}