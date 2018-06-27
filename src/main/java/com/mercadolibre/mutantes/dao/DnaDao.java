package com.mercadolibre.mutantes.dao;

import com.mercadolibre.mutantes.dto.DnaDto;

import java.util.List;


public interface DnaDao {

    DnaDto saveDna(DnaDto dnaDto);

    boolean existsBySequence(String sequence);

    DnaDto getByDnaSequence(DnaDto dnaDto);

    float getStatistics();

    int getNumberMutants();

    int getNumberHumans();

    List<DnaDto> getAll();
}
