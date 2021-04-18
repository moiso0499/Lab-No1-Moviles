/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.simple.JSONObject;

/**
 *
 * @author Moi
 */
@ServerEndpoint("/usuario")
public class Socket_usuario {

    Controller controller = new Controller();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket_usuario: " + session.getId() + " nueva conexion");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(message, Map.class);
            JSONObject req = new JSONObject(map);
            String action = req.get("action").toString();
            switch (action) {
                case "ingresarUsuario": {
                    session.getBasicRemote().sendText(controller.ingresarUsuario(req.get("data").toString()));
                }
            }
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Socket_usuario: Sesion " + session.getId() + " terminada");
    }
}
