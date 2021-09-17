import request.AnswerReader;
import request.RequestSender;
import utility.CommandReader;
import utility.Console;
import utility.Invoker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 9898;
        InetAddress serverAddress = InetAddress.getByName("localhost");
        try {
            if (args.length != 0 && args[0].contains(":")) {
                serverAddress = InetAddress.getByName(args[0].split(":")[0]);
                port = Integer.parseInt(args[0].split(":")[1]);
            } else {
                System.out.println("Server IP wasn't found. Default value localhost:9898 will be used.");
            }
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
            return;
        } catch (NumberFormatException exception) {
            System.out.println("Incorrect format of port.");
        }
        try {
            SocketAddress socketAddress = new InetSocketAddress(serverAddress, port);
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.connect(socketAddress);
            System.out.println(datagramChannel.getLocalAddress());
            RequestSender requestSender = new RequestSender(datagramChannel, socketAddress);
            AnswerReader answerReader = new AnswerReader(datagramChannel, socketAddress);
            Scanner scanner = new Scanner(System.in);
            Console console = new Console(scanner, answerReader);
            Invoker invoker = new Invoker();
            invoker.initMap(datagramChannel, socketAddress, console, invoker);
            CommandReader commandReader = new CommandReader(console, invoker, answerReader);
            commandReader.activeMode();
        } catch (IOException e) {
            System.out.println("Client can't connect to server, Ip isn't belongs Server");
        }
    }
}
