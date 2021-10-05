package org.example;


import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class App {

    private static final Logger logger=Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new WorkWithDataImplementation()).build();
        server.start();
        System.out.println("Server started");
        logger.info("Server started");
        //метод будет работать пока сервер будет работать
        server.awaitTermination();
    }
}
