package goingmerry.survey;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SurveyController.class)
class SurveyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    void list_Ok() throws Exception {
        mockMvc.perform(get("/survey")).andExpect(status().isOk());
    }

    @Test
    void list_BadRequestParameters() throws Exception {
        mockMvc.perform(get("/survey?sort=incorrectField")).andExpect(status().isBadRequest());
    }

    @Test
    void get_Ok() throws Exception {
        mockMvc.perform(get("/survey/123")).andExpect(status().isOk());
    }

    @Test
    void get_BadRequestParameters() throws Exception {
        mockMvc.perform(get("/survey/123?fields=salary,incorrectField")).andExpect(status().isBadRequest());
    }

    @Test
    void count_Ok() throws Exception {
        mockMvc.perform(get("/survey/count")).andExpect(status().isOk());
    }

    @Test
    void count_BadRequestParameters() throws Exception {
        mockMvc.perform(get("/survey/count?salary=not-integer")).andExpect(status().isBadRequest());
    }
}
