package cn.partner.algorithms.interview;

/**
 * 大整数相加
 * 大数相加   a和b都是很大的正整数  long存不下  求a + b
 */
public class BigDataSum {

    public static void main(String[] args) {
        String a = "9999999";
        String b = "2";
        String result = add(a, b);
        System.out.println(result);
        System.out.println("isEquals:" + new Long(result).equals((new Long(a) + new Long(b))));
    }

    private static final char zero = '0';
    public static String add(String a, String b) {
        int resultLength = Math.max(a.length(), b.length());
        char[] charArr = new char[resultLength];

        int i = 1;
        boolean hasBit = false;
        while (i <= a.length() || i <= b.length()) {
            int sum = 0;

            if (i <= a.length()) {
                char ca = a.charAt(a.length() - i);
                sum += ca -zero;
            }
            if (i <= b.length()) {
                char cb = b.charAt(b.length() - i);
                sum += cb -zero;
            }

            if (hasBit) {
                sum += 1;
            }
            if (sum >= 10) {
                hasBit = true;
                sum = sum % 10;
            }
            charArr[resultLength - i] = (char)(sum + zero);
            i++;
        }

        StringBuilder sb = new StringBuilder();
        if (hasBit) {
            sb.append("1");
        }

        sb.append(charArr);

        return sb.toString();
    }
}
