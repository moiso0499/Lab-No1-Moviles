/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.compra;

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
@ServerEndpoint("/compra")
public class Socket_compra {

    Controller controller = new Controller();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Socket_compra: " + session.getId() + " nueva conexion");
        try {
            session.getBasicRemote().sendText(controller.enviarListaUbicacion());
        } catch (Exception e) {
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
                case "filtrarVuelo": {
                    session.getBasicRemote().sendText(controller.filtrarVuelo(req.get("data").toString(), req.get("date").toString()));
                    break;
                }
                case "obtenerTiquetesVuelo": {
                    session.getBasicRemote().sendText(controller.enviarListaTiquetesVuelo(req.get("data").toString()));
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Socket_compra: Sesion " + session.getId() + " terminada");
    }

}
