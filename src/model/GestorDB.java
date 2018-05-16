package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * La següent classe es tracta del gestor de la base de dades,  que es una classe que s'encarrega de la gestió de la
 * informació de la base de dades en funció de les necessitats de l'usuari i el servidor construint totes les queries
 * per obtenir la informació necessaria i utilitzant el ConnectorDB per obtenir i enviar informació.
 *
 * Version: 12/04/2018
 */
public class GestorDB {
    /**
     * El següent atribut es tracta del mitja o conector per el qual el nostre programa es conecta a la base de dades
     */
    private ConectorDB conectorDB;

    /**
     * Constructor de la classe amb el qual asignem el conectorDB del programa a la classe
     *
     * @param conectorDB
     */
    public GestorDB(ConectorDB conectorDB) {
        this.conectorDB = conectorDB;
    }

    public void closeConnection() {
        conectorDB.disconnect();
    }

    /**
     * El següent metode s'encarrega de comprobar si l'usuari existeix o no en la base de dades, si l'usuari existeix
     * retorna false, si l'usuari existeix, retorna true i el guarda.
     *
     * @param user
     * @return boolean
     */
    public boolean registraUsuari(User user,Parser parser) {
        if (!userExists(user)) {
            parser.hashMD5(user);
            conectorDB.insertQuery("INSERT INTO usuari(nickname,password,email,dateRegister,dateAccess) VALUES ('" + user.getNickname() + "','" +
                    user.getPassword() + "','" + user.getEmail() + "','" + user.getDateRegister() + "','" + user.getDateAccess() + "');");
            System.out.println("INSERT INTO usuari(nickname,password,email,dateRegister,dateAccess) VALUES ('" + user.getNickname() + "','" +
                    user.getPassword() + "','" + user.getEmail() + "','" + user.getDateRegister() + "','" + user.getDateAccess() + "');");
            return true;

        } else {
            return false;
        }
    }

