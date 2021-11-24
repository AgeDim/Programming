package beans;

import org.kopitubruk.util.json.JSONUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Entry implements Serializable {
    private double xval;
    private double yval;
    private double rval;
    private String currentTime;
    private String executeTime;
    private boolean result;

    public Entry() {

    }

    public double getX() {
        return xval;
    }

    public void setX(double xval) {
        this.xval = xval;
    }

    public double getY() {
        return yval;
    }

    public void setY(double yval) {
        this.yval = yval;
    }

    public double getR() {
        return rval;
    }

    public void setR(double rval) {
        this.rval = rval;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String dataToJSON(){
        Map<String, String> dataFields = new HashMap<>();
        dataFields.put("xval", String.valueOf(xval));
        dataFields.put("yval", String.valueOf(yval));
        dataFields.put("rval", String.valueOf(rval));
        dataFields.put("currentTime", currentTime);
        dataFields.put("executeTime", executeTime);
        dataFields.put("result", String.valueOf(result));
        return JSONUtil.toJSON(dataFields);
    }
}