package com.zm.inference.common.util;

import com.zm.inference.common.util.domain.BaseOperator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Description 字符串相关处理工具类
 * @Author zm
 * @Date 2020/5/18 9:45
 **/
public class StringTool {

    /**
     * 中缀转后缀
     */
    public static String transRTextWithIText(String iText) {
        String[] iTextArr = iText.split(" ");
        Integer len = iTextArr.length;
        List<String> rTextList = new LinkedList<>();
        // 操作符栈
        Stack<BaseOperator> operatorStack = new Stack<>();

        for (int i = 0; i < len; i++) {
            String s = iTextArr[i];

            switch (s) {
                case "^":
                    while (true) {
                        if (operatorStack.empty() || operatorStack.peek().getPriority() < BaseOperator.AND.getPriority()
                                || operatorStack.peek() == BaseOperator.LEFT) {
                            // 栈为空,或栈顶元素的优先级严格小于^，或栈顶元素是(时，进行入栈操作
                            operatorStack.push(BaseOperator.AND);
                            break;
                        }
                        BaseOperator topOperator = operatorStack.peek();
                        rTextList.add(topOperator.getSymbol());
                        operatorStack.pop();
                    }
                    break;
                case "v":
                    while (true) {
                        if (operatorStack.empty() || operatorStack.peek().getPriority() < BaseOperator.OR.getPriority()
                                || operatorStack.peek() == BaseOperator.LEFT) {
                            operatorStack.push(BaseOperator.OR);
                            break;
                        }
                        BaseOperator topOperator = operatorStack.peek();
                        rTextList.add(topOperator.getSymbol());
                        operatorStack.pop();
                    }
                    break;
                case "(":
                    // 优先级最高，肯定是默认加入
                    operatorStack.push(BaseOperator.LEFT);
                    break;
                case ")":
                    // )操作符遇到一个则将栈元素弹出并输出一直到遇到左括号，左括号只弹出不输出
                    while (true) {
                        BaseOperator tmp = operatorStack.peek();
                        operatorStack.pop();
                        if ("(".equals(tmp.getSymbol())) {
                            break;
                        }
                        rTextList.add(tmp.getSymbol());
                    }
                    break;
                default:
                    // 操作数直接加入到rTextList中
                    rTextList.add(s);
            }
        }

        // 处理栈中剩余的元素
        while(!operatorStack.empty()){
            rTextList.add(operatorStack.peek().getSymbol());
            operatorStack.pop();
        }

        // 将rTextList中的内容重新封装成字符串
        StringBuilder rText = new StringBuilder();
        for (int i = 0; i < rTextList.size(); i++) {
            rText.append(rTextList.get(i)).append(" ");
        }

        return rText.toString();
    }
}
