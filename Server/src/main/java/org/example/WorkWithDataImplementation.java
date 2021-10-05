package org.example;

import com.example.grpc.WorkWithDataServiceGrpc;
import com.example.grpc.WorkWithDataServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class WorkWithDataImplementation extends WorkWithDataServiceGrpc.WorkWithDataServiceImplBase {
    @Override
    public void processing(WorkWithDataServiceOuterClass.SimpleRequest request, StreamObserver<WorkWithDataServiceOuterClass.SimpleResponce> responceObserver){
        //Сообщение которое приходит от клиента
        System.out.println(request);
        //request.getValueList();
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //отсылаем данные
            WorkWithDataServiceOuterClass.SimpleResponce responce = WorkWithDataServiceOuterClass.SimpleResponce.newBuilder().setData("Hello from server "+request.getName()).build();

            responceObserver.onNext(responce);
        }

        //заканчиваем посылку данных
        responceObserver.onCompleted();
    }
}
