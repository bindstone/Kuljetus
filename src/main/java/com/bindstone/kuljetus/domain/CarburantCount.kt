package com.bindstone.kuljetus.domain;


import java.util.StringJoiner;

public class CarburantCount {

    Long count;
    private String libelleCarburant;

    public String getLibelleCarburant() {
        return libelleCarburant;
    }

    public void setLibelleCarburant(String libelleCarburant) {
        this.libelleCarburant = libelleCarburant;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "[", "]")
                .add("libelleCarburant='" + libelleCarburant + "'")
                .add("count='" + count + "'")
                .toString().concat("\n");
    }
}
