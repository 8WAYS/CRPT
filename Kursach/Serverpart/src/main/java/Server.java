package server;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.*;

import static cryptor.ADGFVX.Cryptor;

public class Server {
    public static void main(String[] args) {
        System.out.println("server on");
        try {
            int portnumber = 50000;
            DatagramSocket socket = new DatagramSocket(portnumber);

            byte[] buffer = new byte[2048];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);

                Yaml yamlfile = new Yaml();
                if (packet.getData() == null){
                    System.out.print("Error");
                    System.exit(1);
                }
                String recdata = new String(packet.getData(), 0, packet.getLength());
                System.out.println("package delivered\n" + recdata);
                recdata = Cryptor(recdata);

                byte[] bufferB = (recdata).getBytes();

                DatagramPacket packetB = new DatagramPacket(bufferB, bufferB.length, packet.getAddress(), packet.getPort());

                socket.send(packetB);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
