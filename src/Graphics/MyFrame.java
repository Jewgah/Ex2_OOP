package Graphics;


import Server.Game_Server_Ex2;
import api.*;
import gameClient.Arena;
import gameClient.CL_Agent;
import gameClient.CL_Pokemon;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import static gameClient.Arena.EPS2;
import static gameClient.Arena.json2Pokemons;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.parallelSetAll;

public class MyFrame implements Runnable{
    private static gameClient.MyFrame _win;
    private static Arena _ar;
    private static long tz;
    private static int scenario;


    public MyFrame(long tz, int scenario){
        this.tz = tz;
        this.scenario = scenario;

    }

    public static void main(String[] a) {
//        Thread client = new Thread(new MyFrame());
//        client.start();
    }

    @Override
    public synchronized void run() {
        game_service game = Game_Server_Ex2.getServer(this.scenario); // you have [0,23] games
        //int id = 999;
        //game.login(this.tz)

        String g = game.getGraph();
        String pks = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        init(game);

        game.startGame();
        _win.setTitle("POKEMON GAME by JORDAN PEREZ & NATHANAEL BENICHOU | Tz: "+tz+" | Scenario number: "+scenario+" | ");


        int ind=0;
        long dt=150;


        while(game.isRunning()) {
            moveAgents(game, gg);
            try {
                if(ind%1==0) {_win.repaint();}
                Thread.sleep(dt);
                ind++;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        String res = game.toString();

        System.out.println(res);
        System.exit(0);
    }
    /**
     * Moves each of the agents along the edge,
     * in case the agent is on a node the next destination (next edge) is chosen (randomly).
     * @param game
     * @param gg
     * @param
     */
    private static void moveAgents(game_service game, directed_weighted_graph gg) {

        dw_graph_algorithms algo_g = new DWGraph_Algo();
        algo_g.init(gg);

        String lg = game.move(); //get the JSON String with all agent status
        List<CL_Agent> agentList = Arena.getAgents(lg, gg); //turn it into a list of agents
        _ar.setAgents(agentList); //updates arena agents

        //ArrayList<OOP_Point3D> rs = new ArrayList<OOP_Point3D>();

        String fs = game.getPokemons(); // get the JSON String with all pokemons status
        List<CL_Pokemon> pokeList = Arena.json2Pokemons(fs); //turn it into a list of pokemons

        //update edge of each pokemon in the pokelist
        for (CL_Pokemon pokemon : pokeList) {
            updateEdge(pokemon, gg);
        }


        _ar.setPokemons(pokeList); // updates arena pokemons as pokelist


        //Init Pokemon as not Targeted
        for  (CL_Pokemon pokemon :pokeList) {
            pokemon.setTargeted(false);
        }

            //Pass over the agent list
            for (CL_Agent agent : agentList) {

                //Pass over the Pokemon list
                for (CL_Pokemon pokemon : pokeList) {

                        //Initialise the node to go
                        NodeData nodeToGo = new NodeData(pokemon.get_edge().getDest());

                        //Set the distance of my current agent to my current pokemon
                         double shortestpath = algo_g.shortestPathDist(agent.getSrcNode(), nodeToGo.getKey());

                         //if there is no minimum distance registered from this pokemon to any agent OR
                     //if there is a bigger distance registered then keep the smallest
                         if(pokemon.getDistFromAgent() != -1 || pokemon.getDistFromAgent() > shortestpath){
                            pokemon.setDistFromAgent(shortestpath);
                         }
                }

                wichBest(agent,pokeList,gg);


            for (int i = 0; i < agentList.size(); i++) {
                CL_Agent cl_agent = agentList.get(i);

                int id = cl_agent.getID();
                int dest = cl_agent.getNextNode();
                int src = cl_agent.getSrcNode();
                double v = cl_agent.getValue();

                if (dest == -1) {
                    dest = nextNode(gg, cl_agent,pokeList);
                    game.chooseNextEdge(cl_agent.getID(), dest);

                    System.out.println("Agent: " + id + ", val: " + v + "   turned to node: " + dest+" i= "+i);

                }
            }
        }
    }




    private static void PokePrint(List<CL_Pokemon> pokelist){

        for (CL_Pokemon pokemon: pokelist )
        {
         edge_data ed = new EdgeData (pokemon.get_edge().getSrc(), pokemon.get_edge().getDest(), pokemon.get_edge().getWeight());

          pokemon.set_edge(ed);

            //System.out.println(pokemon.get_edge().getDest());
            //System.out.println("Pokemon "+index+"  : ["+pokemon.get_edge().toString().split(",")+"]");
        }


    }
    public static void updateEdge(CL_Pokemon fr, directed_weighted_graph g) {
        for (node_data v : g.getV()) {
            for (edge_data e : g.getE(v.getKey())) {
                boolean f = isOnEdge(fr.getLocation(), e, fr.getType(), g);
                if (f) {
                    fr.set_edge(e);
                }
            }
        }
    }
    private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest ) {

        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        if(dist>d1-EPS2) {ans = true;}
        return ans;
    }
    private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
        geo_location src = g.getNode(s).getLocation();
        geo_location dest = g.getNode(d).getLocation();
        return isOnEdge(p,src,dest);
    }
    private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
        int src = g.getNode(e.getSrc()).getKey();
        int dest = g.getNode(e.getDest()).getKey();
        if(type<0 && dest>src) {return false;}
        if(type>0 && src>dest) {return false;}
        return isOnEdge(p,src, dest, g);
    }



    /**
     * a very simple random walk implementation!
     * @param g
     * @param agent
     * @return
     */
    private static int nextNode(directed_weighted_graph g, CL_Agent agent,List<CL_Pokemon> pokeList) {

        dw_graph_algorithms algo_g = new DWGraph_Algo();
        algo_g.init(g);

        int[] arr_help = fromListToInt(algo_g.shortestPath(agent.getSrcNode(), agent.get_curr_fruit().get_edge().getDest()));

        if (arr_help.length==0){pokeList.remove(agent.get_curr_fruit());

            wichBest(agent,pokeList,g);
        }

        wichBest(agent,pokeList,g);
            int[] arr_path = fromListToInt(algo_g.shortestPath(agent.getSrcNode(), agent.get_curr_fruit().get_edge().getDest()));

            System.out.println();

            for (Integer k : arr_path) {
                System.out.print(k + ",");
            }
        System.out.println("]");
            System.out.println();

            int myKey = arr_path[0];
        wichBest(agent,pokeList,g);
            return myKey;
        }


    private static int[] fromListToInt(List<node_data> ln){
        int []arr = new int[ln.size()];
        int index= 0;

        for (node_data nd :ln) {
            arr[index]=nd.getKey();
            index++;
        }
        return copyOfRange(arr,1,arr.length);
    }






    private static boolean isOnGraph(CL_Pokemon pokemon) {
        if(pokemon.get_edge()!=null){
            return true;
        }
        else return false;

    }

    private static void wichBest(CL_Agent agent,List<CL_Pokemon> pokeList,directed_weighted_graph g) {

        dw_graph_algorithms algo_g = new DWGraph_Algo();
        algo_g.init(g);

        agent.set_curr_fruit(pokeList.get(1));

        //initialise the current pokemon of my agent to the first pokemon
            //Pass all over the pokeList.
            for (CL_Pokemon pokemon : pokeList) {
                //Checks if the pokemon is already targeted
                    //If the distance from my agent to my pokemon is bigger than my current pokemon
                    if (!pokemon.isTargeted() && algo_g.shortestPathDist(agent.getSrcNode(), agent.get_curr_fruit().get_edge().getDest()) > pokemon.getDistFromAgent()) {
                        //my current pokemon become the pokemon of my agent
                        //System.out.println("Coucou");
                      agent.get_curr_fruit().setTargeted(false);
                        agent.set_curr_fruit(pokemon);

                        //Set Pokemon to Targeted
                        pokemon.setTargeted(true);

                        //TODO : If Pokemon is eaten remove it from the PokeList

                    }


        }

    }




    private void init(game_service game) {
        String g = game.getGraph();
        String fs = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        //gg.init(g);
        _ar = new Arena();
        _ar.setGraph(gg);
        _ar.setPokemons(Arena.json2Pokemons(fs));
        _win = new gameClient.MyFrame("test Ex2");
        _win.setSize(1000, 700);
        _win.update(_ar);


        _win.show();
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int rs = ttt.getInt("agents");
            //System.out.println(info);
           // System.out.println(game.getPokemons());
            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
            ArrayList<CL_Pokemon> cl_fs = Arena.json2Pokemons(game.getPokemons());
            for(int a = 0;a<cl_fs.size();a++) { Arena.updateEdge(cl_fs.get(a),gg);}
            for(int a = 0;a<rs;a++) {
                int ind = a%cl_fs.size();
                CL_Pokemon c = cl_fs.get(ind);
                int nn = c.get_edge().getDest();
                if(c.getType()<0 ) {nn = c.get_edge().getSrc();}

                game.addAgent(nn);
            }
        }
        catch (JSONException e) {e.printStackTrace();}
    }
}

