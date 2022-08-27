package goingmerry.survey;

import goingmerry.survey.client.SurveyFilterParams;
import goingmerry.survey.constant.SurveyField;
import goingmerry.survey.model.Survey;
import goingmerry.survey.util.SurveyFilterCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
    @Spy
    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private SurveyFilterCriteria surveyFilterCriteria;

    @Test
    public void list_NoFilterNoSort() {
        Query query = mock(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        List<Survey> expected = new ArrayList<>();
        expected.add(new Survey());

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(expected));
        List<Survey> actual = surveyService.list(null, null);
        assertEquals(expected.size(), actual.size());
        verify(query, times(0)).addCriteria(any());
        verify(query, times(0)).with(any(Sort.class));
    }

    @Test
    public void list_WithFilter() {
        Query query = mock(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        SurveyFilterParams params = new SurveyFilterParams();
        List<Survey> expected = new ArrayList<>();
        expected.add(new Survey());
        List<Criteria> filterCriterias = new ArrayList<>();
        filterCriterias.add(new Criteria());

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(expected));
        when(surveyFilterCriteria.build(any(SurveyFilterParams.class))).thenReturn(filterCriterias);
        List<Survey> actual = surveyService.list(null, params);
        assertEquals(expected.size(), actual.size());
        verify(query, times(1)).addCriteria(any());
        verify(query, times(0)).with(any(Sort.class));
    }

    @Test
    public void list_WithFilterAndSort() {
        Query query = mock(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        SurveyFilterParams params = new SurveyFilterParams();
        List<Survey> expected = new ArrayList<>();
        expected.add(new Survey());
        List<Criteria> filterCriterias = new ArrayList<>();
        filterCriterias.add(new Criteria());

        when(mongoTemplate.find(any(Query.class), any())).thenReturn(Collections.singletonList(expected));
        when(surveyFilterCriteria.build(any(SurveyFilterParams.class))).thenReturn(filterCriterias);
        List<Survey> actual = surveyService.list(SurveyField.salary, params);
        assertEquals(expected.size(), actual.size());
        verify(query, times(1)).addCriteria(any());
        verify(query, times(1)).with(any(Sort.class));
    }

    @Test
    public void get() {
        Query query = mock(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        Survey survey = new Survey();

        when(mongoTemplate.findOne(any(Query.class), any())).thenReturn(survey);
        Survey actual = surveyService.get("id", Collections.emptyList());
        assertEquals(survey, actual);
        verify(query, times(0)).fields();
    }

    @Test
    public void get_SparseFields() {
        Query query = spy(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        Survey survey = new Survey();

        when(mongoTemplate.findOne(any(Query.class), any())).thenReturn(survey);
        Survey actual = surveyService.get("id", Arrays.asList(SurveyField.ageRange, SurveyField.id));
        assertEquals(survey, actual);
        verify(query, times(1)).fields();
    }

    @Test
    public void count_UnFiltered() {
        Query query = spy(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        SurveyFilterParams params = new SurveyFilterParams();
        List<Criteria> filterCriterias = new ArrayList<>();

        when(surveyFilterCriteria.build(any(SurveyFilterParams.class))).thenReturn(filterCriterias);
        when(mongoTemplate.count(any(Query.class), any(Survey.class.getClass()))).thenReturn(10L);
        long actual = surveyService.count(params);
        assertEquals(10L, actual);
        verify(query, times(0)).addCriteria(any());
    }

    @Test
    public void count_Filtered() {
        Query query = spy(Query.class);
        when(surveyService.createQuery()).thenReturn(query);

        SurveyFilterParams params = new SurveyFilterParams();
        List<Criteria> filterCriterias = new ArrayList<>();
        filterCriterias.add(new Criteria());

        when(surveyFilterCriteria.build(any(SurveyFilterParams.class))).thenReturn(filterCriterias);
        when(mongoTemplate.count(any(Query.class), any(Survey.class.getClass()))).thenReturn(10L);
        long actual = surveyService.count(params);
        assertEquals(10L, actual);
        verify(query, times(1)).addCriteria(any());
    }
}
