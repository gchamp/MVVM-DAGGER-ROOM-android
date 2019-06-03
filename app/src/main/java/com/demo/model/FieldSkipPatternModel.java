package com.demo.model;

import java.io.Serializable;
import java.util.List;

public class FieldSkipPatternModel implements Serializable {

    public String id;
    public List<String> response;
    public String question_id;
    public String skip_questions;
    public String question_type;
    public String userId;
    public String formId;
}