package com.mercadolibre.mutantes.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class DnaEntity implements Serializable{

    private static final long serialVersionUID = 4894;

    @Id
    @GeneratedValue
    private long id;
    private String dnaSequence;
    private boolean isMutant;

    public DnaEntity() {
    }

    public DnaEntity(String dnaSequence, boolean isMutant) {
        this.dnaSequence = dnaSequence;
        this.isMutant = isMutant;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(String dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }

}
