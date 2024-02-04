package com.example.springbootproject.utils;

public class R { // R = Result

    private boolean flag;
    private Object data;

    public R(){}

    public R(boolean flag) {
        this.flag = flag;
    }

    public R(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                    "\"flag\": " + flag + ", " +
                    "\"data\": " +  data +
                "}";
    }
}
