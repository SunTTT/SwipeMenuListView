package com.suntt.swipemenulistview;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.CityDetail;
import db.CarDB;

/**
 * Created by Administrator on 2015/8/13.
 */
public class GetJsonToProvince {
    private CarDB carDB;
    private String[] provincelist = {"BJ", "SH", "ZJ", "FJ", "JL", "LN", "SD", "HN", "JS", "SX", "QH", "GD", "FB", "HLJ", "AH", "YN", "XS", "HAN", "GZ", "XJ", "GS", "NX", "XZ", "CQ", "GX"
    };

    public void  JsonToProvince(Context context,String response) {
        CityDetail cityDetail = new CityDetail();
        carDB = CarDB.getInstance(context);
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            String resultcode = jsonObject.get("resultcode").toString();
            String reason = jsonObject.get("reason").toString();
            if (resultcode.equals("200")) {
                JSONObject result = (JSONObject) jsonObject.get("result");
                for (int i = 0; i < provincelist.length; i++) {
                    JSONObject city = (JSONObject) result.get(provincelist[i]);
                    String province = city.get("province").toString();
                    String province_code = city.get("province_code").toString();
                    JSONArray citys = (JSONArray) city.get("citys");
                    for (int x = 0; x < citys.length(); x++) {
                        JSONObject o = (JSONObject) citys.get(x);
//                        String city_name = o.get("city_name").toString();
//                        String city_code = o.get("city_code").toString();
//                        String abbr = o.get("abbr").toString();
//                        String engine = o.get("engine").toString();
//                        String engineno = o.get("engineno").toString();
//                        String classa = o.get("classa").toString();
//                        String classno = o.get("classno").toString();
//                        String regist = o.get("regist").toString();
//                        String registno = o.get("registno").toString();
                       cityDetail.setProvince(province);
                        cityDetail.setProvince_code(province_code);
                        cityDetail.setCity_name(o.get("city_name").toString());
                        cityDetail.setCity_code(o.get("city_code").toString());
                        cityDetail.setAbbr(o.get("abbr").toString());
                        cityDetail.setEngine(o.get("engine").toString());
                        cityDetail.setEngineno(o.get("engineno").toString());
                        cityDetail.setClassa(o.get("classa").toString());
                        cityDetail.setClassno(o.get("classno").toString());
                        cityDetail.setRegist(o.get("regist").toString());
                        cityDetail.setRegistno(o.get("registno").toString());
                        carDB.saveCityDetail(cityDetail);

                    }
                }


            } else {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
