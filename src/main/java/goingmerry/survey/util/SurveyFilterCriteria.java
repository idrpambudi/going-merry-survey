package goingmerry.survey.util;

import goingmerry.survey.client.SurveyFilterParams;
import goingmerry.survey.constant.SurveyField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyFilterCriteria {
    @Autowired
    private CriteriaOperator criteriaOperator;

    public List<Criteria> build(SurveyFilterParams filterParams) {
        List<Criteria> criterias = new ArrayList<>();
        if (filterParams.getTimestamp() != null) {
            criterias.add(criteriaOperator.build(
                    filterParams.getTimestamp(), filterParams.getTimestampOp(), SurveyField.timestamp.name()));
        }
        if (filterParams.getSalary() != null) {
            criterias.add(criteriaOperator.build(
                    filterParams.getSalary(), filterParams.getSalaryOp(), SurveyField.salary.name()));
        }
        if (filterParams.getAgeRange() != null) {
            criterias.add(Criteria.where(SurveyField.ageRange.name()).is(filterParams.getAgeRange()));
        }
        if (filterParams.getIndustry() != null) {
            criterias.add(Criteria.where(SurveyField.industry.name()).is(filterParams.getIndustry()));
        }
        if (filterParams.getJobTitle() != null) {
            criterias.add(Criteria.where(SurveyField.jobTitle.name()).is(filterParams.getJobTitle()));
        }
        if (filterParams.getCurrency() != null) {
            criterias.add(Criteria.where(SurveyField.currency.name()).is(filterParams.getCurrency()));
        }
        if (filterParams.getLocation() != null) {
            criterias.add(Criteria.where(SurveyField.location.name()).is(filterParams.getLocation()));
        }
        if (filterParams.getExperienceRange() != null) {
            criterias.add(Criteria.where(SurveyField.experienceRange.name()).is(filterParams.getExperienceRange()));
        }
        if (filterParams.getJobContext() != null) {
            criterias.add(Criteria.where(SurveyField.jobContext.name()).is(filterParams.getJobContext()));
        }
        if (filterParams.getOtherCurrency() != null) {
            criterias.add(Criteria.where(SurveyField.otherCurrency.name()).is(filterParams.getOtherCurrency()));
        }
        return criterias;
    }
}
