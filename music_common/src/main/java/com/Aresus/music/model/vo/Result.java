package com.Aresus.music.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result <T> implements Serializable {
    private boolean success;
    private String msg;
    private Integer code;
    private T Result;

    public static <T> Result<T> success(){
        return new Result<>(true, "success", 200, null);
    }

    public static <T> Result<T> success(T result){
        return new Result<>(true, "success", 200, result);
    }

    public static <T> Result<T> fail(Integer code, String message){
        return new Result<>(false, message, code, null);
    }

    public static <T> Result<T> fail(){
        return new Result<>(false, "fail", -999, null);
    }

}
