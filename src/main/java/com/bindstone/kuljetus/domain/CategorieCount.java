package com.bindstone.kuljetus.domain;

import com.bindstone.kuljetus.domain.enumeration.Categorie;

import java.util.StringJoiner;

public class CategorieCount {

    Long count;
    private Categorie categorieStatec;

    public Categorie getCategorieStatec() {
        return categorieStatec;
    }

    public void setCategorieStatec(Categorie categorieStatec) {
        this.categorieStatec = categorieStatec;
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
                .add("categorieStatec='" + categorieStatec + "'")
                .add("count='" + count + "'")
                .toString().concat("\n");
    }
}
