import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	//takes broadcast from port 14456 and prints it to a webpage

	public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(14456);
            Socket cs;
            while (true) {
                cs = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                String coords = new String(in.readLine());
                System.out.println(coords);
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("../htdocs/latlong.txt"), "utf-8"))) {
                	writer.write(coords);
                }
            }
        } catch (IOException e) {
        }

	}

}
