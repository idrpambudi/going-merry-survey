package goingmerry.survey.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("survey")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Survey {
    @Id
    private String id;
    private String timestamp;
    private String ageRange;
    private String industry;
    private String jobTitle;
    private String salary;
    private String currency;
    private String location;
    private String experienceRange;
    private String jobContext;
    private String otherCurrency;
}
