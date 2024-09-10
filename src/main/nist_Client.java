package echoService;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.ByteBuffer;

public class nist_Client {
    public static void main(String[] args) throws Exception {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please specify <serverIP> ");
        String serverip = keyboard.nextLine();
        System.out.println("Server port");
        int serverPort= Integer.parseInt(keyboard.nextLine());
        nist_Client client = new nist_Client();
        client.client(serverip,serverPort);




    }

    public void client(String serverip,int serverPort_num) throws IOException {
        InetAddress serverIP=InetAddress.getByName(serverip);

        String message="";


        DatagramSocket socket=new DatagramSocket();
        DatagramPacket request= new DatagramPacket(
                message.getBytes(),
                message.getBytes().length,serverIP, serverPort_num
        );
        socket.send(request);

        DatagramPacket reply=new DatagramPacket(
                new byte[1024],
                1024
        );
        socket.receive(reply);
        socket.close();

        byte[]serverMessage= Arrays.copyOf(
                reply.getData(),
                reply.getLength()
        );
        int value = ByteBuffer.wrap(serverMessage).getInt();
        long unsigned_value = Integer.toUnsignedLong(value);
        System.out.println(new String(String.valueOf(unsigned_value)));




    }
}
