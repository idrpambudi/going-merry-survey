package goingmerry.survey.util;

import goingmerry.survey.constant.Operator;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CriteriaOperatorTest {
    private static final Object VALUE = new Object();
    private static final String FIELD = "field";

    @InjectMocks
    private CriteriaOperator criteriaOperator;

    @Test
    public void nullOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, null, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
    }

    @Test
    public void eqOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, null, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
    }

    @Test
    public void gtOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, Operator.gt, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
        assertTrue(criteria.getCriteriaObject().get(FIELD, Document.class).containsKey("$gt"));
    }

    @Test
    public void gteOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, Operator.gte, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
        assertTrue(criteria.getCriteriaObject().get(FIELD, Document.class).containsKey("$gte"));
    }

    @Test
    public void ltOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, Operator.lt, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
        assertTrue(criteria.getCriteriaObject().get(FIELD, Document.class).containsKey("$lt"));
    }

    @Test
    public void lteOperator() {
        Criteria criteria = criteriaOperator.build(VALUE, Operator.lte, FIELD);
        assertNotNull(criteria);
        assertTrue(criteria.getCriteriaObject().containsKey(FIELD));
        assertTrue(criteria.getCriteriaObject().get(FIELD, Document.class).containsKey("$lte"));
    }
}
