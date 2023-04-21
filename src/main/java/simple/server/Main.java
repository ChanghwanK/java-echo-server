package simple.server;

public class Main {
    public static void main(String[] args) {
        final int port = 5000;
        Server server = new Server(port);
        server.start();
    }
}
