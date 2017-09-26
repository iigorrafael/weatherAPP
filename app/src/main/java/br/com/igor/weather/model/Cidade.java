package br.com.igor.weather.model;

import com.google.common.base.Strings;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Aluno on 29/08/2017.
 */

public class Cidade extends SugarRecord {

    private String nome;
    private Integer temp;
    private String date;
    private String time;
    private String condition_code;
    private String description;
    private String currently;
    private String condition_slug;
    private String humidity;
    private String windSpeedy;
    private String sunrise;
    private String sunset;
    private String weekday1;
    private String max1;
    private String min1;
    private String weekday2;
    private String max2;
    private String min2;
    private String weekday3;
    private String max3;
    private String min3;
    private String weekday4;
    private String max4;
    private String min4;
    private String weekday5;
    private String max5;
    private String min5;


    public Cidade() {
    }

    public Cidade(String nome, Integer temp, String date, String time, String condition_code, String
            description, String currently, String condition_slug, String humidity, String windSpeedy,
                  String sunrise, String sunset, String weekday1, String max1, String min1, String
                          weekday2, String max2, String min2, String weekday3, String max3, String
                          min3, String weekday4, String max4, String min4, String weekday5, String max5, String min5) {
        this.nome = nome;
        this.temp = temp;
        this.date = date;
        this.time = time;
        this.condition_code = condition_code;
        this.description = description;
        this.currently = currently;
        this.condition_slug = condition_slug;
        this.humidity = humidity;
        this.windSpeedy = windSpeedy;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.weekday1 = weekday1;
        this.max1 = max1;
        this.min1 = min1;
        this.weekday2 = weekday2;
        this.max2 = max2;
        this.min2 = min2;
        this.weekday3 = weekday3;
        this.max3 = max3;
        this.min3 = min3;
        this.weekday4 = weekday4;
        this.max4 = max4;
        this.min4 = min4;
        this.weekday5 = weekday5;
        this.max5 = max5;
        this.min5 = min5;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeedy() {
        return windSpeedy;
    }

    public void setWindSpeedy(String windSpeedy) {
        this.windSpeedy = windSpeedy;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCondition_code() {
        return condition_code;
    }

    public void setCondition_code(String condition_code) {
        this.condition_code = condition_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrently() {
        return currently;
    }

    public void setCurrently(String currently) {
        this.currently = currently;
    }

    public String getCondition_slug() {
        return condition_slug;
    }

    public void setCondition_slug(String condition_slug) {
        this.condition_slug = condition_slug;
    }

    public String getWeekday1() {
        return weekday1;
    }

    public void setWeekday1(String weekday1) {
        this.weekday1 = weekday1;
    }

    public String getMax1() {
        return max1;
    }

    public void setMax1(String max1) {
        this.max1 = max1;
    }

    public String getMin1() {
        return min1;
    }

    public void setMin1(String min1) {
        this.min1 = min1;
    }

    public String getWeekday2() {
        return weekday2;
    }

    public void setWeekday2(String weekday2) {
        this.weekday2 = weekday2;
    }

    public String getMax2() {
        return max2;
    }

    public void setMax2(String max2) {
        this.max2 = max2;
    }

    public String getMin2() {
        return min2;
    }

    public void setMin2(String min2) {
        this.min2 = min2;
    }

    public String getWeekday3() {
        return weekday3;
    }

    public void setWeekday3(String weekday3) {
        this.weekday3 = weekday3;
    }

    public String getMax3() {
        return max3;
    }

    public void setMax3(String max3) {
        this.max3 = max3;
    }

    public String getMin3() {
        return min3;
    }

    public void setMin3(String min3) {
        this.min3 = min3;
    }

    public String getWeekday4() {
        return weekday4;
    }

    public void setWeekday4(String weekday4) {
        this.weekday4 = weekday4;
    }

    public String getMax4() {
        return max4;
    }

    public void setMax4(String max4) {
        this.max4 = max4;
    }

    public String getMin4() {
        return min4;
    }

    public void setMin4(String min4) {
        this.min4 = min4;
    }

    public String getWeekday5() {
        return weekday5;
    }

    public void setWeekday5(String weekday5) {
        this.weekday5 = weekday5;
    }

    public String getMax5() {
        return max5;
    }

    public void setMax5(String max5) {
        this.max5 = max5;
    }

    public String getMin5() {
        return min5;
    }

    public void setMin5(String min5) {
        this.min5 = min5;
    }
}
