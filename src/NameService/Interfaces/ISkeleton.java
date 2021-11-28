package NameService.Interfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface ISkeleton {
    //process method will be invoked by the ORB after obtaining the interface number.
    public void process(DataInputStream dis, DataOutputStream dos);
    //returns the identifier of the class
    public int getIid();
    //Returns the proxy object of the service
    public Object createProxy();
}
