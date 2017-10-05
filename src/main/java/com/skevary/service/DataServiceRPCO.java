package com.skevary.service;

import com.skevary.DataService;
import one.nio.rpc.client.AbstractRPCO;
import one.nio.rpc.client.RPCConnectionException;

public class DataServiceRPCO {
    private static AbstractRPCO<DataService> instance;

    static
    {
        instance = new AbstractRPCO("rpc_connect", DataService.class);
    }

    public static DataService get() throws RPCConnectionException
    {
        return instance.get();
    }
}
