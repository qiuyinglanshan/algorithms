package cn.partner.algorithms.interview.other;
/**
 * 只考虑了正数，负数的二进制的补码形式=反码 + 1
 */
public class DecimalToBinary {

    public static void main(String[] args) {
        // 100010
        new DecimalToBinary().binaryToDecimal(34);
    }

    public void binaryToDecimal(int n) {
        String str = "";
        while (n != 0) {
            str = (n & 1) + str;
            n = n >> 1;
        }
        System.out.println(str);
    }
}
