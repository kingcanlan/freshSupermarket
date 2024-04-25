package com.syhg.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

/**
 * 封装前端返回的通知实体类
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {
    private int status; //状态0:接口调用成功
    private T date; //当status=0，将返回的数据封装到data中
    private String msg;//提示信息

    private ServerResponse() {
    }

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T date) {
        this.status = status;
        this.date = date;
    }

    private ServerResponse(int status, T date, String msg) {
        this.status = status;
        this.date = date;
        this.msg = msg;
    }

    public static ServerResponse creatServerResponseBySucess(){
        return new ServerResponse(0);
    }
    public static <T> ServerResponse creatServerResponseBySucess(T data){
        return new ServerResponse(0,data);
    }
    public static <T> ServerResponse creatServerResponseBySucess(T data,String msg){
        return new ServerResponse(0,data,msg);
    }

    public static ServerResponse creatServerResponseByFail(int status){
        return new ServerResponse(status);
    }
    public static ServerResponse creatServerResponseByFail(int status,String msg){
        return new ServerResponse(status,null,msg);
    }

//    判断接口是否成功
    @JsonIgnore
    public boolean isSucess(){
        return this.status==0;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
