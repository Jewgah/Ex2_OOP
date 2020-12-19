package Informations;
/**
 * This interface represents the set of information we need for a Pokemon.
 *
 * @author Nathanael Benichou & Jordan Perez
 *
 */
    import api.edge_data;
    import api.geo_location;

public interface pokemon_info {

    /**
     * Return the Value of my Pokemon
     *
     * @return
     */
    public double getVal();

    /**
     * Return the Type of my Pokemon
     *
     * @return
     */
    public int getType();

    /**
     * Returns the geoLocation of my pokemon
     *
     * @return
     */
    public geo_location getPos();


    /**
     * @return The Edge of my Pokemon
     */
    public edge_data getEdge();


    /**
     * To set the position of my pokemon.
     *
     * @param g
     */
    public void setPos(geo_location g);

    /**
     * Set the Edges of my pokemon
     *
     * @return
     */
    public void  setEdge(edge_data e );


}
