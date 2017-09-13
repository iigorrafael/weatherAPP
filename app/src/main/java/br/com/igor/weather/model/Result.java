package br.com.igor.weather.model;

public class Result {
    private String by;
    private boolean valid_key;
    private Previsao results;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public boolean isValid_key() {
        return valid_key;
    }

    public void setValid_key(boolean valid_key) {
        this.valid_key = valid_key;
    }

    public Previsao getResults() {
        return results;
    }

    public void setResults(Previsao results) {
        this.results = results;
    }
}
