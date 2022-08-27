package goingmerry.survey.util;

import goingmerry.survey.client.SurveyFilterParams;
import goingmerry.survey.constant.Operator;
import goingmerry.survey.constant.SurveyField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyFilterCriteriaTest {
    @InjectMocks
    private SurveyFilterCriteria surveyFilterCriteria;

    @Mock
    private CriteriaOperator criteriaOperator;

    @Test
    public void timestampEq() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setTimestamp("timestamp");

        Criteria criteria = new Criteria();
        when(criteriaOperator.build(params.getTimestamp(), null, SurveyField.timestamp.name())).
                thenReturn(criteria);
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        verify(criteriaOperator, times(1)).build(params.getTimestamp(), null, SurveyField.timestamp.name());
    }

    @Test
    public void timestampNonEqOperator() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setTimestamp("timestamp");
        params.setTimestampOp(Operator.lt);

        Criteria criteria = new Criteria();
        when(criteriaOperator.build(params.getTimestamp(), Operator.lt, SurveyField.timestamp.name())).
                thenReturn(criteria);
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        verify(criteriaOperator, times(1)).build(params.getTimestamp(), Operator.lt, SurveyField.timestamp.name());
    }

    @Test
    public void salaryEq() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setSalary(10000);

        Criteria criteria = new Criteria();
        when(criteriaOperator.build(params.getSalary(), null, SurveyField.salary.name())).
                thenReturn(criteria);
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        verify(criteriaOperator, times(1)).build(params.getSalary(), null, SurveyField.salary.name());
    }

    @Test
    public void salaryNonEqOperator() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setSalary(10000);
        params.setSalaryOp(Operator.lt);

        Criteria criteria = new Criteria();
        when(criteriaOperator.build(params.getSalary(), Operator.lt, SurveyField.salary.name())).
                thenReturn(criteria);
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        verify(criteriaOperator, times(1)).build(params.getSalary(), Operator.lt, SurveyField.salary.name());
    }

    @Test
    public void ageRange() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setAgeRange("age - range");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.ageRange.name()));
    }

    @Test
    public void industry() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setIndustry("industry");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.industry.name()));
    }

    @Test
    public void jobTitle() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setJobTitle("job title");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.jobTitle.name()));
    }

    @Test
    public void currency() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setCurrency("CUR");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.currency.name()));
    }

    @Test
    public void location() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setLocation("loc/CA/US");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.location.name()));
    }

    @Test
    public void experienceRange() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setExperienceRange("exp - rience years");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.experienceRange.name()));
    }

    @Test
    public void jobContext() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setJobContext("job context");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.jobContext.name()));
    }

    @Test
    public void otherCurrency() {
        SurveyFilterParams params = new SurveyFilterParams();
        params.setOtherCurrency("RUC");
        List<Criteria> actual = surveyFilterCriteria.build(params);
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).getCriteriaObject().containsKey(SurveyField.otherCurrency.name()));
    }
}
