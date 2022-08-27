package goingmerry.survey.util;

import goingmerry.survey.constant.Operator;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
public class CriteriaOperator {
    public Criteria build(Object value, Operator operator, String field) {
        Criteria criteria = Criteria.where(field);
        if (operator == null || operator == Operator.eq) {
            return criteria.is(value);
        } else if (operator == Operator.gt) {
            return criteria.gt(value);
        } else if (operator == Operator.gte) {
            return criteria.gte(value);
        } else if (operator == Operator.lt) {
            return criteria.lt(value);
        }
        return criteria.lte(value);
    }
}
