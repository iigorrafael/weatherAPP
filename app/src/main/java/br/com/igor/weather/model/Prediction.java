package br.com.igor.weather.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Aluno on 18/09/2017.
 */

public class Prediction extends SugarRecord  {


    private String description;
    private String id;
    private List<MatchedSubstring> matchedSubstrings = null;
    private String placeId;
    private String reference;
    private List<Term> terms = null;
    private List<String> types = null;

    public Prediction() {
    }

    public Prediction(String description, String id, List<MatchedSubstring> matchedSubstrings, String placeId, String reference, List<Term> terms, List<String> types) {
        this.description = description;
        this.id = id;
        this.matchedSubstrings = matchedSubstrings;
        this.placeId = placeId;
        this.reference = reference;
        this.terms = terms;
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
        this.matchedSubstrings = matchedSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}


