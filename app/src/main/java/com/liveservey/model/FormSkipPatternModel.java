package com.liveservey.model;

import java.io.Serializable;
import java.util.List;

public class FormSkipPatternModel implements Serializable {

    public String id;
    public String formId;
    public String userId;
    public String question_id;
    public List<String> response;
    public String skip_forms;
    public String question_type;

}