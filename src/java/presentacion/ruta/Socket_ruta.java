/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.ruta;

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
@ServerEndpoint("/ruta")
public class Socket_ruta {

    Controller controller = new Controller();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket_ruta: " + session.getId() + " nueva conexion");
        try {
            session.getBasicRemote().sendText(controller.enviarListaUbicacion());
            session.getBasicRemote().sendText(controller.enviarListaRuta());
        } catch (Exception e) {
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
                case "agregarUbicacion": {
                    controller.agregarUbicacion(req.get(req).toString());
                    session.getBasicRemote().sendText(controller.enviarListaUbicacion());
                    break;
                }
                case "agregarRuta":{
                    controller.agregarRuta(req.get("data").toString(), req.get("duracion").toString());
                    session.getBasicRemote().sendText(controller.enviarListaRuta());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Socket_ruta: Sesion " + session.getId() + " terminada");
    }
}
