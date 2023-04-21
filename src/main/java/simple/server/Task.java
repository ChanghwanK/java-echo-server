package simple.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("init socket");

        try(ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(5000));
            Socket socket = serverSocket.accept();

            System.out.println("socket accepted waiting for request");

            InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

            System.out.println("host.name: " + remoteSocketAddress.getHostName());

            byte [] bytes = new byte[1024];
            int num = socket.getInputStream().read(bytes);
            System.out.println(new String(bytes, 0, num, StandardCharsets.UTF_8));

            socket.close();

            System.out.println("socket close");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
