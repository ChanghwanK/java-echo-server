package echoserver.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.HashMap;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("connected");


            HashMap<String, Object> data = new HashMap<>();

            data.put("message", "hello");
            data.put("name", "Martin");

            OutputStream socketOutputStream = socket.getOutputStream();

            ObjectMapper objectMapper = new ObjectMapper();

            byte[] jsonBytes = objectMapper.writeValueAsBytes(data); // HashMap 객체를 JSON 바이트 배열로 변환

            socketOutputStream.write(jsonBytes); // OutputStream에 JSON 바이트 배열 쓰기

            System.out.println("send message");
            socket.close();

        } catch(UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
