package com.za.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/hephaistoswebserver", encoders = { SensorMessageEncoder.class}, decoders = {SensorMessageDecoder.class})
public class HephaistosWebServer {
	static Set<Session> sessionClient = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void handleOpen(Session client){
		System.out.println("[Un Client s'est connecté]");
		sessionClient.add(client);
	}
	
	@OnMessage
	public void handleMessage(SensorMessage incomingSensorMessage, Session client) throws IOException, EncodeException{
		String username = (String) client.getUserProperties().get("username");
		SensorMessage outgoingSensorMessage = new SensorMessage();
		
		// Si l'utilisateur n'existe pas on le créer
		if(username ==null){
			client.getUserProperties().put("username",incomingSensorMessage.getMessage());
			outgoingSensorMessage.setName("Serveur");
			outgoingSensorMessage.setMessage("Vous êtes connecté en tant que : " + incomingSensorMessage.getMessage());
			System.out.println("[Authentification du client "+incomingSensorMessage.getMessage()+"]");
			client.getBasicRemote().sendObject(outgoingSensorMessage);
		}else{
			outgoingSensorMessage.setName(username);
			outgoingSensorMessage.setMessage(incomingSensorMessage.getMessage());
			Iterator<Session> iterator = sessionClient.iterator();
			
			// On renvoie le message recu à toutes les sessions clients
			while(iterator.hasNext()) {
				iterator.next().getBasicRemote().sendObject(outgoingSensorMessage);
			}
		}
	}

	@OnClose
	public void handleClose(Session client) throws IOException, EncodeException{
		System.out.println("[Le Client "+ (String) client.getUserProperties().get("username") + " s'est déconnecter]");
		sessionClient.remove(client);
	}
}
