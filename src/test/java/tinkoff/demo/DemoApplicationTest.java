package tinkoff.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tinkoff.demo.domain.ApplicationModel;
import tinkoff.demo.repository.ApplicationRepository;
import tinkoff.demo.service.ApplicationService;
import tinkoff.demo.utils.ApplicationNotFoundException;
import wiremock.org.apache.commons.lang3.tuple.ImmutablePair;

import java.math.BigInteger;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTest {

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
    @BeforeAll
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        var pair = givenApplicationModel();
        applicationRepository.save(pair.left);
        applicationRepository.save(pair.right);
    }

    @AfterAll
    void tearDown() {
        applicationRepository.deleteAll();
    }


    @Test
    void getLastApplication() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URL + CONTACT_ID_TEST)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        var json = mvcResult.getResponse().getContentAsString();
        var resultModel = objectMapper.readValue(json, ApplicationModel.class);
        Assertions.assertThat(resultModel).isEqualToComparingFieldByField(applicationModelLast);
    }

    @Test
    void applicationAbsenceTest() {
        assertThrows(ApplicationNotFoundException.class, () -> applicationService.getApplicationModelById(new BigInteger("0000")));
    }

    @Test
    void serviceTest() {
        var res = applicationService.getApplicationModelById(new BigInteger("1234"));
        Assertions.assertThat(res.getProductName()).isEqualTo((PRODUCT2_NAME_TEST));
    }

    @Test
    void dbTest(){
        String h2 = "jdbc:h2:mem:testdb";
        var ds = new DriverManagerDataSource(h2,"sa",null);
        JdbcTemplate jdbc = new JdbcTemplate(ds);
        Assertions.assertThat(
                jdbc.queryForObject("SELECT COUNT (*) FROM INFORMATION_SCHEMA.USERS ", Integer.class)).isGreaterThan(0);
    }

    private ImmutablePair<ApplicationModel, ApplicationModel> givenApplicationModel() {

        applicationModel.setContactId(CONTACT_ID_TEST);
        applicationModel.setProductName(PRODUCT1_NAME_TEST);
        applicationModel.setApplicationId(new BigInteger("1"));
        applicationModel.setCrtDate(new Timestamp(System.currentTimeMillis()));

        applicationModelLast.setContactId(CONTACT_ID_TEST);
        applicationModelLast.setProductName(PRODUCT2_NAME_TEST);
        applicationModelLast.setApplicationId(new BigInteger("2"));
        applicationModelLast.setCrtDate(new Timestamp(System.currentTimeMillis() * 2));

        return ImmutablePair.of(applicationModel, applicationModelLast);
    }

}




