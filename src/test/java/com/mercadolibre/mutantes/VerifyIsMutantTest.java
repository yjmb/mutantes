package com.mercadolibre.mutantes;

import com.mercadolibre.mutantes.pages.MutantPage;
import com.mercadolibre.mutantes.pages.TestBase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyIsMutantTest extends TestBase {
    MutantPage mutantPage = new MutantPage(request,response);

    @Test(priority = 0, description = "Post Mutant")
    public void postMutant(){
        response = mutantPage.postMutant();
    }
    @Test(priority = 1, description = "Status Code")
    public void checkStatusCode(){
        Assert.assertTrue(mutantPage.isStatusCode(response));
    }
    @Test(priority = 2, description = "Schema Validation")
    public void checkSchema(){
        Assert.assertTrue(mutantPage.isSchemaValid(response));
    }
    @Test(priority = 3, description = "Mutant Validation")
    public void checkMutant(){
        Assert.assertTrue(mutantPage.isMutant(response));
    }
    @Test(priority = 4, description = "Response Time")
    public void checkResponseTimeIsLessThan(){
        Assert.assertTrue(mutantPage.isResponseTimeLessThan(response, TIME));
    }

}
