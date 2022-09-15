package com.beneway.basic.common.exception;

public class TokenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;

    public TokenException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
