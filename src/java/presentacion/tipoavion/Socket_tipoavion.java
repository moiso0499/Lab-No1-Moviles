/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.tipoavion;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author Moi
 */
@ServerEndpoint("/tipoavion")
public class Socket_tipoavion {

    Controller controller = new Controller();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket_tipoavion: " + session.getId() + " nueva conexion");
        try {
            session.getBasicRemote().sendText(controller.enviarListaTipoAvion());
            session.getBasicRemote().sendText(controller.enviarListaRuta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(message, Map.class);
            JSONObject req = new JSONObject(map);
            String action = req.get("action").toString();
            switch (action) {
                case "agregarTipoavion": {
                    controller.agregarTipoavion(req.get("data").toString());
                    session.getBasicRemote().sendText(controller.enviarListaTipoAvion());
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Socket_tipoavion: Sesion " + session.getId() + " terminada");
    }
}
