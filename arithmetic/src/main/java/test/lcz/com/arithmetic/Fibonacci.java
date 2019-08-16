package test.lcz.com.arithmetic;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-08-08
 */
public class Fibonacci {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long a = fibonacci(50);
        System.out.println("use time " + (System.currentTimeMillis() - start) + "  a " + a);
        long startTwo = System.currentTimeMillis();
        long two = fibonacciTwo(94);
        System.out.println("two use time " + (System.currentTimeMillis() - startTwo) + "  two " + two);
    }

    //斐波那契数
    private static long fibonacci(int n) {
        if (n > 1) {
            return fibonacci(n - 2) + fibonacci(n - 1);
        } else {
            return n;
        }
    }

    //斐波那契数
    private static long fibonacciTwo(int n) {
        if (n > 1) {
            long a, b = 1;
            n--;
            System.out.println("before  a  " + " n " + n);
            a = n & 1;
            System.out.println("end a  " + a);
            n /= 2;
            while (n-- > 0) {
                a += b;
                b += a;
            }
            return b;
        } else {
            return n;
        }
    }

}
