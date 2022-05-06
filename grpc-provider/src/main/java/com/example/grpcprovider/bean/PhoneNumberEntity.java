package com.example.grpcprovider.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author xinguai.liu
 */
@Data
public class PhoneNumberEntity implements Serializable {

    private String number;

    private PhoneType phoneType;

    @Getter
    @AllArgsConstructor
    public enum PhoneType {
        MOBILE(0),

        HOME(1),

        WORK(2);
        private final int value;

    }


}
