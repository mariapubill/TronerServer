package model;


import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe que gestiona tota la informació referent als usuaris, els controls definits per l'usuari i el llistat
 * de punts que ha guanyat.
 *
 * @version: 12/04/2018
 */
public class User implements Serializable{
    private static final long serialVersionUID = 2679916162453832699L;
    /**
     * Nickname de l'usuari
     */
    private String nickname;
    /**
     * Password de l'usuari
     */
    private String password;
    /**
     * Email de l'usuari
     */
    private String email;
    /**
     * Data de registre de l'usuari
     */
    private String dateRegister;
    /**
     * Data de l'ultim access de l'usuari
     */
    private String dateAccess;
    /**
     * Caracter al que l'usuari ha assignat desplaçarse cap amunt
     */
    private char goUp;
    /**
     * Caracter al que l'usuari ha assignat desplaçarse cap a baix
     */
    private char goDown;
    /**
     * Caracter al que l'usuari ha assignat desplaçarse cap a l'esquerra
     */
    private char goLeft;
    /**
     * Caracter al que l'usuari ha assignat desplaçarse cap a la dreta
     */
    private char goRight;
    /**
     * Catacter al que l'usuari ha assignat per a fer el turbo
     */
    private char goTurbo;
    /**
     * Llista la qual conte tota la informació referent als punts que ha anat guanyant
     */
    private LinkedList<Score> score;

    /**
     * Constructor per defecte de la classe user
     */
    public User(){

    }
    /*
    /**
     * Constructor de proba
     * @param nickname
     * @param password
     * @param email
     * @param dateRegister
     * @param dateAccess
     */
    public User(String nickname,String password,String email,String dateRegister,String dateAccess){
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.dateRegister = dateRegister;
        this.dateAccess = dateAccess;
        score= new LinkedList<>();

    }

    /**
     * Getter de la llista amb les puntuacions realitzades per l'usuari
     *
     * @return score
     */
    public LinkedList<Score> getScore() {
        return score;
    }

    /**
     * Setter de la llista amb les puntuacions realitzades per l'usuari
     *
     * @param score
     */
    public void setScore(LinkedList<Score> score) {
        this.score = score;
    }

    /**
     * Getter de l'email de l'usuari
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de l'email de l'usuari
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter del nickname de l'usuari
     *
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter del nickname de l'usuari
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Getter del password de l'usuari
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter del password de l'usuari
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter de la data de registre de l'usuari
     *
     * @return dateRegister
     */
    public String getDateRegister() {
        return dateRegister;
    }

    /**
     * Setter de la data de registre de l'usuari
     *
     * @param dataRegister
     */
    public void setDateRegister(String dataRegister) {
        this.dateRegister = dataRegister;
    }

    /**
     * Getter de la data d'ultim access de l'usuari
     *
     * @return dateAccess
     */
    public String getDateAccess() {
        return dateAccess;
    }

    /**
     * Setter de la data d'ultim acces de l'usuari
     *
     * @param dateAccess
     */
    public void setDateAccess(String dateAccess) {
        this.dateAccess = dateAccess;
    }

    /**
     * Getter de la tecla amb la que es mou cap amunt
     *
     * @return goUp
     */
    public char getGoUp() {
        return goUp;
    }

    /**
     * Setter de la tecla amb al que es mou cap amunt
     *
     * @param goUp
     */
    public void setGoUp(char goUp) {
        this.goUp = goUp;
    }

    /**
     * Getter de la tecla amb la qual es mou en direccio cap a baix
     *
     * @return goDown
     */
    public char getGoDown() {
        return goDown;
    }

    /**
     * Setter de la tecla amb la qual es mou en direccio cap a baix
     *
     * @param goDown
     */
    public void setGoDown(char goDown) {
        this.goDown = goDown;
    }

    /**
     * Getter de la tecla amb la qual es mou a l'esquerra
     *
     * @return goLeft
     */
    public char getGoLeft() {
        return goLeft;
    }

    /**
     * Setter de la tecla amb al qual es mou a l'esquerrra
     *
     * @param goLeft
     */
    public void setGoLeft(char goLeft) {
        this.goLeft = goLeft;
    }

    /**
     * Getter de la tecla amb la qual es gira a la dreta
     *
     * @return goRight
     */
    public char getGoRight() {
        return goRight;
    }

    /**
     * Setter de la tecla amb la qual es gira a la  dreta
     *
     * @param goRight
     */
    public void setGoRight(char goRight) {
        this.goRight = goRight;
    }
    /**
     * Getter de la tecla amb el qual es realitza el turbo
     *
     * @return goTurbo
     */
    public char  getGoTurbo() {
        return goTurbo;
    }

    /**
     * Setter de la tecla amb el qual es realitza el turbo
     *
     * @param goTurbo
     */
    public void setGoTurbo(char goTurbo) {
        this.goTurbo = goTurbo;
    }




    public int recountType(int mode) {
        int puntuacion = 0;
        for(int i = 0; i< score.size();i++) {
            switch (mode){
                case 1:
                    score.get(i).getType().equals("2xGame");
                    break;
                case 2:
                    score.get(i).getType().equals("4xGame");
                case 3:
                    score.get(i).getType().equals("tournament");
            }
        }
        return puntuacion;
    }
}

