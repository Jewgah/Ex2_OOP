package Informations;
/**
 * This interface represents the set of information we need for a Pokemon.
 *
 * @author Nathanael Benichou & Jordan Perez
 *
 */

public interface game_info  {
    /**
     * Return the pokemon
     *
     * @return double
     */
    public double getPokemon();

    /**
     * Return 1 if the User is logged on ,0 if he's not.
     *
     * @return   boolean
     */
    public boolean isLog();

    /**
     * Return the move
     *
     * @return  int
     */
    public int moves();

    /**
     * Return the grade of the game
     *
     * @return  int
     */
     public int grade();

    /**
     * Return the scenario
     *
     * @return  int
     */
    public int scenario();


    /**
     * Return the Id
     *
     * @return  int
     */
     public int getId();


    /**
     * Return the agent
     *
     * @return  int
     */
    public int agents();




}
