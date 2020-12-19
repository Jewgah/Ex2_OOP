package Informations;

import api.edge_data;
import api.geo_location;

public class Pokemon implements pokemon_info{

    private double val;
    private edge_data edge;
    private geo_location pos;
    private int type;

    ////////////////////////////////////// GETTERS ///////////////////////////////////////////

    @Override
    public double getVal() {
        return this.val;
    }

    @Override
    public geo_location getPos() {
        return this.pos;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public edge_data getEdge() {
        return this.edge;
    }


    @Override
    public void setEdge(edge_data e) {
        this.edge=e;

    }

    @Override
    public void setPos(geo_location g) {
        this.pos=g;

    }


    @Override
    public String toString() {
        return "Pokemon{" +
                "val=" + val +
                ", edge=" + edge +
                ", pos=" + pos +
                ", type=" + type +
                '}';
    }
}
