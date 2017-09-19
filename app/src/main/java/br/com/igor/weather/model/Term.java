package br.com.igor.weather.model;

import com.orm.SugarRecord;

/**
 * Created by Aluno on 18/09/2017.
 */

public class Term extends SugarRecord {

    private Integer offset;
    private String value;

    public Term() {
    }

    public Term(Integer offset, String value) {
        this.offset = offset;
        this.value = value;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
