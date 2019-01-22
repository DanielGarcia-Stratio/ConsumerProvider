package com.example.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com.example:provider:+:stubs:8085"
)
public class ConsumerControllerTest {
    private final String  body= "{\n" +
            "  \"patientInfo\":{\n" +
            "    \"name\": \"Pepe\",\n" +
            "    \"firstSurname\":\"Florez\",\n" +
            "    \"secondSurname\":\"Garcia\",\n" +
            "    \"gender\": \"male\",\n" +
            "    \"medicalHistory\":\"XDSSI\",\n" +
            "    \"VIP\" : false\"\n" +
            "  },\n" +
            "  \"visitStatus\": \"done\",\n" +
            "  \"scheduledTime\": \"2010-06-15T00:00:00\",\n" +
            "  \"arrivalTime\": \"2010-06-15T02:00:00\",\n" +
            "  \"agenda\": \"XDD\",\n" +
            "  \"center\": \"Moraleja\",\n" +
            "  \"resource\": \"EF\",\n" +
            "  \"service\": \"enfermeria\",\n" +
            "  \"visitType\": \"revision\",\n" +
            "  \"insurance\": \"Sanitas SA\",\n" +
            "  \"observation\": \"Ha sufrido anteriormente de neumonia\",\n" +
            "  \"visitChannel\": \"videollamada\",\n" +
            "  \"overload\": false\n" +
            "}";
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void ShouldResponseFullJson() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/getVisit?code=1234")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(body));
    }
}
