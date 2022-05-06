package com.github.liuxg.example.grpcconsumer.controller;

import com.github.liuxg.api.grpc.AddressBookProto;
import com.github.liuxg.api.grpc.AddressBookServiceGrpc;
import com.github.liuxg.example.grpcconsumer.entity.AddressBookEntity;
import com.github.liuxg.example.grpcconsumer.entity.PersonEntity;
import com.github.liuxg.example.grpcconsumer.entity.PhoneNumberEntity;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("home")
@RestController
public class HelloController {

    @GrpcClient("grpc-provider")
    private AddressBookServiceGrpc.AddressBookServiceBlockingStub addressBookServiceBlockingStub;


    @GetMapping("hello")
    public AddressBookEntity hello(@RequestParam("id") int id, @RequestParam("name") String name) {
        final AddressBookProto.GetAddressBookRequest.Builder builder = AddressBookProto.GetAddressBookRequest.newBuilder().setId(id).setName(name);
        AddressBookProto.GetAddressBookResponse addressDetail = addressBookServiceBlockingStub.getAddressDetail(builder.build());
        AddressBookEntity entity = new AddressBookEntity();
        entity.setId(addressDetail.getId());
        entity.setName(addressDetail.getName());
        List<AddressBookProto.Person> personList = addressDetail.getPeopleList();
        ArrayList<PersonEntity> persons = new ArrayList<>(personList.size());
        personList.forEach(person -> {
            final PersonEntity personEntity = new PersonEntity();
            personEntity.setName(person.getName());
            personEntity.setId(person.getId());
            personEntity.setEmail(person.getEmail());
            List<AddressBookProto.Person.PhoneNumber> phonesList = person.getPhonesList();
            ArrayList<PhoneNumberEntity> phoneNumbers = new ArrayList<>(phonesList.size());
            phonesList.forEach(phoneNumber -> {
                PhoneNumberEntity number = new PhoneNumberEntity();
                phoneNumbers.add(number);
                number.setNumber(phoneNumber.getNumber());
                switch (phoneNumber.getType()) {
                    case HOME:
                        number.setPhoneType(PhoneNumberEntity.PhoneType.HOME);
                        break;
                    case WORK:
                        number.setPhoneType(PhoneNumberEntity.PhoneType.WORK);
                    case MOBILE:
                        number.setPhoneType(PhoneNumberEntity.PhoneType.MOBILE);
                    default:
                }
            });
            personEntity.setPhoneNumbers(phoneNumbers);
            persons.add(personEntity);
        });
        entity.setPersonList(persons);
        return entity;
    }

}
