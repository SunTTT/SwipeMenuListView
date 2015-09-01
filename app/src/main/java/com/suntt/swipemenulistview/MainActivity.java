package com.suntt.swipemenulistview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.getbase.floatingactionbutton.AddFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import bean.CarDetail;
import db.CarDB;
import thread.ProvinceTask;


public class MainActivity extends Activity {
    //    private List<String> list;
    private List<CarDetail> list = null;
    private SwipeMenuListView mListView;
    private mAdapter adapter;
    private CarDB carDB;
    //    private String[] city = {"adsfsda", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh", "asdfasdff", "ajksdfh"};
    private AddFloatingActionButton addFloatingActionButton;
    private final static String CITY_URL = "http://v.juhe.cn/wz/citys?key=346955208f255276119effc3d3ec96cf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carDB = CarDB.getInstance(this);
        if (carDB.loadCityDetailBoolean()) {
            System.out.print("123456");
        } else {
            new ProvinceTask().execute(this);
            Log.v("1223","____________________________________________________");
        }

        mListView = (SwipeMenuListView) findViewById(R.id.listView);
        View emptyView = findViewById(android.R.id.empty);
        mListView.setEmptyView(emptyView);

//        CarDetail car1 = new CarDetail();
//        car1.setCarNumberString("sdfasd");
//        car1.setShortProvince("sdfs");
//        car1.setCarNumberString("sdfas");
//        list.add(car1);
//        list = new ArrayList<String>();
//        for (int i = 0; i < city.length; i++) {
//            list.add(city[i]);
//        }
        addFloatingActionButton = (AddFloatingActionButton) findViewById(R.id.normal_plus);
        if (carDB.loadCarDetail() != null) {
            list = carDB.loadCarDetail();
//
//            adapter.notifyDataSetChanged();
        } else {
            list = new ArrayList<CarDetail>();
//
        }
        adapter = new mAdapter();
        mListView.setAdapter(adapter);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Car.class);
                startActivityForResult(i, 1);
//                list.add("122455");
//                adapter.notifyDataSetChanged();
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu swipeMenu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xf9, 0x3f, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.abc_ic_menu_selectall_mtrl_alpha);
                swipeMenu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu swipeMenu, int index) {
                switch (index) {
                    case 0:
                        CarDetail car = new CarDetail();
                        car = list.get(position);
                        carDB.removelist(car.getShortProvince(), car.getCharCity(), car.getCarNumberString());
//                        list = new ArrayList<CarDetail>();
                        list = carDB.loadCarDetail();
                        adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, CarReason.class);
                CarDetail car = new CarDetail();CarDetail car1 = new CarDetail();
                car = list.get(position);
                car1 = carDB.queryCarDetail(car.getShortProvince(),car.getCharCity(),car.getCarNumberString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("car", car1);
                String hphm = new StringBuffer(car.getShortProvince()).append(car.getCharCity()).append(car.getCarNumberString()).toString();
                i.putExtra("hphm", hphm);
                i.putExtra("bundle",bundle);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {

            adapter.notifyDataSetChanged();
        } else {
            CarDetail car = (CarDetail) data.getExtras().getBundle("bundle").getSerializable("car");
            list.add(car);
            adapter.notifyDataSetChanged();
//        super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    class mAdapter extends BaseAdapter {

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
            CarDetail car = (CarDetail) getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, null);

                holder = new ViewHolder();
                holder.shortProvince = (TextView) convertView.findViewById(R.id.shortProvince);
                holder.charProvince = (TextView) convertView.findViewById(R.id.charProvince);
                holder.carNumberString = (TextView) convertView.findViewById(R.id.carNumberString);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }
            holder.shortProvince.setText(car.getShortProvince());
            holder.charProvince.setText(car.getCharCity());
            holder.carNumberString.setText(car.getCarNumberString());


            return convertView;
        }

        class ViewHolder {
            TextView shortProvince;
            TextView charProvince;
            TextView carNumberString;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
