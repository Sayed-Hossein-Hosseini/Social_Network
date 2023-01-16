import java.util.ArrayList;
import java.util.LinkedList;

public class Network implements INetwork {

    private boolean isDirected;
    private LinkedList<User> userList;
    private LinkedList<Connector> connectorList;

    public Network(boolean directed) {
        isDirected = directed;
        userList = new LinkedList<>();
        connectorList = new LinkedList<>();
    }

    @SuppressWarnings({"unchecked"})
    private User validate(IUser v) {
        if (!(v instanceof User)) {
            throw new IllegalArgumentException("Invalid vertex");
        }

        // Safe cast:
        User vert = (User) v;

        // We COULD check if this vertex is actually in this graph...
        // naah.
        return vert;
    }

    @SuppressWarnings({"unchecked"})
    private Connector validate(IConnector e) {
        if(!(e instanceof Connector)) {
            throw new IllegalArgumentException("Invalid edge");
        }
        // Safe cast:
        Connector edge = (Connector) e;

        // We COULD check if this edge is actually in this graph...
        // naah.
        return edge;
    }

    public IUser insertUser(String name, String family, String email, String phoneNumber, String userName, String password) throws InvalidInput {
        User v = new User(name, family, email, phoneNumber, userName, password, isDirected);
        userList.add(v);
        // Skip position.
        return (IUser) v;
    }

    /** Create an Edge with the given element between nodes u and v. */
    public IConnector insertConnector(IUser u, IUser v, String element) throws IllegalArgumentException {
        if(getConnector(u,v)==null) {
            Connector e = new Connector(u, v, element);
            connectorList.add(e);
            User origin = validate(u);
            User dest = validate(v);
            // Skip position
            origin.getFollowing().put(v, e);
            if(isDirected) {
                dest.getFollower().put(u, e);
            } else {
                dest.getFollowing().put(u, e);
            }
            return (IConnector) e;
        } else {
            throw new IllegalArgumentException("There is already an edge between u and v!");
        }
    }

    /** Remove a Vertex from the graph, and delete all incident edges. */
    public void removeVertex(IUser vert) throws IllegalArgumentException {
        User v = validate(vert);
        for(IConnector e : v.getFollowing().values()) {
            removeConnector(e);
        }
        for(IConnector e : v.getFollower().values()) {
            removeConnector(e);
        }
        userList.remove(userList.indexOf(v));
    }

    /** Remove an Edge from the graph. */
    public void removeConnector(IConnector edge) throws IllegalArgumentException {
        Connector e = validate(edge);

        ArrayList<IUser> vertices = e.getEndpoints();

        // Remove vertices at edge endpoints from their incoming/outgoing list
        User v0 = validate(vertices.get(0));
        User v1 = validate(vertices.get(1));
        v0.getFollowing().remove(v1);
        v1.getFollower().remove(v0);

        // Remove edge from list of edges
        connectorList.remove(connectorList.indexOf(e));
    }

    // Vertex/Edge Utility Methods:
    /** Return number of vertices in graph. */
    public int numVertices() {
        return userList.size();
    }

    /** Return number of edges in graph. */
    public int numEdges() {
        return connectorList.size();
    }

    /** Return all vertices in graph. */
    public Iterable<User> users() {
        return userList;
    }

    /** Return all edges in graph. */
    public Iterable<Connector> connectors() {
        return connectorList;
    }

    // Vertices to Edges and Vice-Versa:
    /** Return edge connecting vertex u to vertex v. */
    public IConnector getConnector(IUser u, IUser v) {
        User vxu = validate(u);
        return vxu.getFollowing().get(v);
    }

    /** Return the vertices at the endpoints of the edge e. */
    public ArrayList<IUser> endpoints(IConnector edge) {
        Connector e = validate(edge);
        return e.getEndpoints();
    }

    /** Return the vertex opposite a given vertex. */
    public IUser opposite(IUser vertex, IConnector edge) throws IllegalArgumentException {
        Connector e = validate(edge);
        ArrayList<IUser> endpoints = e.getEndpoints();
        if(endpoints.get(0).equals(vertex)) {
            return endpoints.get(1);
        } else if(endpoints.get(1).equals(vertex)) {
            return endpoints.get(0);
        } else {
            throw new IllegalArgumentException("The vertex you passed is not incident to the edge you passed.");
        }
    }



    // Incoming/Outoing Edges from Vertex:
    /** Return the incoming edge degree of the vertex v. */
    public int inDegree(IUser v) {
        return validate(v).getFollower().size();
    }

    /** Return the outgoing edge degree of the vertex v. */
    public int outDegree(IUser v) {
        return validate(v).getFollowing().size();
    }

    /** Return an iterable set of Edge objects incoming to the vertex v. */
    public Iterable<IConnector> inEdges(IUser v) {
        return validate(v).getFollower().values();
    }

    /** Return an iterable set of Edge objects outgoing from the vertex v. */
    public Iterable<IConnector> outEdges(IUser v) {
        return validate(v).getFollowing().values();
    }
}