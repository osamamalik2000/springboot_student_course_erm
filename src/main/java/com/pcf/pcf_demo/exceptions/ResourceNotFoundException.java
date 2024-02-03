package com.pcf.pcf_demo.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    final String resourceName;   
    final String filedName;
    final int filedValue;

    public ResourceNotFoundException(String resourceName, String filedName, int filedValue) {
        super(String.format("%s not found with %s : %s", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }
}
