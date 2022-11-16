import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try(var s = new ServerSocket(1011)) {
            int i = 1;

            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                var t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}