package com.example.demo.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value="/websocket")
@Component
public class WebSocketHandler {

	private static CopyOnWriteArraySet<WebSocketHandler> webSocketSet = 
			new CopyOnWriteArraySet<WebSocketHandler>();
	
	private Session session;
	
	public static void sendInfo(String message) {
		for(WebSocketHandler socket : webSocketSet) {
			try {
				socket.sendMessage(message);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		System.out.println("有新连接加入！当前在线人数为："+webSocketSet.size());
		try {
			sendMessage("当前共有"+webSocketSet.size()+"位用户在线");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		System.out.println("有一个连接关闭！当前在线人数为："+webSocketSet.size());
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		System.out.println("来自客户端的消息:"+message);
		
		sendInfo(message);
	}
	
	@OnError
	public void onError(Session session,Throwable error) {
		System.out.println("发生错误！");
		error.printStackTrace();
	}
	
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
}
