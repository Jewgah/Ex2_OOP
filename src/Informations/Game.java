package Informations;

public class Game implements game_info{

    private boolean log;
    private int pokemon;
    private int moves;
    private int grade;
    private int scenario;
    private int id;
    private int agents;


    public Game( boolean log,  int grade,int scenario, int moves, int id,int pokemon,  int agents){
        this.log = log;
        this.pokemon = pokemon;
        this.moves = moves;
        this.grade = grade;
        this.scenario = scenario;
        this.id = id;
        this.agents = agents;
    }



    public Game( Game gg){
        this.log = gg.log;
        this.pokemon = gg.pokemon;
        this.moves = gg.moves;
        this.grade = gg.grade;
        this.scenario = gg.scenario;
        this.id = gg.id;
        this.agents = gg.agents;
    }



    @Override
    public double getPokemon() {
        return this.pokemon;
    }

    @Override
    public boolean isLog() {
        return this.log;
    }

    @Override
    public int moves() {
        return this.moves;
    }

    @Override
    public int grade() {
        return this.grade;
    }

    @Override
    public int scenario() {
        return this.scenario;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int agents() {
        return this.agents;
    }

    @Override
    public String toString() {
        return "Game{" +
                "log=" + log +
                ", pokemon=" + pokemon +
                ", moves=" + moves +
                ", grade=" + grade +
                ", scenario=" + scenario +
                ", id=" + id +
                ", agents=" + agents +
                '}';
    }

}
