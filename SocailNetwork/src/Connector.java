import java.util.ArrayList;

public class Connector implements IConnector {

    // Not storing a position pointer
    private String element;
    private ArrayList<IUser> endpoints;

    public Connector(IUser u, IUser v, String element) {
        this.element = element;
        endpoints = new ArrayList<IUser>();
        endpoints.add(u);
        endpoints.add(v);
    }

    public String getElement() {
        return element;
    }

    public ArrayList<IUser> getEndpoints() {
        return endpoints;
    }
}