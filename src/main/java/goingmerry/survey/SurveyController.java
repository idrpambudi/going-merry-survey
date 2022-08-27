package goingmerry.survey;

import goingmerry.survey.client.SurveyFilterParams;
import goingmerry.survey.constant.SurveyField;
import goingmerry.survey.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public @ResponseBody List<Survey> list(
            @RequestParam(required=false) SurveyField sort,
            SurveyFilterParams filterParams) {
        return surveyService.list(sort, filterParams);
    }

    @GetMapping("/{id}")
    public @ResponseBody Survey get(
            @PathVariable String id,
            @RequestParam(required=false) List<SurveyField> fields) {
        return surveyService.get(id, fields);
    }

    @GetMapping("/count")
    public @ResponseBody long count(SurveyFilterParams filterParams) {
        return surveyService.count(filterParams);
    }
}
