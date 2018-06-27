package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.api.DnaRequest;
import com.mercadolibre.mutantes.api.DnaResponse;
import com.mercadolibre.mutantes.api.StatsResponse;
import com.mercadolibre.mutantes.dto.DnaDto;

import java.util.List;

public interface DnaService{


    DnaResponse isMutant (DnaRequest dnaRequest);

    StatsResponse getStatistics();

    List<DnaResponse> getAll();
}
