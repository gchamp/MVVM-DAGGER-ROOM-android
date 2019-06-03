package com.liveservey.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.List;

public class FormQuestionDbModel implements Serializable {

    public String id;
    public String userId;
    public String questionId;
    public String formId;
    public String stakeHolderId;
    public String questionKey;
    public String questionTitle;
    //Change
    public String questionImage;
    public String questionType;
    public String required;
    public String defaultValue;
    public String validationConditons;
    public String multifield_id;
    public List<OptionsModel> options;
    //public List<String> multifield_option;
    public String multifield_option;
    public String allowed_values_options;
    public String answer;
    public String isAnswer;
    public String visited;
    public String matrixRow;
    public String matrixColumn;
    public String matrixType;
    public String languageId;
    public int skipQuestion;
    public LogicModel logic;
    public int qcType;
    public int answerStatus;
    public int questionPointer;
    public String hidden;
    public String displayConditions;
    public String placeholder;
    public String size;
    public String max_length;
    public String minimum;
    public String maximum;
    public String future_date;
    public HelpText helptext;
    public int field_order;
    public int isInCorrect;
    public String startDate;





    public String getAnswer() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("question_id", questionId);
        jsonObject.addProperty("question_key", questionKey);
        jsonObject.addProperty("question_title", questionTitle);
        jsonObject.addProperty("question_type", questionType);
        jsonObject.addProperty("question_answer", answer);
        return new Gson().toJson(jsonObject);
    }

}