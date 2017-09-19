package br.com.igor.weather.model;

import com.orm.SugarRecord;

/**
 * Created by Aluno on 18/09/2017.
 */

public class MatchedSubstring extends SugarRecord {

    private Integer length;
    private Integer offset;

    public MatchedSubstring() {
    }

    public MatchedSubstring(Integer length, Integer offset) {
        this.length = length;
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
