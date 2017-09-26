package br.com.igor.weather.model;


import com.orm.SugarRecord;

public class Forecast extends SugarRecord{

    private String date;
    private String weekday;
    private String max;
    private String min;
    private String description;
    private String condition;


    public Forecast() {
    }

    public Forecast(String date, String weekday, String max, String min, String description, String condition) {
        this.date = date;
        this.weekday = weekday;
        this.max = max;
        this.min = min;
        this.description = description;
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}