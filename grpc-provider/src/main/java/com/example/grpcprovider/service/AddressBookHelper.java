package com.example.grpcprovider.service;

import com.example.grpcprovider.bean.AddressBookEntity;
import com.example.grpcprovider.bean.PersonEntity;
import com.example.grpcprovider.bean.PhoneNumberEntity;

import java.util.ArrayList;

/**
 * @author xinguai.liu
 */

public class AddressBookHelper {

    public static AddressBookEntity getAddressBook(int id, String name) {
        AddressBookEntity addressBook = new AddressBookEntity();
        addressBook.setId(id);
        addressBook.setName(name);
        ArrayList<PersonEntity> persons = new ArrayList<>();
        addressBook.setPersonList(persons);
        PersonEntity person = new PersonEntity();
        person.setId(10000);
        person.setName("10000-name");
        person.setEmail("xinguai.liu@xxx.com");
        persons.add(person);
        ArrayList<PhoneNumberEntity> phoneNumbers = new ArrayList<>();
        person.setPhoneNumbers(phoneNumbers);
        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();
        phoneNumberEntity.setNumber("15579124310");
        phoneNumberEntity.setPhoneType(PhoneNumberEntity.PhoneType.MOBILE);
        phoneNumbers.add(phoneNumberEntity);
        return addressBook;
    }
}
