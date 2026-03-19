package golovach.io.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestCodes {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str =new String(new byte[] {0, 1, 2});
        String str1 =new String(new byte[] {65, 66, 67}, StandardCharsets.UTF_8);
        System.out.println(str);
        System.out.println(str1);
        System.out.println("A".getBytes("UTF-16").length);
        System.out.println("AA".getBytes("UTF-16").length);
        byte[] b = "AA".getBytes("UTF-16");
        System.out.println(Arrays.toString(b));
        System.out.println("A".getBytes("UTF-16").toString());
    }

}
