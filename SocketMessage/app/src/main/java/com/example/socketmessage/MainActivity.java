package com.example.socketmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Kết nối tới server Socket.IO
            socket = IO.socket("http://127.0.0.1");
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // Lắng nghe sự kiện "message" từ App A
        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                // Nhận thông tin từ App A và hiển thị thông báo dạng Toast
                final String message = (String) args[0];
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}