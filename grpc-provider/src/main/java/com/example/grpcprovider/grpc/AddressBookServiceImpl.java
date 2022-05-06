package com.example.grpcprovider.grpc;

import com.example.grpcprovider.bean.AddressBookEntity;
import com.example.grpcprovider.service.AddressbookService;
import com.github.liuxg.api.grpc.AddressBookProto;
import com.github.liuxg.api.grpc.AddressBookServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.annotation.Resource;

/**
 * @author xinguai.liu
 */
@Slf4j
@GrpcService
public class AddressBookServiceImpl extends AddressBookServiceGrpc.AddressBookServiceImplBase {

    @Resource
    private AddressbookService addressbookService;

    @Override
    public void getAddressDetail(AddressBookProto.GetAddressBookRequest request, StreamObserver<AddressBookProto.GetAddressBookResponse> responseObserver) {

        final AddressBookEntity bookEntity = addressbookService.getAddressBook(request.getId(), request.getName());
        final AddressBookProto.GetAddressBookResponse.Builder builder = AddressBookProto.GetAddressBookResponse.newBuilder()
                .setId(bookEntity.getId())
                .setName(bookEntity.getName());
        bookEntity.getPersonList().forEach(personEntity -> {
            AddressBookProto.Person.Builder personBuild = AddressBookProto.Person.newBuilder()
                    .setId(personEntity.getId())
                    .setName(personEntity.getName())
                    .setEmail(personEntity.getEmail());
            personEntity.getPhoneNumbers().forEach(phoneNumberEntity -> {
                AddressBookProto.Person.PhoneNumber.Builder phoneNumberBuilder = AddressBookProto.Person.PhoneNumber.newBuilder();
                phoneNumberBuilder.setNumber(phoneNumberEntity.getNumber())
                                .setTypeValue(phoneNumberEntity.getPhoneType().getValue());
                personBuild.addPhones(phoneNumberBuilder.build());
            });
            builder.addPeople(personBuild.build());
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();

    }
}
