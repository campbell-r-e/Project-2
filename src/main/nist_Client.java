package echoService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class nist_Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Please specify <serverIP> and <serverPort>");
        Scanner keyboard = new Scanner(System.in);
        String serverip = keyboard.nextLine();
        int serverPort= Integer.getInteger(keyboard.nextLine());
        nist_Client client = new nist_Client();
        client.client(serverip,serverPort);




    }

    public void client(String serverip,int serverPort_num) throws IOException {
        InetAddress serverIP=InetAddress.getByName(serverip);
        int serverPort=serverPort_num;
        Scanner console=new Scanner(System.in);
        String message=console.nextLine();


        DatagramSocket socket=new DatagramSocket();
        DatagramPacket request= new DatagramPacket(
                message.getBytes(),
                message.getBytes().length,serverIP, serverPort
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
        System.out.println(new String(serverMessage));




    }
}
