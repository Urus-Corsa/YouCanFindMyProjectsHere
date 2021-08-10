
import java.io.*;
import java.net.Socket;
import java.util.*;

public class Client {
    private Socket client;
    private final int PORT = 5034;
    private DataInputStream in;
    private DataOutputStream out;

    private int ack;
    private final int FRAME_SIZE = 1000; //server will calculate good-put for every 1000 sequences
    private final String END = "end";
    private final String RETRANSMISSION = "RET:";
    private final int Max_SEQ_NUM = 10000000; //maximum sequence number possible
    private int Sequence_Number = 10000000; //our packet size
    private int counter = 0;
    List<Integer> missed_packets = new ArrayList<>();
    private int missed_sum = 0;

    public void start() {
        try{
            //connect to server
            client = new Socket("127.0.0.1", PORT);
            //initialize socket input and output stream
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());

            //check connection
            out.writeUTF("network");
            String message = in.readUTF();
            if(message.equals("success")) {
                print("Connection is success.");
            } else {
                print("Connection is fail.");
                close();
                return;
            }
            int sequence_num = 1;
            int frame_num = 0;

            while(sequence_num <= Sequence_Number) {
                String frame = getSequence();
                print(FRAME_SIZE + " packets sent....");
                out.writeUTF(frame);
                ack = in.readInt();

                //receive ack and confirm missed packet
                int missed = FRAME_SIZE - ack;
                missed_sum += missed;
                print(missed + " packet(s) are dropped");
                double good_put = (double) missed / FRAME_SIZE;
                print("Good-Put: " + good_put);
                print("");

                //adjust sequence
                sequence_num += FRAME_SIZE;
                frame_num++;
                if(frame_num % 10 == 0){
                    //retransmission every 10000 sequences
                    if(!missed_packets.isEmpty()){
                        print("Retransmission " + missed_packets.size() + " packet(s)");
                        out.writeUTF(RETRANSMISSION + getSequence(missed_packets));
                        missed_packets.clear();
                        ack = in.readInt();
                    }
                }
            }



            //end transmission
            out.writeUTF(END);

            //report average good-put
            print("");
            print("------------------------------------------------------");
            print("Average Good-Put: " + (1 - ((double)missed_sum / Sequence_Number)));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
    }



    //get sequence string frame with 1 % drop probability
    private String getSequence(){
        Random rand = new Random();
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < FRAME_SIZE; i++){
            //drop package as 1 % probability
            if(rand.nextInt(100) == 0){
                missed_packets.add(i);
                continue;
            }
            out.append(i).append(",");
        }
        return out.toString().replaceAll(",$", "");
    }


    private String getSequence(List<Integer> list){
        Random rand = new Random();
        StringBuilder out = new StringBuilder();
        for(int item : list){
            out.append(item).append(",");
        }
        return out.toString().replaceAll(",$", "");
    }



    //print message
    private void print(String message){
        System.out.println(message);
    }



    //close connection method
    private void close(){
        try{
            in.close();
            out.close();
            client.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //Main Method
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
