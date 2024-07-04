package com.airline.booking.model;

import java.util.HashMap;
import java.util.Map;
public class ResponseModel {

    private final String status;
    private final String message;
    private final Map<String, Object> data;

    private ResponseModel(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public static class Builder {
        private String status;
        private String message;
        private Map<String, Object> data = new HashMap<>();

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(String key, Object value) {
            this.data.put(key, value);
            return this;
        }

        public ResponseModel build() {
            return new ResponseModel(this);
        }
    }
}
