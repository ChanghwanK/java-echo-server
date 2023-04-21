package echoserver.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final String hostAddress;
    private final int port;

    public EchoServer(
            final String hostAddress,
            final int threadPoolSize,
            int port) {
        this.hostAddress = hostAddress;
        this.port = port;

        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }


    void serverStart() {
        System.out.println("server start");

        Thread thread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress(hostAddress, port));
                while(true) {
                    // 대기중인 큐에서 Task를 가져옴
                    Socket socket = serverSocket.accept();
                    System.out.println("socket accepted");

                    executorService.execute(() -> {
                        try {
                            System.out.println("thread: " + Thread.currentThread().getName());
                            JsonNode jsonNode = objectMapper.readTree(socket.getInputStream());
                            System.out.println("data: " + jsonNode);

                            Thread.sleep(20000);
                            socket.close();
                        } catch(IOException e) {
                            throw new RuntimeException(e);
                        } catch(InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }

    void serverEnd() {
        try {
            serverSocket.close();
            executorService.shutdownNow();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
