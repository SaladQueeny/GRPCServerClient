package org.example;

import com.example.grpc.WorkWithDataServiceGrpc;
import com.example.grpc.WorkWithDataServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();

        //спец объект для удалённого выполнения процедур
        WorkWithDataServiceGrpc.WorkWithDataServiceBlockingStub stub = WorkWithDataServiceGrpc.newBlockingStub(channel);

        //запрос
        WorkWithDataServiceOuterClass.SimpleRequest request = WorkWithDataServiceOuterClass.SimpleRequest.newBuilder().setName("Egor").build();

        //для единичного responce
        //GreetingServiceOuterClass.HelloResponce responce = stub.greeting(request);
        //System.out.println(responce);

        //для потока данных нужно принимать итератор и идти по нему пока сервер отправляет данные
        Iterator<WorkWithDataServiceOuterClass.SimpleResponce> responce = stub.processing(request);

        while(responce.hasNext()){
            System.out.println(responce.next());
        }

        channel.shutdownNow();
    }
}
