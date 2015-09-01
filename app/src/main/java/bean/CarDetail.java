package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/10.
 */
public class CarDetail implements Serializable {
    private String shortProvince;
    private String charCity;
    private String carNumberString;

    private String province;
    private String city;
    private String enginenumber;
    private String chejianumber;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getChejianumber() {
        return chejianumber;
    }

    public void setChejianumber(String chejianumber) {
        this.chejianumber = chejianumber;
    }

    public String getEnginenumber() {
        return enginenumber;
    }

    public void setEnginenumber(String enginenumber) {
        this.enginenumber = enginenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCharCity() {
        return charCity;
    }

    public void setCharCity(String charProvince) {
        this.charCity = charProvince;
    }

    public String getCarNumberString() {
        return carNumberString;
    }

    public void setCarNumberString(String carNumberString) {
        this.carNumberString = carNumberString;
    }

    public String getShortProvince() {
        return shortProvince;
    }

    public void setShortProvince(String shortProvince) {
        this.shortProvince = shortProvince;
    }


}
