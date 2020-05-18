package com.zm.inference.common.util.domain;

/**
 * 比较符
 *
 * @author zm
 * @date 2020/5/18 10:49
 **/
public enum BaseOperator {
    LEFT("(", 100), AND("^", 90), OR("v", 80);
    private String symbol;
    private Integer priority;

    private BaseOperator(String symbol, Integer priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
