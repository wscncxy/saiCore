package com.sai.core.utils;

/**
 * Created by xuehan on 2017/7/1.
 */
import java.util.Random;

public class CodeGeneric {

    public static final int CODE_DEFALUT_LENGTH = 4;

    private static final Random RANDOM = new Random();

    public static String codeGeneric() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();

        for (int i = 0; i < CODE_DEFALUT_LENGTH; i++) {
            int res = rand.nextInt(9);

            if (i == 0) {
                while (res == 0) {
                    res = rand.nextInt(9);
                }
            }
            sb.append(res);
        }
        return sb.toString();
    }

    public static String random(int count) {
        char[] buffer = new char[count];
        int end = 'z' + 1;
        int start = ' ';

        int gap = end - start;

        while (count-- != 0) {
            char ch = (char) (RANDOM.nextInt(gap));

            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                if (ch >= 56320 && ch <= 57343) {
                    if (count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it
                        // in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + RANDOM.nextInt(128));
                    }
                } else if (ch >= 55296 && ch <= 56191) {
                    if (count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting
                        // it in
                        buffer[count] = (char) (56320 + RANDOM.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if (ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }

    public static void main(StringUtil[] args) {

        System.out.println(random(8));
    }
}