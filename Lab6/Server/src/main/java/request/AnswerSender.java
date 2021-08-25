package request;

import data.Vehicle;
import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Stack;


public class AnswerSender {
    private final Logger logger;
    private SerializationForClient answer;
    private SocketAddress socketAddress;
    DatagramChannel datagramChannel;

    public AnswerSender(Logger logger, DatagramChannel datagramChannel) {
        this.logger = logger;
        this.datagramChannel = datagramChannel;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public void addToAnswer(boolean status, String message, Long count, Stack<Vehicle> vehicles) {
        answer = new SerializationForClient(status, message, count, vehicles);
    }


    public void sendAnswer() {
        if (answer == null) {
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(answer);
            objectOutputStream.flush();
            ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            datagramChannel.send(buffer,socketAddress);
            logger.info("Answer has been sent to " + socketAddress);
        } catch (IOException exception) {
            logger.info("Failed sending answer." + exception.getMessage() + exception.getCause());
            exception.printStackTrace();
        }
        answer = null;
    }
}