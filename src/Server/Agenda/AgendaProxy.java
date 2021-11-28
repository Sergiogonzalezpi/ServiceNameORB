package Server.Agenda;

// Client - ProxyAgenda

import Server.Interfaces.IRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AgendaProxy implements IRepository {
    // Inicializacion de host y puerto
    String host = "localHost";
    int port = 9999;
    // declaracion del identificador del objeto
    private int idObj;
    public AgendaProxy() {
        // Inicializacion del socket
        Socket cs = null;
        // Inicializacion de entradas y salidas del cliente
        DataInputStream dis = null;
        DataOutputStream dos = null;
        // Inicializacion del codigo de creacion de objeto
        // tipo agenda
        int codOp = 1;
        // Inicializacion de ACK mensaje
        boolean ok = false;
        try {
            // Inicializacion de socket
            cs = new Socket(host, port);
            // Inicializacion de entradas y salidas
            dos = new DataOutputStream(cs.getOutputStream());
            dis = new DataInputStream(cs.getInputStream());
            // enviamos el codigo de la operacion que se quiere realizar
            dos.writeInt(codOp);
            // esperamos a recibir el ack
            ok = dis.readBoolean();
            if(ok){
                // guardamos el id del objeto que hemos creado en el servidor
                // para su uso posteriormente
                this.idObj = dis.readInt();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null)
                try {
                    // cierre de cliente
                    cs.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public void write(String s, int v) {
        // Inicializacion del socket
        Socket cs = null;
        // Inicializacion de entradas y salidas del cliente
        DataInputStream dis = null;
        DataOutputStream dos = null;
        // Inicializacion del codigo de creacion de objeto
        // tipo agenda
        int codOp = 2;
        boolean ok = false;
        try {
            // Inicializacion de socket
            cs = new Socket(host, port);
            // Inicializacion de entradas y salidas
            dos = new DataOutputStream(cs.getOutputStream());
            dis = new DataInputStream(cs.getInputStream());
            // enviamos el codigo de la operacion que se quiere realizar
            dos.writeInt(codOp);
            // enviamos el id del objeto sobre el que queremos realizar la
            // operacion
            dos.writeInt(this.idObj);
            // enviamo el identificador del elemento sobre el que queremos
            // realizar la operacion
            dos.writeUTF(s);
            // enviamos el valor del elemento
            dos.writeInt(v);
            // esperamos a recibir el ack
            ok = dis.readBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {
            if (cs != null)
                try {
                    cs.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public int read(String s) {
        // Inicializacion del socket
        Socket cs = null;
        // Inicializacion de entradas y salidas del cliente
        DataInputStream dis = null;
        DataOutputStream dos = null;
        // Inicializacion del codigo de creacion de objeto
        // tipo agenda
        int codOp = 3;
        int value = -1;
        boolean ok = false;
        try {
            // Inicializacion de socket
            cs = new Socket(host, port);
            // Inicializacion de entradas y salidas
            dos = new DataOutputStream(cs.getOutputStream());
            dis = new DataInputStream(cs.getInputStream());
            // enviamos el codigo de la operacion que se quiere realizar
            dos.writeInt(codOp);
            // enviamos el id del objeto sobre el que queremos realizar la
            // operacion
            dos.writeInt(this.idObj);
            // enviamos el elemento sobre el que queremos informacion
            dos.writeUTF(s);
            // esperamos a recibir el ack
            ok = dis.readBoolean();
            if (ok)
                value = dis.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null)
                try {
                    cs.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
        // retornamos el valor que hemos solicitado al servidor
        return value;
    }
}
