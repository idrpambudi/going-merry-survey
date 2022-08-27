package goingmerry.survey.client;

import goingmerry.survey.constant.Operator;
import lombok.Data;

@Data
public class SurveyFilterParams {
    private String timestamp;
    private Operator timestampOp;
    private String ageRange;
    private String industry;
    private String jobTitle;
    private Integer salary;
    private Operator salaryOp;
    private String currency;
    private String location;
    private String experienceRange;
    private String jobContext;
    private String otherCurrency;
}
