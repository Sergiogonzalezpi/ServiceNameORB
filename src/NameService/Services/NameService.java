package NameService.Services;

import NameService.Interfaces.INameService;

import java.io.IOException;
import java.util.Hashtable;

public class NameService implements INameService {
    private final Hashtable<String, Object> nameProxiesServices;
    private int indexProxies;

    public NameService() {
        this.nameProxiesServices = new Hashtable<>();
        this.indexProxies = 1;
    }

    @Override
    public void bind(String serviceName, Object proxy) {
        nameProxiesServices.put(serviceName, proxy);
    }

    @Override
    public Object lookup(String nameServerHost, int nameServerPort, String serviceName) throws IOException {
        return nameProxiesServices.get(serviceName);
    }

    @Override
    public String[] list(String nameServerHost, int nameServerPort) {
        return new String[0];
    }

    @Override
    public void unbind(String serviceName) {
        nameProxiesServices.remove(serviceName);
    }
}
