package model;


/**
 * La seguent classe conte tota la informacio referent a la puntuacio aconseguida en una ronda en concret aixi com
 * el moment en el que s'ha realitzat i en quin tipus de joc s'ha realitzat.
 *
 * @Version 12/04/2018
 */
public class Score {
    /**
     * Data en la que s'ha guanyat la puntuacio
     */
    private String dateScore;
    /**
     * Puntuacio guanyada en la ronda
     */
    private long points = 0;
    /**
     * Tipus de joc al que s'esta jugant
     */
    private String type;
    /**
     * Temps exacte en el que s'ha guanyat la puntuacio
     */
    private String timeRound;

    /**
     * Constructor de la classe Score per defecte
     */
    public Score(){

    }

    /**
     * Getter de la data en la que s'ha guanyat la puntuacio
     *
     * @return dateScore
     */
    public String getDateScore() {
        return dateScore;
    }

    /**
     * Setter de la data en la que s'ha guanyat la puntuacio
     *
     * @param dataScore
     */
    public void setDateScore(String dataScore) {
        this.dateScore = dataScore;
    }

    /**
     * getter de la puntuacio que ha guanyat en la ronda
     *
     * @return points
     */
    public long getPoints() {
        return points;
    }

    /**
     * Setter de la puntuacio que ha guanyat en la ronda
     *
     * @param points
     */
    public void setPoints(long points) {
        this.points = points;
    }

    /**
     * Getter del tipus de joc en el que s'ha guanyat la puntuacio
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter del tipus de joc en el que s'ha guanyat la puntuacio
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter del temps exacte en el que s'ha guanyat la puntuacio
     *
     * @param timeRound
     */
    public void setTimeRound(String timeRound) {
        this.timeRound = timeRound;
    }

    /**
     * Getter del temps exacte en el que s'ha guanyat la puntuacio
     *
     * @return timeRound
     */
    public String getTimeRound(){
        return this.timeRound;
    }

}

