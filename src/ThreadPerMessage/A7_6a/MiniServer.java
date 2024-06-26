package ThreadPerMessage.A7_6a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {
    private final int portnumber;
    public MiniServer(int portnumber) {
        this.portnumber = portnumber;
    }
    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portnumber);
        try {
            while (true) {
                System.out.println("Accepting...");
                final Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to " + clientSocket);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Service.service(clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
