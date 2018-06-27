package com.mercadolibre.mutantes.service.impl;

import com.mercadolibre.mutantes.api.DnaRequest;
import com.mercadolibre.mutantes.api.DnaResponse;
import com.mercadolibre.mutantes.api.StatsResponse;
import com.mercadolibre.mutantes.dao.DnaDao;
import com.mercadolibre.mutantes.dto.DnaDto;
import com.mercadolibre.mutantes.exception.BusinessException;
import com.mercadolibre.mutantes.service.DnaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DnaServiceImpl implements DnaService{
    @Autowired
    private DnaDao dnaDao;

    public DnaResponse isMutant (DnaRequest dnaRequest)  {
        DnaDto dnaDto = new DnaDto().build(dnaRequest.getDnaSequencing());
        if(dnaDao.existsBySequence(dnaDto.getDnaSequenceToString())){
            dnaDto = dnaDao.getByDnaSequence(dnaDto);
            if (!dnaDto.isMutant()){
                throw new BusinessException("No es un Mutante");
            }
        }else {
            if (!isMutant(dnaRequest.getDnaSequencing())) {
                dnaDto.setMutant(false);
                dnaDao.saveDna(dnaDto);
                throw new BusinessException("No es un Mutante");
            }
            dnaDto.setMutant(true);
            dnaDao.saveDna(dnaDto);
        }
        return new DnaResponse(dnaDto.getDnaSequence(),true);
    }


    public StatsResponse getStatistics(){
        StatsResponse statsResponse = new StatsResponse();
        statsResponse.setCount_human_dna(dnaDao.getNumberHumans());
        statsResponse.setCount_mutant_dna(dnaDao.getNumberMutants());
        statsResponse.setRatio(dnaDao.getStatistics());

        return statsResponse;
    }


    public List<DnaResponse> getAll(){
        List<DnaResponse> dnaResponseList = new ArrayList<>();
        for(DnaDto dnaDto: dnaDao.getAll() ){
            DnaResponse dnaResponse= new DnaResponse(dnaDto.getDnaSequence(),dnaDto.isMutant());
            dnaResponseList.add(dnaResponse);
        }
        return dnaResponseList;
    }

    public boolean isMutant (String[] dna) {
        int sequenceCount=0;
        String columns[]=new String[dna.length];
        String mainDiagonals[]=new String[dna.length*2];
        String secondaryDiagonals[]=new String[dna.length*2];
        int inputLength;
        int rowNumber=0;

        //Iterate rows
        for (String input : dna){
            input=input.toUpperCase();
            validateLength(input,dna.length);
            validateCharacters (input);

            inputLength =input.length();
            if(searchSequence(input)){
                sequenceCount++;
                if (sequenceCount>=2) break;
            }

            //Build array of columns
            for(int i=0; i < inputLength; i++ ){
                if (columns[i]==null){columns[i]="";}

                columns[i]=columns[i].concat(String.valueOf(input.charAt(i)));
            }

            //Build array of diagonals
            for (int i=0; i<inputLength; i++){
                if (mainDiagonals[i]==null){
                    mainDiagonals[i]="";
                    mainDiagonals[inputLength+i]="";
                    secondaryDiagonals[i]="";
                    secondaryDiagonals[inputLength+i]="";
                }

                // (Diagonal to the right) (\\\\\)
                try{
                    mainDiagonals[i]=mainDiagonals[i].concat(String.valueOf(input.charAt(i+rowNumber)));
                }catch (Exception e){
                    //nothing
                }
                if (rowNumber >0){
                    try{
                        mainDiagonals[inputLength+i]=mainDiagonals[inputLength+i].concat(String.valueOf(input.charAt(rowNumber-(i+1))));
                    }catch (Exception e){
                        //nothing
                    }
                }

                //Diagonal to the left (//////)
                try{
                    secondaryDiagonals[i]=secondaryDiagonals[i].concat(String.valueOf(input.charAt(inputLength-(1+i)-rowNumber)));
                }catch (Exception e){
                    //nothing
                }
                if (rowNumber >0 && i <rowNumber){
                    try{
                        secondaryDiagonals[inputLength+i]=secondaryDiagonals[inputLength+i].concat(String.valueOf(input.charAt(inputLength-rowNumber+i)));
                    }catch (Exception e){
                        //nothing
                    }
                }
            }
            rowNumber++;
        }

        if (sequenceCount<2) {
            //Iterate columns
            for (String input : columns){
                if(searchSequence(input)){
                    sequenceCount++;
                    if (sequenceCount>=2) break;
                }
            }
            //Iterate diagonal to the right  \\\\\
            for (String input : mainDiagonals){
                if(searchSequence(input)){
                    sequenceCount++;
                    if (sequenceCount>=2) break;
                }
            }
            //Iterate diagonal to the left  ////
            for (String input : secondaryDiagonals){
                if(searchSequence(input)){
                    sequenceCount++;
                    if (sequenceCount>=2) break;
                }
            }

        }
        return  (sequenceCount>=2);
    }

    public boolean searchSequence(String input){
        if (input.length()<4){
            return  false;
        }
        //check that you have 4 equal letters
        Pattern p = Pattern.compile("(.)\\1{3}");
        Matcher m = p.matcher(input);
        return m.find();
    }

    public void validateCharacters (String input){
        if (!StringUtils.containsOnly(input, new char[] {'A','C','T','G'})){
           throw new BusinessException("Sólo se permiten la letras: A,T,C,G, las cuales representan cada base nitrogenada del ADN");
        }
    }

    public void validateLength(String input, int length){
        if (input.length()!= length){
            throw new BusinessException("Dimensión de la matriz inválida. Debe ser una matriz NxN");
        }
    }
}
