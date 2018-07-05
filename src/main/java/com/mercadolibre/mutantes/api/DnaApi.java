package com.mercadolibre.mutantes.api;

import com.mercadolibre.mutantes.service.impl.DnaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DnaApi {
    @Autowired
    DnaServiceImpl dnaService;

    @RequestMapping("/")
    public String home(){
        return "Welcome to Mutant Application";
    }

    @RequestMapping(value = "/mutant/", method = RequestMethod.POST)
    public MutantResponse<DnaResponse> isMutant(@RequestBody DnaRequest dnaRequest, MutantResponse<DnaResponse> dnaResponse) {
        dnaResponse.setResult(dnaService.isMutant(dnaRequest));
        return dnaResponse.toStatus200OK();
    }
    @RequestMapping(value = "/stats/", method = RequestMethod.GET)
    public MutantResponse<StatsResponse> stats(MutantResponse<StatsResponse> statsResponse) {
        statsResponse.setResult(dnaService.getStatistics());
        return statsResponse.toStatus200OK();
    }

    @RequestMapping(value = "/consultValidatedDna/", method = RequestMethod.GET)
    public MutantResponse<List<DnaResponse>> getAll(MutantResponse<List<DnaResponse>> statsResponse) {
        statsResponse.setResult(dnaService.getAll());
        return statsResponse.toStatus200OK();
    }


}
