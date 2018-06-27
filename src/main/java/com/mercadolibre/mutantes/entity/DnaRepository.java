package com.mercadolibre.mutantes.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DnaRepository extends JpaRepository<DnaEntity, Long> {

    boolean existsByDnaSequence(String dna_sequence);

    DnaEntity getByDnaSequence(String dna_sequence);

    @Query("SELECT count(d) FROM DnaEntity d WHERE is_mutant=1")
    int getNumberMutants();

    @Query("SELECT count(d) FROM DnaEntity d WHERE is_mutant=0")
    int getNumberHumans();

    @Override
    List<DnaEntity> findAll();
}
