package bean;

import java.util.List;

/**
 * Created by Administrator on 2015/8/11.
 */
public class CarAccident {
    private String province;
    private String city;
    private String hphm;
    private String hpzl;
    private List<CarAccidentDetail> list;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public List<CarAccidentDetail> getList() {
        return list;
    }

    public void setList(List<CarAccidentDetail> list) {
        this.list = list;
    }
}
