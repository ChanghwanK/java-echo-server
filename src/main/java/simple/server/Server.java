package simple.server;

public class Server {

    private final int port;

    public Server(final int port) {
        this.port = port;
    }

    public void start() {
        Task task = new Task();
        Thread taskThread = getRunThread(task);
        taskThread.start();
    }

    private Thread getRunThread(Task task) {
        System.out.println("====== server.Task Start ====");
        return new Thread(task);
    }
}
