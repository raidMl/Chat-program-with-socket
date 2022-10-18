/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Developer
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Server {
      private DatagramSocket ds;
    private InetAddress inetAddress;
    private byte[] buffer=new byte[256];
    private byte[] buffer1;

 public Server(DatagramSocket ds,InetAddress inetAddress){
  this.ds=ds;
        this.inetAddress=inetAddress; }
 
 public void reciveThenSend(){
     while (true) {         
         try {
             DatagramPacket dp=new DatagramPacket(buffer, buffer.length);
             ds.receive(dp);
             
            
             String messageFromClient=new String(dp.getData(),0,dp.getLength());
             System.out.println("CLIENT SAYS: "+messageFromClient);
                inetAddress=dp.getAddress();
             int port=dp.getPort();
            // ds.close();

              Scanner scanner=new Scanner(System.in);
             String s1=scanner.nextLine();
              buffer1=s1.getBytes();
              DatagramPacket dp1=new DatagramPacket(buffer1, buffer1.length,inetAddress,port);
  
            // System.out.println("ME : "+s1);
             ds.send(dp1);
         } catch (Exception e) {
             break;
         }
     }
 
 
 }
    public static void main(String[] args) throws SocketException, UnknownHostException   {
        DatagramSocket ds=new DatagramSocket(1555);
         InetAddress inetAddress=InetAddress.getByName("255.255.255.255");//in other pc add his ip


        Server server=new Server(ds, inetAddress);
                System.out.println("*** SERVER SIDE ***");

        server.reciveThenSend();
    }
    
}
