package com.getir.intw.books.rest;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Response implements Serializable {

    private String status;
    private Object data;

    public static Response success(Object data) {
        Response r = new Response();
        r.status = "SUCCESS";
        r.data = data;

        return r;
    }

    public static Response fail(String errorMessage) {
        Response r = new Response();
        r.status = "ERROR";
        r.data = errorMessage;

        return r;
    }
}
