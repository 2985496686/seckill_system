package com.dto;

public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    //执行失败时返回
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }


    //执行成功时返回
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
