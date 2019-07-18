package com.zhouzhi.wangyue.websocket;

import com.alibaba.fastjson.JSONObject;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.HashMap;

public class Test {

    public static void connect2() throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.path = "/socket/socket.io";
        Socket socket = IO.socket("http://localhost:3000", options);
        JSONObject obj = new JSONObject();
        obj.put("ws_event","barrage_message");
        obj.put("ws_group", new String[]{"0_teacherRoom_1"});
        obj.put("emitType", "emit_group");
        obj.put("ws_data", new HashMap<String, String>(){{
            put("content", "啊啊啊，我错过");
            put("colorType", "2");
        }});
        socket.emit("socket.io", obj);

//        ws_event: "barrage_message", ws_group: Array(1), ws_data: {…}, emitType: "emit_group"}
    }

    public static void connect() {
        try {
            IO.Options options = new IO.Options();
            options.path = "/socket/socket.io";
            final Socket socket = IO.socket("http://localhost:3000", options);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    socket.emit("foo", "hi");
                    socket.disconnect();
                }

            }).on("event", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                }

            });
            socket.connect();
            JSONObject obj = new JSONObject();
            obj.put("content", "啊啊啊，我错过");
            obj.put("colorType", "2");
            socket.emit("barrage_message", obj);
            // Receiving an object
            socket.on("barrage_message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject)args[0];
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("finish");

    }

    public static void main(String[] args) {
        try {
            connect2();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
