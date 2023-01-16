import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;

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

    public static HashMap<User, Float> sortByValue(HashMap<User, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<User, Float> > list =
               new LinkedList<Map.Entry<User, Float>>(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<User, Float> >() {
            public int compare(Map.Entry<User, Float> o1,
                               Map.Entry<User, Float> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<User, Float> temp = new LinkedHashMap<User, Float>();
        for (Map.Entry<User, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public float rateSuggestion(User user1, User user2){
        int count = 0;
        for(Map.Entry<IUser, IConnector> set1 : user1.getFollowing().entrySet()){
            User checkMutual = (User) set1.getKey();
            if(user2.getFollowing().containsKey(checkMutual))
                count++;
        }
        return ((float) count)/ ((float) (user1.getFollowing().size() + user2.getFollowing().size()));
    }

    public HashMap<User, Float> fillByNew(int count){
        HashMap<User, Float> newUser = new HashMap<>();
        for(int i = 1; i <= count && i <= userList.size(); i++)
            newUser.put(userList.get(userList.size() - i), (float)0);
        
        return newUser;
    }

    // suggestions for all users
    public void makeSuggestions(){
        HashMap<User, Float> newUser = fillByNew(6);

        for (int i = 0; i < userList.size(); i++){
            User user1 = userList.get(i);
            if(user1.getFollowing().size() == 0){
                user1.setSuggestion(newUser);    
                continue;
            }
            for(int j = i + 1; j < userList.size(); j++){
                User user2 = userList.get(j);
                if(user2.getFollowing().size() == 0)
                    continue;
                float rate = rateSuggestion(user1, user2);
                user1.getSuggestion().put(user2, rate);
                user2.getSuggestion().put(user1, rate);
            }
            if(user1.getSuggestion().size() < 6)
                user1.getSuggestion().putAll(fillByNew(6 - user1.getSuggestion().size()));
            HashMap<User, Float> sorted = sortByValue(user1.getSuggestion());
            HashMap<User, Float> sug = new HashMap<>();
            for(Map.Entry<User, Float> set1 : sorted.entrySet()){
                sug.put(set1.getKey(), set1.getValue());
            }
            user1.setSuggestion(sug);
        }
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