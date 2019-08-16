package test.lcz.com.arithmetic;

public class SortClass {
    private static int[] array = new int[]{1, 32, 0, 43, 134, 7564, 23, 54, 78};

    public static void main(String[] args) {
//        bubbleSort(array);
//        selectSort(array);
//        insertSort(array);
//        quickSort(array);
//        long start = System.currentTimeMillis();
//        long a = fibonacci(50);
//        System.out.println("use time " + (System.currentTimeMillis() - start) + "  a " + a);

//        long starttwo = System.currentTimeMillis();
//        long two = fibonacciTwo(94);
//        System.out.println("two use time " + (System.currentTimeMillis() - starttwo) + "  two " + two);
        getArrowType(100, 0);
        getArrowType(100, 100);
        getArrowType(0, 100);
        getArrowType(-100, 100);
        getArrowType(-100, 0);
        getArrowType(-100, -100);
        getArrowType(0, -100);
        getArrowType(100, -100);
    }


    //冒泡
    private static void bubbleSort(int[] array) {
        if (array != null && array.length > 0) {
            for (int i = array.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1);
                    }
                }
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    private static void swap(int[] array, int x, int y) {
        if (array != null && array.length >= x && array.length > y) {
            int temp = array[x];
            array[x] = array[y];
            array[y] = temp;
        }
    }

    //选择排序
    private static void selectSort(int[] array) {
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] > array[j]) {
                        swap(array, i, j);
                    }
                }
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    //插入排序
    private static void insertSort(int[] array) {
        if (array != null && array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                for (int j = i; (j > 0) && (array[j] < array[j - 1]); j--) {
                    swap(array, j, j - 1);
                }
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    //希尔排序
    private static void shellSort(int[] array) {
        if (array != null && array.length > 0) {
            int temp;    //暂存变量
            boolean change; //数据是否改变
            int dataLength = array.length / 2; //分割集合的间隔长度
            int pointer; //进行处理的位置
            while (dataLength != 0) {
                for (int j = dataLength; j < array.length; j++) {
                    change = false;
                    temp = array[j];
                    pointer = j - dataLength;
                    while (temp < array[pointer] && pointer >= 0 && pointer <= array.length) {
                        array[pointer + dataLength] = array[pointer];
                        pointer = pointer - dataLength;
                        change = true;
                        if (pointer < 0 || pointer > dataLength) {
                            break;
                        }
                    }
                    array[pointer + dataLength] = temp;
                    if (change) {
                        System.out.print("排序中");
                        {
                            for (int k = 0; k > array.length; k++) {
                                System.out.print(array[k] + " , ");

                            }
                        }
                    }
                    dataLength = dataLength / 2;
                }
            }

            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    //二分排序
    private static void middleSort(int[] array) {
        if (array != null && array.length > 1) {
            int j, low, high, mid, temp;
            for (int i = 1; i < array.length; i++) {
                temp = array[i];
                low = 0;
                high = i - 1;
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (array[mid] > temp) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                for (j = i - 1; j > high; j--) {
                    array[j + 1] = array[j];
                }
                array[high + 1] = temp;
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    //快速排序
    private static void quickSort(int[] array) {
        if (array != null && array.length > 1) {
            int low = 0, high = array.length - 1;
            int i = 0, j = array.length - 1, temp;
            if (i < j) {
                i = low;
                j = high;
                temp = array[i];
                while (i < j) {
                    while (i < j && array[j] > temp) {
                        j--;
                    }
                    if (i < j) {
                        array[i] = array[j];
                        i++;
                    }
                    while (i < j && array[i] < temp) {
                        i++;
                    }
                    if (i < j) {
                        array[j] = array[i];
                        j--;
                    }
                }
                array[i] = temp;
            }
            for (int num : array) {
                System.out.print(num + " , ");
            }
        }
    }

    private static void getArrowType(double x, double y) {
        if (x == 0 && y == 0) {
            System.out.println(" ,vdsvdv  ");
        }
        double point1X = 320.0;
        double point1Y = 0;
        double vector = x * point1X + y * point1Y;
        //向量的模乘
        double sqrt = Math.sqrt((Math.abs(x * x) + Math.abs(y * y)) * (Math.abs(point1X * point1X) + Math.abs(point1Y * point1Y)));
        //反余弦计算弧度
        double radian = Math.acos(vector / sqrt);
        //弧度转角度制
        double degree = 180 * radian / Math.PI;
        double aa = Math.atan2(y, x) * 180 / Math.PI;
        System.out.println(" degree " + degree + "  aa " + aa);
    }
}
