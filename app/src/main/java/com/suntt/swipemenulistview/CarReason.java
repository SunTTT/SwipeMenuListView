package com.suntt.swipemenulistview;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bean.CarAccidentDetail;
import bean.CarDetail;
import bean.CityDetail;
import db.CarDB;


public class CarReason extends Activity {
    private List<CarAccidentDetail> list = null;
    private reasonAdapter adapter;
    private CarDB carDB;
    private String hphm;
    private ListView listView;
    private CarDetail carDetail;
    private String engineNum;
    private String address;
    private CityDetail cityDetail;
    private String city_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_reason);
        Intent i = getIntent();
        hphm = i.getStringExtra("hphm");
        carDetail = (CarDetail) i.getExtras().getBundle("bundle").getSerializable("car");
        engineNum = carDetail.getEnginenumber();

        listView = (ListView) findViewById(R.id.caraccidentlistview);

        carDB = CarDB.getInstance(this);
        cityDetail = carDB.queryCity(carDetail.getCity());
        city_code = cityDetail.getCity_code();
        address = new StringBuffer("http://v.juhe.cn/wz/query?city=").append(city_code).append("&hphm=").append(hphm).append("&engineno=").append(engineNum).append("&key=").append("346955208f255276119effc3d3ec96cf").toString();
        AccidentTask task = new AccidentTask();
        task.execute();
        if (carDB.queryAccident(hphm).size() > 0) {
            list = carDB.queryAccident(hphm);
        } else {

            list = new ArrayList<CarAccidentDetail>();
        }
        listView.setAdapter(adapter);
        View emptyView = findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);

    }

    class reasonAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ReasonViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.car_reason_item, null);
                holder = new ReasonViewHolder();
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.area = (TextView) convertView.findViewById(R.id.area);
                holder.act = (TextView) convertView.findViewById(R.id.act);
                holder.fen = (TextView) convertView.findViewById(R.id.fen);
                holder.money = (TextView) convertView.findViewById(R.id.money);
                holder.handled = (TextView) convertView.findViewById(R.id.handled);
                convertView.setTag(holder);
            } else {
                holder = (ReasonViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ReasonViewHolder {
            TextView date;
            TextView area;
            TextView act;
            TextView fen;
            TextView money;
            TextView handled;
        }
    }
    public class AccidentTask extends AsyncTask<Void,Void,String>{

        private String result;

        @Override
        protected String doInBackground(Void... params) {

            if(!TextUtils.isEmpty(address)) {
                result = HttpUtil.sendHttpRequest(address);
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            GetJsonToAccident getJsonToAccident = new GetJsonToAccident();
            getJsonToAccident.JsonToAccident(CarReason.this,s);
            super.onPostExecute(s);
        }
    }
}
