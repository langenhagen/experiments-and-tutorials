package org.sercho.masp.context.providers.location;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "UbiSenseWebService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
// @WebService
public class UbiWebService {

    JUbiClient Client;

    public UbiWebService() {

    }

    public boolean Connect(final String ip) {
        this.Client = new JUbiClient(ip, 10587);
        return this.Client.isConnected();
    }

    public boolean isConnected() {
        return this.Client.isConnected();
    }

    @WebMethod
    public Args getAnglesOf(final String name) {
        return this.Client.getAnglesOf(name);
    }

    @WebMethod
    public Poss getPositionOf(final String name) {

        return this.Client.getPositionOf(name);
        // teststruct result = new teststruct(5,5,5);
        // return result;
    }

    @WebMethod
    public String[] getContainersOf(final String name) {
        return this.Client.getContainersOf(name);
    }

    @WebMethod
    public String[] getObjects() {
        return this.Client.getObjects();
    }

    @WebMethod
    public String[] getObjectsIn(final String container) {
        return this.Client.getObjectsIn(container);
    }

}
