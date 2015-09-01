package com.suntt.swipemenulistview;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bean.CarAccident;
import bean.CarAccidentDetail;
import db.CarDB;

/**
 * Created by Administrator on 2015/8/14.
 */
public class GetJsonToAccident {
    private CarDB carDB;
//    private CarAccident carAccident;

    public void JsonToAccident(Context context, String response) {
        carDB = CarDB.getInstance(context);
        CarAccident carAccident = new CarAccident();
//        List<CarAccidentDetail> list = new ArrayList<CarAccidentDetail>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);

            String resultcode = jsonObject.get("resultcode").toString();
            String reason = jsonObject.get("reason").toString();
            if (resultcode.equals("200")) {
                JSONObject result = (JSONObject) jsonObject.get("result");
                String province = (String) result.get("province");
                String city = (String) result.get("city");
                String hphm = (String) result.get("hphm");
                String hpzl = (String) result.get("hpzl");
                JSONArray lists = result.getJSONArray("lists");
                for (int i = 0; i < lists.length(); i++) {
                    JSONObject o = (JSONObject) lists.get(i);
                    List<CarAccidentDetail> list = new ArrayList<CarAccidentDetail>();
                    CarAccidentDetail carAccidentDetail = new CarAccidentDetail();
                    String date = (String) o.get("date");
                    String area = (String) o.get("area");
                    String act = (String) o.get("act");
                    String fen = (String) o.get("fen");
                    String money = (String) o.get("money");
                    String handled = (String) o.get("handled");
                    carAccident.setProvince(province);
                    carAccident.setCity(city);
                    carAccident.setHphm(hphm);
                    carAccident.setHpzl(hpzl);
                    carAccidentDetail.setData(date);
                    carAccidentDetail.setArea(area);
                    carAccidentDetail.setAct(act);
                    carAccidentDetail.setFen(fen);
                    carAccidentDetail.setMoney(money);
                    carAccidentDetail.setHandled(handled);
                    list.add(carAccidentDetail);

                    carAccident.setList(list);
                    carDB.saveCarAccident(carAccident);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
