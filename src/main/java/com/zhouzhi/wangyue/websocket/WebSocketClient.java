package com.zhouzhi.wangyue.websocket;

import com.alibaba.fastjson.JSONObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;

public class WebSocketClient {
    static Socket socket = null;

    public static void connect() throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.path = "/socket/socket.io";
//        http://127.0.0.1:3000/socket
        socket = IO.socket("http://127.0.0.1:2000/socket?ip=127.0.0.1&port=3001", options);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println("connect");
                JSONObject obj = new JSONObject();
                obj.put("ws_group", new String[]{"0_teacherRoom_1"});
                obj.put("emitType", "join_group");

                myEmit(obj);
                System.out.println("myEmit event");
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

        }).on("wangyue_event", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("receive wangyue_event from server:");
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }

                MidWebSocketServer.responseClient("finish", args[0].toString());
            }
        });

        socket.connect();

/*

        JSONObject obj = new JSONObject();
        obj.put("ws_event", "page_tap");
        obj.put("ws_group", new String[]{"0_teacherRoom_1"});
        obj.put("emitType", "emit_group");
        obj.put("ws_data", new HashMap<String, Integer>() {{
            put("snapshotContentId", 1);
        }});
        socket.emit("socket.io", obj);

        obj = new JSONObject();
        obj.put("emitType", "emit_group");
        obj.put("ws_event", "switch_barrage");
        obj.put("ws_group", new String[]{"0_teacherRoom_1"});
        obj.put("ws_data", new HashMap<String, Integer>() {{
            put("open", 1);
        }});
        socket.emit("socket.io", obj);

        obj = new JSONObject();
        obj.put("content", "啊啊啊，我错过");
        obj.put("colorType", "2");
        obj.put("emitType", "emit_group");

        socket.emit("socket.io", obj);
*/


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
            sendDemo("学习强国");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
