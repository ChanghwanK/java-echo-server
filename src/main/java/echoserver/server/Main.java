package echoserver.server;

public class Main {

    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer("localhost", 10, 5000);
        echoServer.serverStart();
    }
}
