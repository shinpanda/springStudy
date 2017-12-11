package com.newlecture.webapp.chat;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/resource/chat-server")
public class ChatServer {
	
	private static Set<Session> sessionAll = Collections.synchronizedSet(new HashSet<Session>()); // 얘를 쓸 때 잠금장치를 걸어줌 1명에 한명씩 만들어짐
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		sessionAll.add(session);
		//this.session = session;
		//session.getBasicRemote()
		System.out.print("aaaaaaaa");
	}
	
	@OnMessage
	public void onTextMessage(Session session, String data) throws IOException {
		for(Session s:sessionAll) {
			s.getBasicRemote().sendText(data);
		}
		System.out.println(session.getId());
		System.out.println(data);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		sessionAll.remove(session);
		for(Session s:sessionAll) {
			s.getBasicRemote().sendText("누군가가 접속을 끊었습니다");
		}
	}
}
