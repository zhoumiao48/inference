package com.zm.inference;

import com.zm.inference.common.util.StringTool;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/5/17 14:11
 **/
public class MyTest {

    public static void main(String[] args) {
        // 中缀转后缀
        String iText = "1 v ( 2 ^ 3 )";
        String rText = StringTool.transRTextWithIText(iText);
        System.out.println(rText);
    }
}
