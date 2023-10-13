package com.corporation8793.wifiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

//    private String html = "";
//    private Handler handler;

    private Socket socket;

//    private BufferedReader networkReader;
//    private BufferedWriter networkWriter;

    private String ip = "192.168.4.100";
    private int port = 1234;

    private InputStream inputStream;
    private OutputStream outputStream;

    private DataOutputStream writeSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socket = new Socket();

        new Thread(() -> {
            try {
                socket.connect(new InetSocketAddress(ip, port));
                Log.e("testtest", socket.isConnected()+"");

                writeSocket = new DataOutputStream(socket.getOutputStream());

//                inputStream = socket.getInputStream();
//                outputStream = socket.getOutputStream();

                byte[] byteArr = null;
                String msg = "0x1C";

                byteArr = msg.getBytes();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(byteArr);
                objectOutputStream.flush();

                Log.e("testtest", "ok3");

                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Log.e("testtest", objectInputStream.readObject()+"");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("testtest", e+"");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

//            try {
//                byte[] byteArray = null;
//                String message = "0x1C";
//
//                byteArray = message.getBytes();
//                writeSocket.write(byteArray);
//                writeSocket.flush();
//
//                Log.e("testtest", "ok");
//
//                byte[] byteArr = null;
//                String msg = "0x1C";
//
//                byteArr = msg.getBytes("UTF-8");
//                outputStream.write(byteArr);
//                outputStream.flush();
//
//                Log.e("testtest", "ok2");
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.e("testtest", e+"");
//            }
        }).start();

//        handler = new Handler();
//
//        try {
//            setSocket(ip, port);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        checkUpdate.start();
//
//        PrintWriter printWriter = new PrintWriter(networkWriter, true);
//        printWriter.println("test");
    }

//    private Thread checkUpdate = new Thread() {
//        public void run() {
//            try {
//                String line;
//
//                while (true) {
//                    line = networkReader.readLine();
//                    html = line;
//                    handler.post(showUpdate);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };
//
//    private Runnable showUpdate = new Runnable() {
//        @Override
//        public void run() {
//            Log.e("testtest", "Coming word : " + html);
//        }
//    };
//
//    public void setSocket(String ip, int port) throws IOException {
//        new Thread(() -> {
//            try {
//                socket = new Socket(ip, port);
//                networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            //inputStream.close();
            //outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}