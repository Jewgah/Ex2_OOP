package Informations;


import api.geo_location;

/**
 * This interface represents the set of operations applicable on a
 * pokemon agent
 * @author Jordan Perez & Nath Benichou
 *
 */
public interface agent_info {


    ////////////////////////////////////// GETTERS ///////////////////////////////////////////
    /**
     * Get the id of this pokemon agent
     * @return
     */
    public int getId();

    /**
     * Get the amount of points gathered by this pokemon agent
     * @return
     */
    public double getValue();

    /**
     * Get the key of the current node where this pokemon agent is
     * @return
     */
    public int getSrc();

    /**
     * Get the key of the next node where this pokemon agent is going
     * @return
     */
    public int getDest();

    /**
     * Get the current speed of this pokemon agent
     * @return
     */
    public double getSpeed();

    /**
     * Get the current geo_locaation of this pokemon agent
     * @return
     */
    public geo_location getPos();

    ////////////////////////////////////// SETTERS ///////////////////////////////////////////

    /**
     * Set the amount of points gathered by this pokemon agent
     */
    public void setValue(double value);

    /**
     * Set the key of the current node where this pokemon agent is
     * @return
     */
    public void setSrc(int src);

    /**
     * Set the key of the next node where this pokemon agent is going
     * @return
     */
    public void setDest(int dest);

    /**
     * Set the current geo_locaation of this pokemon agent
     * @return
     */
    public void setPos(geo_location pos);


    /**
     * Set the current speed of this pokemon agent
     * @return
     */
    public void setSpeed(double i);









}
