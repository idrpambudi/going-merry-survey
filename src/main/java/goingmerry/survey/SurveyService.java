package goingmerry.survey;

import goingmerry.survey.client.SurveyFilterParams;
import goingmerry.survey.constant.SurveyField;
import goingmerry.survey.model.Survey;
import goingmerry.survey.util.SurveyFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {
    private static final int MAX_LIST_SIZE = 1000;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SurveyFilterCriteria surveyFilterCriteria;

    public List<Survey> list(SurveyField sort, SurveyFilterParams filterParams) {
        Query query = createQuery();
        List<Criteria> filterCriterias = surveyFilterCriteria.build(filterParams);
        if (filterCriterias != null && filterCriterias.size() > 0) {
            query.addCriteria(new Criteria().andOperator(filterCriterias.toArray(new Criteria[0])));
        }
        if (sort != null) {
            query.with(Sort.by(sort.name()));
        }
        query.with(PageRequest.of(0, MAX_LIST_SIZE));
        return mongoTemplate.find(query, Survey.class);
    }

    public Survey get(String id, List<SurveyField> fields) {
        Query query = createQuery();
        query.addCriteria(Criteria.where(SurveyField.id.name()).is(id));
        if (fields != null && fields.size() > 0) {
            query.fields().include(fields.stream().map(Enum::name).toArray(String[]::new));
        }
        return mongoTemplate.findOne(query, Survey.class);
    }

    public long count(SurveyFilterParams filterParams) {
        Query query = createQuery();
        List<Criteria> filterCriterias = surveyFilterCriteria.build(filterParams);
        if (filterCriterias != null && filterCriterias.size() > 0) {
            query.addCriteria(new Criteria().andOperator(filterCriterias.toArray(new Criteria[0])));
        }
        return mongoTemplate.count(query, Survey.class);
    }

    protected Query createQuery() {
        return new Query();
    }
}
