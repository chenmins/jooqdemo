package com.example.jooqdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseData<T>{
    private Integer code;

    private String message;

    private Boolean status;

    private T data;

    public ResponseData(T data){
        this.code = 200;
        this.message = "success";
        this.status = true;
        this.data = data;
    }

    public ResponseData(String message, T data){
        this.code = 200;
        this.message = message;
        this.status = true;
        this.data = data;
    }

    public ResponseData(Integer code, String message, Boolean status, T data){
        this.code = 200;
        this.message = message;
        this.status = true;
        this.data = data;
    }
}

