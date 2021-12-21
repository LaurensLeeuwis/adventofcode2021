package eu.leeuwis.adventofcode2021.day20;

import java.util.List;

class Day20 {

    long puzzle(List<String> input, int maxTimes) {
        Input parsed = parse(input);

        boolean[] algorithm = parsed.algorithm();

        boolean[][] image = pad(parsed.image(), maxTimes);
//        print(image);

        for (int times = 0; times < maxTimes; times++) {
            boolean[][] result = new boolean[image.length - 2][image[0].length - 2];

            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = getValue(image, algorithm, i + 1, j + 1);
                }
            }
            image = result;
//            print(image);
//            System.out.println();

        }
//        print(image);

        return lit(image);
    }

    private boolean[][] pad(boolean[][] image, int maxTimes) {
        boolean[][] result = new boolean[image.length + (maxTimes * 4)][image[0].length + (maxTimes * 4)];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                if (i < maxTimes * 2 || j < maxTimes * 2) {
                    result[i][j] = false;
                } else if ((i - maxTimes * 2) < image.length && (j - maxTimes * 2) < image[0].length) {
                    result[i][j] = image[i - maxTimes * 2][j - maxTimes * 2];
                } else {
                    result[i][j] = false;
                }
            }
        }
        return result;
    }

    private long lit(boolean[][] image) {
        long count = 0L;
        for (boolean[] booleans : image) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    count++;
                }
            }
        }
        return count;
    }

    private void print(boolean[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] ? '#' : '.');
            }
            System.out.println();
        }
    }

    private boolean getValue(boolean[][] image, boolean[] algorithm, int i, int j) {
        String result = "";
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                result += binaryString(getValue(image, x, y));
            }
        }

        return algorithm[Integer.parseInt(result, 2)];

    }

    private String binaryString(boolean bool) {
        return bool ? "1" : "0";
    }

    private boolean getValue(boolean[][] image, int i, int j) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length) {
            return false;
        }

        return image[i][j];
    }

    private Input parse(List<String> input) {
        String algorithm = input.get(0);
        boolean[][] image = new boolean[input.size() - 2][input.get(2).length()];

        for (int i = 2; i < input.size(); i++) {
            image[i - 2] = toBooleanArray(input.get(i));
        }

        return new Input(toBooleanArray(algorithm), image);
    }

    private boolean[] toBooleanArray(String input) {
        boolean[] result = new boolean[input.length()];
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            result[i] = charArray[i] == '#';
        }

        return result;
    }
}
