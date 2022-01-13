package br.com.erudio.exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }


    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    

}
