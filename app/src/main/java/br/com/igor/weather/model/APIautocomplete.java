package br.com.igor.weather.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Aluno on 18/09/2017.
 */

public class APIautocomplete extends SugarRecord {

    private String status;
    private List<Prediction> predictions = null;

    public APIautocomplete() {
    }

    public APIautocomplete(String status, List<Prediction> predictions) {
        this.status = status;
        this.predictions = predictions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

}


