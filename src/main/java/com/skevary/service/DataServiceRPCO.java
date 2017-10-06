package com.skevary.service;

import com.skevary.Service;
import one.nio.rpc.client.AbstractRPCO;
import one.nio.rpc.client.RPCConnectionException;

public class DataServiceRPCO {
    private static AbstractRPCO<Service> instance;

    static
    {
        instance = new AbstractRPCO("rpc_connect", Service.class);
    }

    public static Service get() throws RPCConnectionException
    {
        return instance.get();
    }
}
