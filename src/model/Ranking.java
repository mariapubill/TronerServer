package model;




import java.io.Serializable;
import java.util.LinkedList;

public class Ranking implements Serializable {
    private static final long serialVersionUID = 1L;

    private LinkedList<User> users2x;
    private LinkedList<User> users4x;
    private LinkedList<User> usersTournament;

    public Ranking(){
        users2x = new LinkedList<>();
        users4x = new LinkedList<>();
        usersTournament = new LinkedList<>();
    }

    public LinkedList<User> getUsers2x() {
        return users2x;
    }

    public void setUsers2x(LinkedList<User> users2x) {
        this.users2x = users2x;
    }

    public LinkedList<User> getUsers4x() {
        return users4x;
    }

    public void setUsers4x(LinkedList<User> users4x) {
        this.users4x = users4x;
    }

    public LinkedList<User> getUsersTournament() {
        return usersTournament;
    }

    public void setUsersTournament(LinkedList<User> usersTournament) {
        this.usersTournament = usersTournament;
    }
}
