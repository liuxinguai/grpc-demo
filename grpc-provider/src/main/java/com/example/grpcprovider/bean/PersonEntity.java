package com.example.grpcprovider.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xinguai.liu
 */
@Data
public class PersonEntity implements Serializable {

    private int id;

    private String name;

    private String email;

    private List<PhoneNumberEntity> phoneNumbers;
}
