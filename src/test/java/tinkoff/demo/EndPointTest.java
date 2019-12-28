package tinkoff.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.exceptions.ApplicationNotFoundException;
import tinkoff.demo.repository.ApplicationRepository;
import tinkoff.demo.service.ApplicationService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest()
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class EndPointTest {

    private MockMvc mvc;
    private ApplicationModel applicationModel = new ApplicationModel();
    private ApplicationModel applicationModelLast = new ApplicationModel();
    private BigInteger CONTACT_ID_TEST = new BigInteger("1234");
    private String PRODUCT1_NAME_TEST = "testProduct1";
    private String PRODUCT2_NAME_TEST = "testProduct2";
    private ObjectMapper objectMapper = new ObjectMapper();
    private String URL = "/tinkoff/";


    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationService applicationService;

    @SneakyThrows
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        applicationModel.setContactId(CONTACT_ID_TEST);
        applicationModel.setProductName(PRODUCT1_NAME_TEST);
        applicationModel.setApplicationId(new BigInteger("1"));
        applicationModel.setCrtDate(new Timestamp(System.currentTimeMillis()));

        TimeUnit.SECONDS.sleep(2);

        applicationModelLast.setContactId(CONTACT_ID_TEST);
        applicationModelLast.setProductName(PRODUCT2_NAME_TEST);
        applicationModelLast.setApplicationId(new BigInteger("1"));
        applicationModelLast.setCrtDate(new Timestamp(System.currentTimeMillis()));

        applicationRepository.save(applicationModel);
        applicationRepository.save(applicationModelLast);

    }

    @After
    public void tearDown() {
        applicationRepository.deleteAll();
    }


    /*
        Checking if returned result equals to the last application saved.
     */

    @Test
    public void getLastApplication() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URL + CONTACT_ID_TEST)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

       String json = mvcResult.getResponse().getContentAsString();
       ApplicationModel resultModel = objectMapper.readValue(json, ApplicationModel.class);
       Assertions.assertThat(resultModel).isEqualToComparingFieldByField(applicationModelLast);
    }

    @Test(expected = ApplicationNotFoundException.class)
    public void applicationAbsenceTest() throws Exception  {
        applicationService.getApplicationModelById(new BigInteger("0000"));
    }



}
