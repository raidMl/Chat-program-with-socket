/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Developer raid
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client {
    private DatagramSocket ds;
    private InetAddress inetAddress;
    private byte[] buffer;
     private byte[] buffer1=new byte[256];

    public Client(DatagramSocket ds, InetAddress inetAddress){
        this.ds=ds;
        this.inetAddress=inetAddress;
    }
    public void sendThenReceive(){
        while (true) {  
            try {
           Scanner scanner=new Scanner(System.in);
          String messageToSend=scanner.nextLine();
          buffer=messageToSend.getBytes();
          DatagramPacket dp=new DatagramPacket(buffer, buffer.length,inetAddress,1555);
          ds.send(dp);
          //for receive
             DatagramPacket dp1=new DatagramPacket(buffer1, buffer1.length);
          ds.receive(dp1);
           String messageFromServer=new String(dp1.getData(),0,dp1.getLength());
                System.out.println("SERVER SAYS : " + messageFromServer);  
                //ds.close();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

          
        }
        
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        DatagramSocket ds=new DatagramSocket();
        
        //InetAddress inetAddress=InetAddress.getByName("192.168.1.103");//in other pc add his ip
          //    InetAddress inetAddress=InetAddress.getByName("localhost");// in this pc
                  InetAddress inetAddress=InetAddress.getByName("255.255.255.255");//in other pc add his ip


        Client client=new Client(ds, inetAddress);
                System.out.println("*** CLIENT SIDE ***");
    client.sendThenReceive();
    }
}
