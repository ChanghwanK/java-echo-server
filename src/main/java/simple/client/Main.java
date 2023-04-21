package simple.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.rmi.UnknownHostException;
import java.rmi.activation.UnknownObjectException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("connected");

            OutputStream outputStream = socket.getOutputStream();

            outputStream.write("데이터".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            socket.close();
        } catch(UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
