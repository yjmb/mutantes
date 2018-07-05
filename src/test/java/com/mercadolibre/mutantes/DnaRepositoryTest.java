package com.mercadolibre.mutantes;

import com.mercadolibre.mutantes.entity.DnaEntity;
import com.mercadolibre.mutantes.entity.DnaRepository;
import com.mercadolibre.mutantes.pages.TestBase;
import com.mercadolibre.mutantes.utils.JSonUtil;
import com.mercadolibre.mutantes.utils.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DnaRepositoryTest extends TestBase {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DnaRepository repository;

    @Test
    public void testSave() throws Exception {
        String sequence="";
        JSONObject jsonObject = JSonUtil.readAndValidateJSON();
        JSONArray jsonArray= (JSONArray) jsonObject.get("dnaSequencing");
        Iterator iterator =jsonArray.iterator();
        while (iterator.hasNext()) {
            sequence= sequence.concat(iterator.next().toString().toUpperCase()).concat("-");
        }
        sequence=sequence.substring(0,sequence.length()-1);
        this.entityManager.persist(new DnaEntity(sequence, true));
        try{
            DnaEntity dnaEntity = this.repository.getByDnaSequence(sequence);
            assertThat(dnaEntity.getDnaSequence()).isEqualTo(sequence);
            assertThat(dnaEntity.isMutant()).isEqualTo(true);
        }catch (IncorrectResultSizeDataAccessException e){
            Logger.printError("You must try a different DNA sequence: ".concat(e.getMessage()));
            assert(false);
        }
    }

}