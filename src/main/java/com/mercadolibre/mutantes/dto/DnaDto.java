package com.mercadolibre.mutantes.dto;


public class DnaDto {
    private long id;
    private String[] dnaSequence;
    private boolean isMutant;

    public DnaDto build(String[] dnaSequence) {
        this.setDnaSequence(dnaSequence);
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(String[] dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }

    public String getDnaSequenceToString(){
        String sequence="";
        for (String input : getDnaSequence() ){
            sequence= sequence.concat(input.toUpperCase()).concat("-");
        }
        return  sequence.substring(0,sequence.length()-1);
    }
}
