import exceptions.IncorrectArgumentException;
import exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.AnswerSender;
import request.RequestAcceptor;
import utility.*;

import java.io.IOException;
import java.net.*;
import java.nio.channels.DatagramChannel;

public class App {


    public static void main(String[] args) throws ValidationException, IncorrectArgumentException, IOException {
        final String envVariable = "LABA";
        CollectionManager collectionManager = new CollectionManager();
        FileVehicle fileVehicle = new FileVehicle(collectionManager, envVariable);
        int port = 9898;
        try {
            if (args.length == 1) {
                collectionManager.setCollection(fileVehicle.ReadFile());
                port = Integer.parseInt(args[0]);
            } else {
                System.out.println("Incorrect command line arguments. Please, follow the format: \"file_path port\".");
                return;
            }
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect format of port.");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> fileVehicle.writeDocument(collectionManager.getCollection())));
        if (port == 9898) {
            System.out.println("Port hasn't been identified. " + port + " will be used.");
        }
        SocketAddress Host = new InetSocketAddress(port);
        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket socket = channel.socket();
        socket.bind(Host);
        System.out.println(socket.getLocalSocketAddress());
        Logger logger = LoggerFactory.getLogger("Server");
        AnswerSender answerSender = new AnswerSender(logger, channel);
        answerSender.setSocketAddress(socket.getLocalSocketAddress());
        VehicleFactory vehicleFactory = new VehicleFactory();
        vehicleFactory.setStartId(collectionManager.getLastId());
        Receiver receiver = new Receiver(collectionManager, answerSender, vehicleFactory);
        Invoker invoker = new Invoker(receiver);
        invoker.initMap();
        RequestAcceptor requestAcceptor = new RequestAcceptor(vehicleFactory, logger, invoker, answerSender);
        requestAcceptor.acceptRequest(socket);
        try {
            logger.info("Server started on address " + InetAddress.getLocalHost() + " port: " + port);
            System.out.println("Server started on address " + InetAddress.getLocalHost() + " port: " + port);
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        }
    }
}