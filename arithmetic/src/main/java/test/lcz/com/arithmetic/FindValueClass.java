package test.lcz.com.arithmetic;

public class FindValueClass {
    private static int[][] arrays = {{1, 2, 3, 4, 5},
            {3, 5, 7, 8, 9},
            {6, 11, 13, 15, 17},
            {10, 14, 19, 21, 25}};

    public static void main(String[] args) {
        boolean result = findValue(arrays, 400);
        System.out.println("result  " + result);
    }

    private static boolean findValue(int[][] arrays, int value) {
        boolean result = false;
        if (arrays != null && arrays.length > 0 && arrays[0].length > 0) {
            int rows = arrays.length;
            int columns = arrays[0].length;
            int row = 0;
            int column = columns - 1;

            while (row < rows && column >= 0) {
                if (arrays[row][column] == value) {
                    result = true;
                    break;
                } else if (arrays[row][column] > value) {
                    column--;
                } else {
                    row++;
                }
            }
        }
        return result;
    }
}
