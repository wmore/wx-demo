package com.zhouzhi.wangyue.websocket;

import com.alibaba.fastjson.JSONObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;

public class MidWebSocketClient {
    static Socket socket = null;

    public static void connect() throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.transports = new String[]{"websocket"};
        //失败重试次数
        options.reconnectionAttempts = 10;
        //失败重连的时间间隔
        options.reconnectionDelay = 1000;
        //连接超时时间(ms)
        options.timeout = 500;

        socket = IO.socket("http://localhost:10015", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(" mid responseClient connect");
            }

        }).on("message_error", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("has message_error");
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket.EVENT_CONNECT_ERROR");
                disconnect();
            }
        }).on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket.EVENT_CONNECT_TIMEOUT");
                disconnect();
            }
        }).on(Socket.EVENT_PING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket.EVENT_PING");
            }
        }).on(Socket.EVENT_PONG, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Socket.EVENT_PONG");
            }
        }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("-----------接受到消息啦--------" + Arrays.toString(args));
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("客户端断开连接啦。。。");
                disconnect();
            }

        }).on("finish", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("receive finish from mid server:");
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }
            }
        });

        socket.connect();

    }

    public static void myEmit(JSONObject obj) {
        socket.emit("socket.io", obj);
    }

    public static void disconnect() {
        socket.disconnect();
    }

    public static void sendDemo(String content) {
        JSONObject obj = new JSONObject();
        obj.put("content", content);
        obj.put("ws_event", "wangyue_event");
        obj.put("emitType", "wangyue_test");

        myEmit(obj);
    }


    public static void main(String[] args) {
        try {
            connect();
            socket.emit("wangyue_test", "1. hello responseClient!" + new Date());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
