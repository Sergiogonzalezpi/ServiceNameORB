package Client;

// FILE3: AgendaClient

import Server.Agenda.AgendaProxy;

public class AgendaClient {
    public static void main(String[] args) {
// 1. Create a Proxy of the NameService.
// 2. Ask the NS for the proxy of the Agenda. Consider that the SN and
// ORB server run in the same host.
        AgendaProxy phoneAgenda = new AgendaProxy();
        AgendaProxy passwdAgenda = new AgendaProxy();
        phoneAgenda.write("Juan", 66756677);
        passwdAgenda.write("Moodle", 23323);
        phoneAgenda.write("Pepe", 644454456);
        System.out.println("Juan’s Phone = " + phoneAgenda.read("Juan"));
        System.out.println("Pepe’s Phone = " + phoneAgenda.read("Pepe"));
        System.out.println("Password Moodle = " +
                passwdAgenda.read("Moodle"));
    }
}