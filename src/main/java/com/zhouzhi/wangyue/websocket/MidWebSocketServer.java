package com.zhouzhi.wangyue.websocket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

public class MidWebSocketServer {
    static SocketIOClient responseClient = null;

    public static void responseClient(String event, String data) {
        if (responseClient != null) {
            responseClient.sendEvent(event, data + "bye bye");
        }
    }

    public static void main(String[] args) {
        try {
            WebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10015);
        SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("中转server: " + socketIOClient.getRemoteAddress() + "客户端连接成功");

            }
        });

        //添加sub事件
        server.addEventListener("wangyue_test", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackRequest) throws Exception {
                String c = client.getRemoteAddress().toString();
                //获取客户端url参数
                Map params = client.getHandshakeData().getUrlParams();
                System.out.println("中转server: " + c + "：客户端：收到消息->" + data);

                WebSocketClient.sendDemo(data + "2. hello server." + new Date());
                //sub事件成功反馈
//                responseClient.sendEvent("finish", c + "bye bye");
                responseClient = client;

            }
        });

        //添加客户端断开连接事件
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                String c = socketIOClient.getRemoteAddress().toString();
                //获取设备ip
                String clientIp = c.substring(1, c.indexOf(":"));
                System.out.println("中转server: " + clientIp + "-------------------------" + "客户端已断开连接");
            }
        });
        server.start();
    }
}
