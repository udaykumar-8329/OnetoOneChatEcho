package com.uday.incampus_onetoone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class MainActivity extends AppCompatActivity {

    String URL = "ws://192.168.43.238:8999";
    private OkHttpClient client;
    private WebSocket webSocket;
    Button chat;
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        username = findViewById(R.id.username);
        chat = findViewById(R.id.chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDetails.setName(username.getText().toString());
                startActivity(new Intent(MainActivity.this,ChatActivity.class));
            }
        });
    }
//
//    private void getMessages() {
//        String url = "http://192.168.43.238:8999/messages/user3/user4";
//
//        OkHttpClient client = new OkHttpClient();
//
////        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
//        Request request = new Request.Builder()
//                .url(url)
//                .get()
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                final String mMessage = e.getMessage().toString();
//                Log.w("failure Response", mMessage);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String mMessage = response.body().string();
//                Log.e("TAG", mMessage);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//        });
//    }
//
//    void initializeSocketListener(){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(URL).build();
//        webSocket = client.newWebSocket(request,new SocListener());
//        client.dispatcher().executorService().shutdown();
//    }
//
//    public void postRequest() throws IOException {
//
//        MediaType MEDIA_TYPE = MediaType.parse("application/json");
//        String url = "http://192.168.43.238:3001/messages/user3/user4";
//
//        OkHttpClient client = new OkHttpClient();
//
//        JSONObject postdata = new JSONObject();
//        try {
//            postdata.put("name", "user3");
//            postdata.put("message", "Hi!");
//        } catch(JSONException e){
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                final String mMessage = e.getMessage().toString();
//                Log.w("failure Response", mMessage);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //call.cancel();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String mMessage = response.body().string();
//                Log.e("TAG", mMessage);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }
//
//    public class SocListener extends WebSocketListener {
//        @Override
//        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
//            super.onOpen(webSocket, response);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(MainActivity.this, "Connected Successfully!", Toast.LENGTH_SHORT).show();
//                    chat.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            startActivity(new Intent(MainActivity.this, ChatActivity.class));
//                        }
//                    });
//                }
//            });
//
//        }
//        @Override
//        public void onMessage(@NotNull WebSocket webSocket, @NotNull final String text) {
//            super.onMessage(webSocket, text);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        @Override
//        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
//            super.onClosed(webSocket, code, reason);
//        }
//
//        @Override
//        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
//            super.onClosing(webSocket, code, reason);
//        }
//
//        @Override
//        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
//            super.onFailure(webSocket, t, response);
//        }
//    }
}