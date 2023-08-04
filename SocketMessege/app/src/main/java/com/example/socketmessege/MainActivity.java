package com.example.socketmessege;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Kết nối tới server Socket.IO
            socket = IO.socket("http://127.0.0.1"); // Thay thế your-server-url và port bằng địa chỉ và cổng của server Socket.IO
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socket.emit("message", "Hello from App A!");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}