package com.example.mydemo.exceptions;

import lombok.Builder;

@Builder
public class ExternalException {
    private String message;
    private int code;
}
