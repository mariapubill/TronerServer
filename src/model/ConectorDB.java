package model;




import java.sql.*;


    /**
     * La següent classe te com a funció realitzar la conexió entre el programa i la base de dades a la que es vol
     * conectar el servidor ja sigui en local o accedint a la base de dades d'una direcció IP en concret i executa les queries
     * en la base de dades retornant o actualiztant informació d'aquesta.
     * Realment tota la part de realització de querys es poden fer amb una unica funcio pero esta fet de la següent manera
     * per ajudar a identificar l'error de SQL que es pot donar
     *
     * @version 12/04/2018
     */
    public class ConectorDB {
        /**
         * nom del usuari d'acces a la base de dades
         */
        static String userName;
        /**
         * password del usuari d'acces a la base de dades
         */
        static String password;
        /**
         * nom de la base de dades
         */
        static String db;
        /**
         * port al cual es vol conectar la base de dades
         */
        static int port;
        /**
         *base de la url a la que accedeix el programa
         */
        // static String url = "jdbc:mysql://localhost";
        static String url = "jdbc:mysql://";
        /**
         * atribut que realitza la conexió amb la base de dades
         */
        static Connection conn = null;
        /**
         * Atribut que retorna les dades de la recerca a la base de dades a nivell de conexio
         */
        static Statement s;

        /**
         * Constructor de la classe que s'encarrega d'inicialitzar el conector a la base de dades
         * generant la url amb la que es realitza la connexio i inicialitza els atributs amb els valors passats
         * com a parametres
         * @param usr
         * @param pass
         * @param db
         * @param port
         * @param directionIP
         */
        public ConectorDB(String usr, String pass, String db, int port, String directionIP) {
            ConectorDB.userName = usr;
            ConectorDB.password = pass;
            ConectorDB.db = db;
            ConectorDB.port = port;
            ConectorDB.url += directionIP;
            ConectorDB.url += ":"+port+"/";
            ConectorDB.url += db;
        }

        /**
         * El següent mètode realitza la connexio a la base de dades a partir de la url generada al constructor
         * el username i el password.
         * Retorna True si realitza la conexio, sino retorna False
         * @return boolean
         */
        public boolean connect() {
            try {
                Class.forName("com.mysql.jdbc.Connection");
                conn = (Connection) DriverManager.getConnection(url, userName, password);
                if (conn != null) {
                    System.out.println("Conexió a base de dades "+url+" ... Ok");
                }
                return true;
            }
            catch(SQLException ex) {
                System.out.println("Problema al connecta-nos a la BBDD --> "+url);
            }
            catch(ClassNotFoundException ex) {
                System.out.println(ex);
            }
            return false;

        }

        /**
         * El següent mètode executa la query en la base de dades
         *
         * @param query
         */
        public void insertQuery(String query){
            try {
                s =(Statement) conn.createStatement();
                s.executeUpdate(query);

            } catch (SQLException ex) {
                System.out.println("Problema al Inserir --> " + ex.getSQLState());
            }
        }

        /**
         * El següent mètode actualitza la informació de la base de dades y sino mostra un error al modificar
         *
         * @param query
         */
        public void updateQuery(String query){
            try {
                s =(Statement) conn.createStatement();
                s.executeUpdate(query);

            } catch (SQLException ex) {
                System.out.println("Problema al Modificar --> " + ex.getSQLState());
            }
        }

        /**
         * El següent metode executa la query que elimina la informació de la base de dades
         *
         * @param query
         */
        public void deleteQuery(String query){
            try {
                s =(Statement) conn.createStatement();
                s.executeUpdate(query);

            } catch (SQLException ex) {
                System.out.println("Problema al Eliminar --> " + ex.getSQLState());
            }

        }

        /**
         * El següent metode executa la query rebuda per parametre i retorna un ResultSet amb la informacio de la base de dades
         *
         * @param query
         * @return rs
         */
        public ResultSet selectQuery(String query){
            ResultSet rs = null;
            try {
                s =(Statement) conn.createStatement();
                rs = s.executeQuery (query);

            } catch (SQLException ex) {
                System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
            }
            return rs;
        }

        /**
         * El següent mètode tanca la connexio entre el programa i la base de dades.
         */
        public void disconnect(){
            try {
                conn.close();
                System.out.println("Desconnectat!");
            } catch (SQLException e) {
                System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
            }
        }

    }

