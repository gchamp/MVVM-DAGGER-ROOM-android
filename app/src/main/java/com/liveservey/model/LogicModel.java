package com.liveservey.model;

import java.io.Serializable;
import java.util.List;

public class LogicModel implements Serializable {

    public String required;
    public List<ValidationConditionModel> validation_conditions;
    public List<FieldSkipPatternModel> field_skip_pattern;
    public List<FormSkipPatternModel> form_skip_pattern;

}