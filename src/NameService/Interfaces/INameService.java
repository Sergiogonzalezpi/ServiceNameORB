package NameService.Interfaces;

public interface INameService {
        void bind(String serviceName, Object proxy);
        // Binds the specified serviceName to a Proxy Object in the local Name Server.
        Object lookup(String nameServerHost, int nameServerPort,
        String serviceName)throws java.io.IOException;
        // Asks the Name Server running in nameServerHost:nameServerPort for a
        // proxy of a serviceName.
        String [] list(String nameServerHost, int nameServerPort);
        // Returns an array of the name of services bound in the Name Server running
        // in NameServerHost and nameServerPort.
        void unbind(String serviceName);
        // Unbinds the specified serviceName in the local Name Server.
}