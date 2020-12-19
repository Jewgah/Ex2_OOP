package Informations;

import api.GeoLocation;
import api.geo_location;

public class Agent implements agent_info{

    private int id; //agent id
    private int src; //current node
    private int dest; //next node
    private double value; //score
    private double speed; //current speed
    private geo_location pos; //current pos


    //Constructor
    public Agent(int id,int src, int dest,  double value, geo_location geo, double speed) {

        this.id = id;
        this.src = src;
        this.dest = dest;
        this.value = value;
        this.pos = geo;
        this.speed = speed;

    }


    //CopyConstructor
    public Agent(agent_info age){
        this.id = age.getId();
        this.src = age.getSrc();
        this.dest = age.getDest();
        this.value = age.getValue();
        this.pos = new GeoLocation(age.getPos());
        this.speed = age.getSpeed();
    }

    ////////////////////////////////////// GETTERS ///////////////////////////////////////////

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public geo_location getPos() {
        return this.pos;
    }

    ////////////////////////////////////// SETTERS ///////////////////////////////////////////

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void setSrc(int src) {
        this.src=src;
    }

    @Override
    public void setDest(int dest) {
        this.dest=dest;
    }

    @Override
    public void setPos(geo_location pos) {
        this.pos = pos;
    }

    @Override
    public void setSpeed(double speed) {

        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id= " + id +
                ", src= " + src +
                ", dest= " + dest +
                ", value= " + value +
                ", speed= " + speed +
                ", pos= " + pos +
                '}';
    }
}
