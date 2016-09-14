package com.example.estudiante.integracionredesandroid;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by estudiante on 14/09/16.
 */
public class Conexion extends Observable implements Runnable {

    private static Conexion c = new Conexion();
    private DatagramSocket socket;
    private InetAddress dir;
    private int port;


    private Conexion() {
        port = 5000;
        try {
            dir = InetAddress.getByName("192.168.115.30");
            socket = new DatagramSocket();

            System.out.println("-------------------------- se creó");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstance() {
        return c;
    }

    public void enviar(Object ob){



        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(ob);
            obs.flush();
            obs.close();
            DatagramPacket packet = new DatagramPacket(out.toByteArray(),out.toByteArray().length,dir,port);
            socket.send(packet);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("borren esto");

    }

    public void recibir() throws IOException, ClassNotFoundException {
        byte[] buffer;
        DatagramPacket packet;
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        deserializar(packet);
    }

    private void deserializar(DatagramPacket packet) throws IOException, ClassNotFoundException {

        ByteArrayInputStream by = new ByteArrayInputStream(packet.getData());
        ObjectInputStream obs = new ObjectInputStream(by);
        setChanged();
        Object ob = obs.readObject();
        notifyObservers(ob);
    }

    @Override
    public void run() {
        while (true) {


            try {

                recibir();
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
