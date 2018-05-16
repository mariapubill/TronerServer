package model;

//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *La següent classe s'encarrega de la lectura dels fitxers necessaris("config.json") i de tot els parsejaments de Json
 * i extraccio de dades.
 *
 * @Version: 11/04/2018
 */
public class Parser {
    /**
     * Port assignat a la base de dades
     */
    private int port;
    /**
     * Direcció ip on es troba el servidor
     */
    private String directionIP;
    /**
     * nom de la base de dades
     */
    private String database;
    /**
     * usuari que conecta a la base de dades
     */
    private String user;
    /**
     * pasword de l'usuari de la base de dades
     */
    private String password;
    /**
     * Port desde el que es conecta el client al servidor
     */
    private String comunicationPort;

    /**
     * Constructor de la classe Parser per defecte
     */
    public Parser() {
    }

    /**
     * Mètode amb el qual llegim el fitxer Json config.json que es troba en la carpeta data
     * i extreu la informació del JSONObject que genera per a la lectura.
     *
     * @throws IOException
     * @throws NullPointerException
     */
    public void readJsonFile() throws IOException, NullPointerException {
        File file = new File("data//");
        if (!file.exists()) {
            throw new FileNotFoundException("No se ha encontrado el direcotrio");
        }
        File[] ficheros = file.listFiles();
        boolean exists = false;
        for (int i = 0; i < ficheros.length; i++) {
            File f = new File(ficheros[i].getName());
            System.out.println(ficheros[i].getName());
            if (ficheros[i].getName().equals("config.json")) {
                exists = true;
            }
        }
        if (!exists) {
            throw new FileNotFoundException("no existe el fichero config.json");
        }
        try {
            Path path = Paths.get("data//config.json");
            List<String> s = Files.readAllLines(path);
            String json = new String();
            for (int i = 0; i < s.size();i++){
                json = json +s.get(i);
            }
            JSONObject jsonObject = new JSONObject(json);
            this.user =jsonObject.getString("User");
            this.directionIP = jsonObject.getString("DirectionIP");
            this.password = jsonObject.getString("Password");
            this.comunicationPort=jsonObject.getString("CommunicationPort");
            this.database =jsonObject.getString("Database");
            this.port =jsonObject.getInt("Port");
        }catch (JSONException e){
            throw new JSONException("Sintaxis d'arxiu Erronia");
        }
    }

    /**
     * Getter del port al que se conecta el servidor para acceder a la base de datos
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter del port a partir del parametre rebut
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Getter de la direccioIP de la base de datos a la que se conecta el servidor
     *
     * @return
     */
    public String getDirectionIP() {
        return directionIP;
    }

    /**
     * Setter de la direccioIP de la base de datos a la que se conecta el servidor
     *
     * @param directionIP
     */
    public void setDirectionIP(String directionIP) {
        this.directionIP = directionIP;
    }

    /**
     * Getter del nombre de la database
     *
     * @return database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * setter del nombre de la database
     *
     * @param database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     *getter que retonna l'usuari que conecta a la database
     *
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter de l'usuari que conecta a la database
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter del Password d'acces a la database
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter del password d'acces a la database
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter del ComunicationPort del servidor
     *
     * @return comunicationPort
     */
    public String getComunicationPort() {
        return comunicationPort;
    }

    /**
     * Setter del comunicationPort del servidor
     *
     * @param comunicationPort
     */
    public void setComunicationPort(String comunicationPort) {
        this.comunicationPort = comunicationPort;
    }
    /*
    public void hashMD5(User user) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(user.getPassword().getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            user.setPassword(sb.toString());
        }catch(Exception exception) {
            System.out.println("imposible convertir a md5");
        }


    }*/
    /* Retorna un hash a partir de un tipo y un texto */

    public static String getHash(User user, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(user.getPassword().getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash MD5 a partir de un texto */

    public void hashMD5(User user) {
       user.setPassword(getHash(user, "MD5"));
    }
}

