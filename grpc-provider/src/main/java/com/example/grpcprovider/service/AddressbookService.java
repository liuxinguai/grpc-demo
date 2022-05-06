package com.example.grpcprovider.service;

import com.example.grpcprovider.bean.AddressBookEntity;
import org.springframework.stereotype.Service;

/**
 * @author xinguai.liu
 */
@Service
public class AddressbookService {


    public AddressBookEntity getAddressBook(int id, String name) {
        return AddressBookHelper.getAddressBook(id,name);
    }

}