    /**
     * El següent mètode s'enccarrega de la comprovació de si l'usuari rebut com a parametre existeix o no en la base de
     * dades en funció de si al realitzar la cerca buscant primerament si el nickname existeix i despres l'email.
     * Retorna Fals si no existeix i retorna True si troba resultats en la base de dades.
     *
     * @param user
     * @return boolean
     */
    public boolean userExists(User user) {
        ResultSet resultSet = null;
        resultSet = conectorDB.selectQuery("SELECT usuari.nickname FROM usuari WHERE usuari.nickname like '" + user.getNickname() + "'");
        String result = new String();
        try {
            while (resultSet.next()) {

                try {
                    result = result + resultSet.getObject("nickname");
                    System.out.println("chek");
                    System.out.println(result+"  dkaadspokfasdkof`pa`sdokfaksòdfkòa");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
        }

        if (result.equals("")) {
            resultSet = conectorDB.selectQuery("SELECT usuari.email FROM usuari WHERE usuari.email like '" + user.getEmail() + "'");
            result = new String();
            try {
                while (resultSet.next()) {

                    try {
                        result = result + resultSet.getObject("email");
                        System.out.println(result+" fpoaijsdfpoiasdjfiasdfipasjdifapsdfjia");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
            }
            if (result.equals("")) {
                return false;
            }
        }
        return true;
    }

    /**
     * La següent funció s'encarrega de verificar si l'usuari ha introduit be o no en la base de dades el seu usuari
     * primerament comproban si aquest usuari existeix, i deprés comproba que el password introduit sigui el correcte
     * retorna True si el nickname/email i password introduits son correctes. Per la resta retorna false.
     *
     * @param user
     * @return boolean
     */
    public boolean logIn(User user) {
        if (userExists(user)) {
            ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.password FROM usuari WHERE usuari.email like '"
                    + user.getEmail() + "' OR usuari.nickname like '" + user.getNickname() + "';");
            System.out.println(user.getEmail());
            System.out.println(user.getNickname());
            String result = new String();
            try {
                while (resultSet.next()) {

                    try {

                        result = result + resultSet.getObject("password");
                        System.out.println("Password BBDD: "+result);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
            }
            if (result.equals("")) {
                return false;
            } else {
                System.out.println(result);
                System.out.println("Password Introducido: "+user.getPassword());
                if(result.equals(user.getPassword())) {
                    getUserInfo(user);
                    if(isConnected(user.getNickname())){
                        return false;
                    }else{
                        setConnected(user.getNickname());
                        return true;
                    }
                }
                return false;
            }
        }else{
            return false;
        }
    }

    private void setConnected(String nickname) {
        conectorDB.insertQuery("UPDATE usuari SET connected ='"+1+"' WHERE nickname = '"+nickname+"';");
        System.out.println("UPDATE usuari SET connected ='"+1+"' WHERE nickname = '"+nickname+"';");
    }

    private boolean isConnected(String nickname) {
        ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.connected FROM usuari WHERE usuari.nickname like '" +
                nickname + "';");
        System.out.println("SELECT usuari.connected FROM usuari WHERE usuari.nickname like '" +
        nickname + "';");

        try {
            while (resultSet.next()) {
                Boolean result = (boolean) resultSet.getObject("connected");
                System.out.println(result);
                if(result.equals(1)){
                    return true;
                }else{
                    return false;
                }

            }
            return false;
        } catch (SQLException e) {
            System.out.println("lol");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * El següent mètode borra tota la informació de la base de dades l'usuari que rep com a parametre tant com a usuari
     * com el seu historic de puntuacio
     *
     * @param user
     */
    public void deleteUser(User user) {
        conectorDB.deleteQuery("DELETE FROM usuari WHERE nickname like'" + user.getNickname() + "'");
        conectorDB.deleteQuery("DELETE FROM score WHERE nickname like'"+user.getNickname()+"'");

    }

    /**
     *El següent mètode recupera tota la informació de cada usuari emmagatzemat en la base de dades
     *
     * @return users
     */
    public LinkedList<User> selectAllUsers() {
        LinkedList<User> users = new LinkedList<>();
        ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.nickname,usuari.dateAccess,usuari.dateRegister FROM usuari;");
        try{
            while ((resultSet.next())){
                User user = new User();
                user = getUserInfoLeaderboard(resultSet.getObject("nickname").toString());
                user.setDateAccess(resultSet.getObject("dateAccess").toString());
                user.setDateRegister(resultSet.getObject("dateRegister").toString());
                users.add(user);
            }
        }catch (SQLException sql){

        }
        return users;
    }

    /**
     * El següent mètode retorna a partir del nickname, l'usuari amb tota la informació necessaria per
     * poder mostrar el ranking
     *
     * @param nickname
     * @return user
     */
    private User getUserInfoLeaderboard(String nickname) {
        User user = new User();
        user.setNickname(nickname);
        ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.nickname,usuari.dateAccess FROM usuari WHERE usuari.nickname like '" + user.getNickname() + "'");
        System.out.println("user:"+user.getNickname());
        System.out.println("SELECT usuari.nickname,usuari.dateAccess FROM usuari WHERE usuari.nickname like '" + user.getNickname() + "'");
        try{
            while (resultSet.next()) {
                user.setDateAccess(resultSet.getObject("dateAccess").toString());
                chargePoints(user);
            }
        }catch(SQLException sql){
            sql.getMessage();
        }
        return user;
    }

    /**
     * El següent mètode retorna l'usuari amb tota la informació referent al usuari rebut com a parametre de la base de dades.
     *
     * @param user
     */
    public void getUserInfo(User user) {
        if(!isNickname(user)){
            user.setEmail(user.getNickname());
            user.setNickname(this.getNickname(user.getEmail()));
        }
        updateLastAccess(user);
        ResultSet resultSet = conectorDB.selectQuery("SELECT * FROM usuari WHERE usuari.nickname like '" + user.getNickname() + "'");
        try {
            while (resultSet.next()) {
                user.setEmail((String) resultSet.getObject("email"));
                user.setGoUp(resultSet.getObject("goUp").toString().toCharArray()[0]);
                user.setGoDown( resultSet.getObject("goDown").toString().toCharArray()[0]);
                user.setGoLeft( resultSet.getObject("goLeft").toString().toCharArray()[0]);
                user.setGoRight( resultSet.getObject("goRight").toString().toCharArray()[0]);
                String s = resultSet.getObject("goTurbo").toString();
                if(s.equals("")){
                    user.setGoTurbo(' ');
                }else {
                    user.setGoTurbo(resultSet.getObject("goTurbo").toString().toCharArray()[0]);
                }
                chargePoints(user);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            System.out.println(sql.getMessage());
        }
    }

    /**
     * El següent mètode retorna el nickname de l'usuari de la base de dades a partir del mail de l'usuari
     *
     * @param email
     * @return result
     */
    private String getNickname(String email) {
        String result = new String();
        ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.nickname FROM usuari WHERE usuari.email like '"+email+"';");
        try {


            while (resultSet.next()) {
                result = resultSet.getString("nickname");
            }
        }catch (SQLException sql){

        }
        return result;
    }

    /**
     * El següent mètode comproba si l'usuari ha introduit un mail o un nickname com a log in
     *
     * @param user
     * @return boolean
     */
    private boolean isNickname(User user) {
        String result = new String();
        ResultSet resultSet = conectorDB.selectQuery("SELECT usuari.nickname FROM usuari WHERE usuari.nickname like '"+user.getNickname()+"';");
        try {
            while (resultSet.next()){
                result = resultSet.getString("nickname");
            }
            if(result.equals("")){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * El següent mètode extreu tota la informació de la base de dades sobre la puntuació de  l'usuari que rep per parametre
     * i la salva en aquesta variable
     *
     * @param user
     */
    private void chargePoints(User user) {
        LinkedList<Score> scores = new LinkedList<>();
        ResultSet resultSet =conectorDB.selectQuery("SELECT s.dateScore,s.points,s.timeRound,g.type FROM game as g,score as s,usuari as u WHERE u.nickname = s.nickname AND s.idGame = g.idGame AND s.nickname like '"+user.getNickname()+"';");
        System.out.println("SELECT s.datePlay,s.points,s.timeRound,g.type FROM game as g,score as s,usuari as u WHERE u.nickname = s.nickname " +
                "AND s.idGame = g.idGame AND s.nickname like '"+user.getNickname()+"';");
        try {

            while(resultSet.next()){
                Score score = new Score();
                score.setDateScore(resultSet.getString("dateScore"));
                score.setPoints((long)resultSet.getObject("points"));
                score.setType(resultSet.getString("type"));
                score.setTimeRound(resultSet.getObject("timeRound").toString());
                scores.add(score);
            }
        } catch (SQLException e) {
        }
        user.setScore(scores);
    }

    /**
     * El següent métode actualitza l'ultim access de l'usuari que s'ha conectat en la base de dades a partir de la informació
     * de l'usuari que rep com a paràmetre
     *
     *
     * @param user
     */
    public void updateLastAccess(User user) {
        conectorDB.updateQuery("UPDATE usuari SET dateAccess = '" + LocalDate.now() + "' WHERE nickname = '" + user.getNickname() + "';");
        System.out.println("UPDATE usuari SET dateAccess = '" + LocalDate.now() + "' WHERE nickname = '" + user.getNickname() + "';");
    }

    /**
     * El següent mètode afegeix el nou score realitzat per l'usuari al final de la ronda en la base de dades en funcio
     * del tipus de joc que es troba realitzant
     *
     * @param user
     */
    public void userSetScore(User user){
        int option = 0;
        if(user.getScore().get(user.getScore().size()-1).getType().equals("2xGame")){
            option = 1;
        }
        if(user.getScore().get(user.getScore().size()-1).getType().equals("4xGame")){
            option = 2;
        }
        if(user.getScore().get(user.getScore().size()-1).getType().equals("tournament")){
            option = 3;
        }
        conectorDB.insertQuery("INSERT INTO score(nickname,idGame,dateScore,points,timeRound) VALUES ('"+user.getNickname()+"','"+option+"','"+user.getScore().get(user.getScore().size()-1).getDateScore()+"','"+user.getScore().get(user.getScore().size()-1).getPoints()+ "','"+user.getScore().get(user.getScore().size()-1).getTimeRound()+"')");
    }

    /**
     * El següent metode actualitza els controls d'un determinat usuari en la base de dades a partir del usuari en concret
     * que rep com a parametre
     *
     * @param user
     */
    public void userSetControls(User user){
        conectorDB.insertQuery("UPDATE usuari SET goUp ='"+user.getGoUp()+"' WHERE nickname = '"+user.getNickname()+"';");
        conectorDB.insertQuery("UPDATE usuari SET goDown ='"+user.getGoDown()+"' WHERE nickname = '"+user.getNickname()+"';");
        conectorDB.insertQuery("UPDATE usuari SET goLeft ='"+user.getGoLeft()+"' WHERE nickname = '"+user.getNickname()+"';");
        conectorDB.insertQuery("UPDATE usuari SET goRight ='"+user.getGoRight()+"' WHERE nickname = '"+user.getNickname()+"';");
        conectorDB.insertQuery("UPDATE usuari SET goTurbo ='"+user.getGoTurbo()+"' WHERE nickname = '"+user.getNickname()+"';");
    }

    public void changeConnected(User user) {
        conectorDB.updateQuery("UPDATE usuari SET connected = false WHERE nickname ='"+user.getNickname()+"';");
        System.out.println("UPDATE usuari SET connected = false WHERE nickname ='"+user.getNickname()+"';");
    }

    //   public void cleanURL() {
 //       conectorDB.cleanURL();
 //   }
}
