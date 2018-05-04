package test.lcz.com.arithmetic;

/**
 * Created by it on 18-5-2.
 */

public class ArrayChange {
    private static int[] array = new int[]{1, 32, 0, 43, 134, 7564, 23, 54, 78};
    private static int[] arrayReveris = new int[]{1, 32, 0, 43};
    private static int[][] matrix = new int[][]{{1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564},
            {1, 32, 0, 43, 134, 7564}};


    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(fibonacciDitui(8));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(fibonacciLoop(8));
//        System.out.println(System.currentTimeMillis());
//        numberOf1(13323);
//        numberOf1Two(13323);
//        reorderOddEven(array);
//        printMaterixClockwisely(matrix);
        System.out.println(getInversePairs(arrayReveris));
    }

    //斐波那契数列
    private static long fibonacciLoop(int n) {
        long[] result = new long[]{0, 1};
        if (n < 0) {
            return -1;
        } else if (n < 2) {
            return result[n];
        } else {
            long fNOne = 1;
            long fNTwo = 0;
            long fN = 0;
            for (int i = 2; i <= n; i++) {
                fN = fNOne + fNTwo;
                fNTwo = fNOne;
                fNOne = fN;
            }
            return fN;
        }
    }

    private static long fibonacciDitui(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacciDitui(n - 1) + fibonacciDitui(n - 2);
        }
    }

    //查看数字1的个数
    private static int numberOf1(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count = count + 1;
            }
            num = num >> 1;
        }
        System.out.println(count);
        return count;
    }

    private static int numberOf1Two(int num) {
        int count = 0;
        while (num > 0) {
            count = count + 1;
            num = num & (num - 1);
        }
        System.out.println(count);
        return count;
    }

    //调整奇偶顺序
    private static void reorderOddEven(int[] array) {
        if (array != null && array.length > 0) {
            int low = 0, high = array.length - 1;
            while (low < high) {
                while (low < high && ((array[low] & 1) != 0)) {
                    low++;
                }
                while (low < high && ((array[high] & 1) == 0)) {
                    high--;
                }
                if (low < high) {
                    int temp = array[low];
                    array[low] = array[high];
                    array[high] = temp;
                }
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    //顺时针打印矩阵
    private static void printMaterixClockwisely(int[][] materix) {
        if (materix != null && materix.length > 0 && materix[0].length > 0) {
            int start = 0;
            int columns = materix[0].length;
            int rows = materix.length;
            while (columns > start * 2 && rows > start * 2) {
                printMaterixInCircle(materix, start);
                start++;
            }
        }
    }

    private static void printMaterixInCircle(int[][] materix, int start) {
        if (materix != null && materix.length > 0 && materix[0].length > 0) {
            int columns = materix[0].length;
            int rows = materix.length;
            int endColumn = columns - start - 1;
            int endRow = rows - start - 1;
            for (int i = start; i <= endColumn; i++) {
                System.out.print(materix[start][i] + " ");
            }

            if (start < endRow) {
                for (int i = start + 1; i <= endRow; i++) {
                    System.out.print(materix[i][endColumn] + " ");
                }
            }

            if (start < endColumn && start < endRow) {
                for (int i = endColumn - 1; i >= start; i--) {
                    System.out.print(materix[endRow][i] + " ");
                }
            }

            if (start < endColumn - 1 && start < endRow) {
                for (int i = endRow - 1; i > start; i--) {
                    System.out.print(materix[i][start] + " ");
                }
            }
        }
    }

    private static int getInversePairs(int[] array) {
        if (array != null && array.length > 0) {
            int[] temp = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            return inversePairsCore(array, temp, 0, array.length - 1);
        } else {
            return -1;
        }
    }

    private static int inversePairsCore(int[] array, int[] temp, int start, int end) {
        if (start == end) {
            return 0;
        }
        int length = (end - start) / 12;
        int left = inversePairsCore(array, temp, start, start + length);
        int right = inversePairsCore(array, temp, start + length + 1, end);
        int count = 0;
        int indexCopy = end;
        int i = start + length;
        int j = end;
        System.out.println("indexCopy  " + indexCopy + " i " + i + " j " + j);
        while (i >= start && start + length + 1 <= j) {
            if (array[start + length + 1] > array[end]) {
                temp[indexCopy--] = array[i--];
                count += j - start - length;
            } else {
                temp[indexCopy--] = array[j--];
            }
        }
        for (; i >= start; --i) {
            temp[indexCopy--] = array[i];
        }
        for (; j > start + length; --j) {
            temp[indexCopy--] = array[j];
        }
        return count + left + right;
    }
}
