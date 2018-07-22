package com.huynh.xinh.data.exception;

import android.os.Build;
import android.support.annotation.RequiresApi;

public class BaseDataException extends RuntimeException {
    public BaseDataException() {
    }

    public BaseDataException(String message) {
        super(message);
    }

    public BaseDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDataException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public BaseDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
