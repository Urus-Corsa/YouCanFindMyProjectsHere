/*
Name: Houman Irani
Name: Sina Kalantar
Term project: CS 158A
Professor: Navrati Saxena
Description: Simple TCP Connection between server and client with Good_Put calculations.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket server;        //Socket for server
    private Socket client;              //Socket for client
    private final int PORT = 5034;
    private DataInputStream in;
    private DataOutputStream out;

    private int ack;
    private final int FRAME_SIZE = 1000; //server will calculate good-put for every 1000 sequences
    private final String END = "end";
    private final String RETRANSMISSION = "RET:";

    List<Integer> missed_tracker = new ArrayList<>();

    public void start() {
        try{
            //initialize server
            server = new ServerSocket(PORT);
            print("Server is running from " + Inet4Address.getLocalHost().getHostAddress());
            //get connected client socket
            print("Listening client connection....");
            client = server.accept();
            print("Client is connected from " + client.getInetAddress().getHostAddress());
            print("");

            //get socket input and output stream
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            //confirm client connection
            String message = in.readUTF();
            if(message.equals("network")){
                //connection is established
                out.writeUTF("success");
            }else{
                print("Connection is fail...");
                close();
                return;
            }

            //start to receive from server
            while(true){
                String received_packet = in.readUTF();
                if(received_packet.equals(END)){
                    break;
                }
                String[] splits = received_packet.split(",");
                ack = splits.length;
                if(received_packet.startsWith(RETRANSMISSION)){
                    //this is retransmitted packet
                    out.writeInt(ack);
                    continue;
                }
                print("Server receive " + ack + " packets.");
                if(ack != FRAME_SIZE){
                    //packet is loss
                    //server track missed packet
                    missed_tracker.clear();
                    List<Integer> sequence = getSequence(splits);
                    for(int i = 0; i < FRAME_SIZE; i++){
                        if(!sequence.contains(i)){
                            missed_tracker.add(i);
                        }
                    }
                    print("Missed packet: " + missed_tracker);
                }
                //send ack
                out.writeInt(ack);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            close();
        }
    }


    //get number sequence from packet
    private List<Integer> getSequence(String[] packet){
        if(packet == null || packet.length == 0) return null;
        List<Integer> sequence = new ArrayList<>();
        for(String item : packet){
            try{
                sequence.add(Integer.parseInt(item));
            }catch (Exception e){

            }
        }
        return sequence;
    }


    //close connection
    private void close(){
        try{
            in.close();
            out.close();
            client.close();
            server.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //print message method
    private void print(String message){
        System.out.println(message);
    }



    //Main Method
    public static void main(String[] args) {
        Server server = new Server();
        server.start();

    }

}
