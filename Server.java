import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String args[]) 
	throws Exception {
		ServerSocket ssock = new ServerSocket(7777);
		System.out.println("Listening on: " + ssock.getLocalPort());
		while (true) {
			Socket sock = ssock.accept();
			System.out.println("Connected " + sock.getPort());
			new Thread(new ServerRunnable(sock)).start();
		}
	}	
}

class ServerRunnable implements Runnable{
	Socket csocket;
	
	ServerRunnable(Socket csocket) {
		this.csocket = csocket;
	}
	
	public void run() {
		try(
            PrintWriter out = new PrintWriter(csocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
        ) {
			String inputLine, outputLine;
            
            // Initiate conversation with client
            Protocol p = new Protocol();
            outputLine = p.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("Bye."))
                    break;
                outputLine = p.processInput(inputLine);
                out.println(outputLine);
                
            }
			System.out.println("Disconnected " + csocket.getPort());
			csocket.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}

