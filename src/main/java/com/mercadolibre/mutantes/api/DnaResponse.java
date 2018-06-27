package com.mercadolibre.mutantes.api;

public class DnaResponse {

    private String[] dnaSequencing;
    private boolean isMutant;

    public DnaResponse(String[] dnaSequencing, boolean isMutant) {
        this.dnaSequencing = dnaSequencing;
        this.isMutant = isMutant;
    }

    public String[] getDnaSequencing() {
        return dnaSequencing;
    }

    public void setDnaSequencing(String[] dnaSequencing) {
        this.dnaSequencing = dnaSequencing;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }
}
