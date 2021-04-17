/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.login;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Moi
 */
@ServerEndpoint("/login")
public class Socket_login {
    Controller controller = new Controller();
    @OnOpen
    public void onOpen(Session session){
        System.out.println("Socket_login: "+session.getId() + "nueva conexion");
        try {
            //session.getBasicRemote().sendText("Conexion establecida");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @OnMessage
    public void onMessage(String message, Session session){
        try {
            session.getBasicRemote().sendText(controller.existeUsuario(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @OnClose
    public void onClose(Session session){
        System.out.println("Socket_login: Sesion "+ session.getId()+" terminada");
    }
    
}
