package com.mercadolibre.mutantes.dao.impl;

import com.mercadolibre.mutantes.dao.DnaDao;
import com.mercadolibre.mutantes.entity.DnaRepository;
import com.mercadolibre.mutantes.dto.DnaDto;
import com.mercadolibre.mutantes.entity.DnaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DnaDaoImpl implements DnaDao {

    @Autowired
    private DnaRepository dnaRepository;

    public DnaDto saveDna(DnaDto dnaDto){
        DnaEntity dnaEntity = new DnaEntity();
        String sequence=dnaDto.getDnaSequenceToString();

        dnaEntity.setDnaSequence(sequence);
        dnaEntity.setMutant(dnaDto.isMutant());

        dnaEntity= dnaRepository.saveAndFlush(dnaEntity);
        dnaDto.setId(dnaEntity.getId());

        return dnaDto;
    }

    public boolean existsBySequence(String sequence){
        return dnaRepository.existsByDnaSequence(sequence);
    }

    public DnaDto getByDnaSequence(DnaDto dnaDto){
        String sequence=dnaDto.getDnaSequenceToString();
        DnaEntity dnaEntity=dnaRepository.getByDnaSequence(sequence);
        if (dnaEntity!=null){
            dnaDto.setMutant(dnaEntity.isMutant());
            return  dnaDto;
        }else{
            return null;
        }
    }


    //Statistics of DNA verifications
    public float getStatistics(){
        int count_mutant_dna  = getNumberMutants();
        int count_human_dna = getNumberHumans();
        float ratio = count_mutant_dna/count_human_dna;
        return ratio;
    }

    public int getNumberMutants(){
        return dnaRepository.getNumberMutants();
    }

    public int getNumberHumans(){
        return dnaRepository.getNumberHumans();
    }

    public List<DnaDto> getAll(){
        List<DnaDto> dnaDaoList= new ArrayList<>();
        String[] dnaSequence;

        for(DnaEntity dnaEntity: dnaRepository.findAll()){
            DnaDto dnaDto = new DnaDto();
            dnaSequence = dnaEntity.getDnaSequence().split("-");
            dnaDto.setDnaSequence(dnaSequence);
            dnaDto.setMutant(dnaEntity.isMutant());
            dnaDto.setId(dnaEntity.getId());
            dnaDaoList.add(dnaDto);
        }
        return dnaDaoList;
    }

}
