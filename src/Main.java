import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final int BLACK_THRESHOLD = 80;
    private static final double MIN_BLACK_RATIO_PER_COLUMN = 0.10;

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Invalid number of arguments. Usage: java Main <absolute_image_path>");
                return;
            }

            File imageFile = new File(args[0]);

            if (!imageFile.exists() || !imageFile.isFile()) {
                System.out.println("Image file does not exist: " + args[0]);
                return;
            }

            BufferedImage image = ImageIO.read(imageFile);

            if (image == null) {
                System.out.println("Unable to read image file. Please provide a valid JPG image.");
                return;
            }

            System.out.println(countVerticalLines(image));

        } catch (IOException e) {
            System.out.println("Error reading image file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static int countVerticalLines(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int lineCount = 0;
        boolean currentlyInsideLine = false;

        for (int x = 0; x < width; x++) {
            boolean isLineColumn = hasEnoughBlackPixels(image, x, height);

            if (isLineColumn && !currentlyInsideLine) {
                lineCount++;
                currentlyInsideLine = true;
            } else if (!isLineColumn) {
                currentlyInsideLine = false;
            }
        }

        return lineCount;
    }

    private static boolean hasEnoughBlackPixels(BufferedImage image, int x, int height) {
        int blackPixelCount = 0;

        for (int y = 0; y < height; y++) {
            if (isBlack(image.getRGB(x, y))) {
                blackPixelCount++;
            }
        }

        return blackPixelCount >= height * MIN_BLACK_RATIO_PER_COLUMN;
    }

    private static boolean isBlack(int rgb) {
        int red = (rgb >> 16) & 0xff;
        int green = (rgb >> 8) & 0xff;
        int blue = rgb & 0xff;

        return red < BLACK_THRESHOLD
                && green < BLACK_THRESHOLD
                && blue < BLACK_THRESHOLD;
    }
}