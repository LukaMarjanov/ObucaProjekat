/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Obuca;
import domain.Prodavac;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Luka
 */
public class ThreadClient extends Thread {
    
    Socket socket;
    
    public ThreadClient(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                //primamo klijentski zahtev
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) ois.readObject();

                //Switch case
                Response response = handleRequest(request);

                //saljemo odgovor klijentu
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(response);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.LOGIN:
                    Prodavac prodavac = (Prodavac) request.getData();
                    Prodavac pro = ServerController.getInstance().login(prodavac);
                    response.setData(pro);
                    break;
                case Operation.GET_ALL_OBUCA:
                    response.setData(ServerController.getInstance().getAllObuca((Obuca) request.getData()));
                    break;
                
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setExc(e);
        }
        return response;
    }
    
}
