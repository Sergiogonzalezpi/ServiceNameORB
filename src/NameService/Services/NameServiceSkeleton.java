package NameService.Services;

import NameService.Interfaces.ISkeleton;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class NameServiceSkeleton implements ISkeleton {
    @Override
    public void process(DataInputStream dis, DataOutputStream dos) {

    }

    @Override
    public int getIid() {
        return 0;
    }

    @Override
    public Object createProxy() {
        return null;
    }
}
