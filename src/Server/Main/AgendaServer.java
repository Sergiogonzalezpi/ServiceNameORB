package Server.Main;

// Server  - AgendaServer

import Server.Agenda.Agenda;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class AgendaServer {
    // Incializacion del servidor y puerto del mismo
    private ServerSocket ss = null;
    private int port = -1;
    /*
        Declaracion de la Tabla de Hash en la que se encontraran los objetos
        que se creen y la declaracion del indice por el cual se van a crear
        los siguientes objetos y en esa posicion se van a introducir en la
        Tabla de Hash para poder buscarlos posteriormente
    */
    private final Hashtable<Integer, Object> agendas_objs;
    private int indexObjs;

    public AgendaServer(int port) {
        // Inicializacion del puerto
        this.port = port;
        // Inicializacion de la Tabla de Hash
        this.agendas_objs = new Hashtable<>();
        // Inicializacion del indice de los objetos - tambien se puede
        // utilizar para conocer el numero de objetos que tenemos
        this.indexObjs = 1;
    }
    public void start() {
        // Declaracion e inicializacion del socket
        Socket cs = null;
        try {
            // Inicializacion de server
            ss = new ServerSocket(port);
            while (true) {
                // Conexion aceptada entre Cliente y Servidor, guardado
                // socket en cs
                cs = ss.accept();
                // Llamada a process
                process(cs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ss != null)
                try {
                    // Cierre del servidor
                    ss.close();
                    System.out.println("shutdown server ");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    private void process(Socket cs) {
        try {
            // Inicializacion de las tuberias de entrada y salida del
            // socket
            DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
            DataInputStream dis = new DataInputStream(cs.getInputStream());
            // Lectura del codigo de operacion
            int codOp = dis.readInt();
            // Inicializacion del codigo de objeto
            int codObj = 0;
            // Incializacion de objeto de tipo agenda como auxiliar
            Agenda obj = null;
            // Busca segun el codigo de operacion, la operacion que se debe de
            // realizar
            switch (codOp) {
                case 1: // create Agenda
                    // añadimos un nuevo objeto de tipo agenda en la Tabla de
                    // Hash, añadiendole como identificador el indexObjs para
                    // usarlo de identificador posteriormente
                    agendas_objs.put(this.indexObjs, new Agenda());
                    // enviamos ACK al cliente
                    dos.writeBoolean(true);
                    // mandamos el id del nuevo objeto que hemos creado
                    dos.writeInt(this.indexObjs);
                    // incremento del indexador a uno para el siguiente objeto
                    // a crear
                    this.indexObjs+=1;
                    break;
                case 2:// write
                    // leemos el codigo del objeto que nos a mandado el cliente
                    codObj = dis.readInt();
                    // guardamos el objeto que corresponde al codigo de objeto
                    // del identificador de los objetos de la Tabal de Hash
                    obj = (Agenda) agendas_objs.get(codObj);
                    if (obj != null ) {
                        // escribimos el nuevo elemento dentro de nuestro objeto
                        obj.write(dis.readUTF(), dis.readInt());
                        // enviamos ACK
                        dos.writeBoolean(true);
                    } else
                        // enviamos que no se ha realizado correctamente
                        dos.writeBoolean(false); // Error. No agenda
                    break;
                case 3: // read
                    // leemos el codigo del objeto que nos a mandado el cliente
                    codObj = dis.readInt();
                    // guardamos el objeto que corresponde al codigo de objeto
                    // del identificador de los objetos de la Tabal de Hash
                    obj = (Agenda) agendas_objs.get(codObj);
                    if (obj != null) {
                        // enviamos ACK
                        dos.writeBoolean(true);
                        // enviamos el elemento que nos ha pedido el cliente
                        dos.writeInt(obj.read(dis.readUTF()));
                    } else
                        // enviamos que no se ha realizado correctamente
                        dos.writeBoolean(false); // Error. No agenda
                    break;
                default:
                    System.out.println("Code " + codOp + "unknown");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cs != null)
                try {
                    // cerramos el cliente
                    cs.close();
                    System.out.println("closing client connection ");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public static void main(String[] args) {
        // Creacion del servidor
        AgendaServer myServer = new AgendaServer(9999);
        // Iniciamos el servidor
        myServer.start();
    }
}
