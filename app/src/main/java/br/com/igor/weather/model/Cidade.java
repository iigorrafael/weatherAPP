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
    private Forecast condicaoDia1;
    private Forecast condicaoDia2;
    private Forecast condicaoDia3;
    private Forecast condicaoDia4;
    private Forecast condicaoDia5;
    private String humidity;
    private String windSpeedy;
    private String sunrise;
    private String sunset;

    public Cidade() {
    }

    public Cidade(String nome, Integer temp, String date, String time, String condition_code, String description,
                  String currently, String condition_slug, Forecast condicaoDia1, Forecast condicaoDia2,
                  Forecast condicaoDia3, Forecast condicaoDia4, Forecast condicaoDia5,
                  String humidity, String windSpeedy, String sunrise, String sunset) {

        this.nome = nome;
        this.temp = temp;
        this.date = date;
        this.time = time;
        this.condition_code = condition_code;
        this.description = description;
        this.currently = currently;
        this.condition_slug = condition_slug;
        this.condicaoDia1 = condicaoDia1;
        this.condicaoDia2 = condicaoDia2;
        this.condicaoDia3 = condicaoDia3;
        this.condicaoDia4 = condicaoDia4;
        this.condicaoDia5 = condicaoDia5;
        this.humidity = humidity;
        this.windSpeedy = windSpeedy;
        this.sunrise = sunrise;
        this.sunset = sunset;
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

    public Forecast getCondicaoDia1() {
        return condicaoDia1;
    }

    public void setCondicaoDia1(Forecast condicaoDia1) {
        this.condicaoDia1 = condicaoDia1;
    }

    public Forecast getCondicaoDia2() {
        return condicaoDia2;
    }

    public void setCondicaoDia2(Forecast condicaoDia2) {
        this.condicaoDia2 = condicaoDia2;
    }

    public Forecast getCondicaoDia3() {
        return condicaoDia3;
    }

    public void setCondicaoDia3(Forecast condicaoDia3) {
        this.condicaoDia3 = condicaoDia3;
    }

    public Forecast getCondicaoDia4() {
        return condicaoDia4;
    }

    public void setCondicaoDia4(Forecast condicaoDia4) {
        this.condicaoDia4 = condicaoDia4;
    }

    public Forecast getCondicaoDia5() {
        return condicaoDia5;
    }

    public void setCondicaoDia5(Forecast condicaoDia5) {
        this.condicaoDia5 = condicaoDia5;
    }
}
