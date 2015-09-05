package com.suntt.swipemenulistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import bean.CarDetail;
import bean.CityDetail;
import db.CarDB;


public class Car extends Activity {
    private String[] province = {"北京", "上海", "浙江", "福建", "吉林", "辽宁", "山东", "河南", "江苏", "陕西", "青海", "广东", "湖北", "黑龙江", "安徽", "云南", "山西", "海南", "贵州", "新疆", "甘肃", "宁夏", "西藏", "重庆", "广西"};
    private String[][] city = {
            {"北京"},
            {"上海"},
            {"杭州", "宁波", "义乌", "台州", "慈溪", "余姚", "永康", "绍兴县", "嘉兴", "金华", "绍兴", "温岭", "桐乡", "海宁", "诸暨", "玉环县", "上虞", "湖州", "丽水", "衢州", "舟山", "临海", "平湖", "长兴县"},
            {"厦门", "福州", "泉州", "晋江", "石狮", "莆田", "漳州", "南安", "三明", "龙岩", "南平", "宁德", "福清", "惠安县"},
            {"吉林", "长春", "四平", "通化", "白山", "辽源", "松原", "白城", "延边"},
            {"沈阳", "锦州", "营口", "葫芦岛", "大连", "鞍山", "抚顺", "丹东", "阜新", "辽阳", "铁岭", "盘锦", "瓦房店", "本溪", "海城"},
            {"淄博", "青岛", "威海", "枣庄", "日照", "临沂", "莱芜", "菏泽", "潍坊", "聊城", "济宁", "滨州", "德州", "东营", "泰安", "烟台", "济南", "滕州", "荣成", "莱州", "诸城", "蓬莱", "龙口", "寿光", "章丘", "胶州", "平度", "招远", "文登", "邹城", "兖州", "乳山", "邹平县", "新泰", "肥城", "胶南"},
            {"郑州", "洛阳", "许昌", "平顶山", "驻马店", "鹤壁", "焦作", "三门峡", "商丘", "济源", "新乡", "巩义", "安阳", "南阳", "开封", "濮阳", "周口", "信阳", "漯河"},
            {"南京", "徐州", "常州", "苏州", "南通", "连云港", "镇江", "扬州", "无锡", "张家港", "江阴", "宜兴", "昆山", "常熟", "江都", "丹阳", "太仓", "溧阳", "海门", "启东", "通州"},
            {"西安", "延安"},
            {"西宁", "海东", "海西", "海南", "玉树", "黄南", "海北", "果洛"},
            {"深圳", "广州", "东莞", "佛山", "中山", "汕头", "珠海", "江门", "揭阳", "惠州", "潮州", "肇庆", "阳江", "湛江", "韶关", "茂名", "清远", "梅州", "河源", "增城", "云浮", "汕尾"},
            {"湖北", "武汉", "宜昌", "荆州", "黄冈", "十堰", "黄石", "随州", "荆门", "孝感", "鄂州", "咸宁", "恩施", "神农架", "潜江", "天门", "仙桃", "襄阳"},
            {"哈尔滨", "大庆", "佳木斯", "牡丹江", "齐齐哈尔", "鸡西", "伊春", "黑河", "鹤岗", "绥化", "双鸭山", "七台河", "大兴安岭"},
            {"合肥", "芜湖", "阜阳", "黄山", "蚌埠", "安庆", "马鞍山", "亳州", "滁州", "铜陵", "淮南", "淮北", "六安", "巢湖", "宿州", "宣城", "池州"},
            {"昆明", "玉溪", "保山", "曲靖", "红河", "丽江", "昭通", "普洱", "临沧", "大理", "迪庆", "楚雄", "西双版纳", "文山", "德宏", "怒江"},
            {"太原", "大同", "运城", "长治", "临汾", "晋城", "阳泉", "忻州", "晋中", "朔州", "吕梁"},
            {"海口", "三亚", "陵水", "白沙", "琼海", "琼中", "澄迈县", "昌江", "文昌", "屯昌县", "定安县", "儋州", "保亭", "五指山", "乐东", "临高县", "东方", "万宁"},
            {"贵阳", "遵义", "毕节", "黔东南", "六盘水", "安顺", "铜仁", "黔南", "黔西南"},
            {"乌鲁木齐", "巴音郭楞", "伊犁", "克拉玛依", "阿克苏", "喀什", "哈密", "和田", "昌吉", "吐鲁番", "阿勒泰", "塔城", "博尔塔拉", "克孜勒苏", "石河子", "阿拉尔", "图木舒克", "五家渠"},
            {"兰州", "酒泉", "天水", "张掖", "白银", "庆阳", "嘉峪关", "武威", "平凉", "金昌", "甘南", "临夏", "陇南", "定西"},
            {"银川", "吴忠", "石嘴山", "固原", "中卫"},
            {"那曲"},
            {"重庆"},
            {"南宁"}

    };
    private String[] shortProvince = {"京", "沪", "浙", "闽", "吉", "辽", "鲁", "豫", "苏", "陕", "青", "粤", "鄂", "黑", "皖", "云", "晋", "琼", "贵", "新", "甘", "宁", "藏", "渝", "桂"};
    private String[] charCity =
            {"A", "B", "C", "D", "F", "L", "P", "M", "N", "S", "T", "J", "E", "Q", "G", "K", "H", "R", "U", "V", "W"};
    private String carSize[] = {"小型车"};
    private ArrayAdapter<String> provinceAdaper;
    private ArrayAdapter<String> cityAdaper;
    private ArrayAdapter<String> shortProvinceAdaper;
    private ArrayAdapter<String> charCityAdaper;
    private ArrayAdapter<String> carSizaAdaper;
    private Spinner provinceSp = null;
    private Spinner citySp = null;
    private Spinner shortProvinceSp = null;
    private Spinner charCitySp = null;
    private Spinner carSizeSp = null;
    private String selectProvince;
    private Button btn;
    private EditText etNum;
    private EditText bodyNum;
    private TextView tv;
    private int provincePosition;
    private String selectCity;
    private String selectShortProvince;
    private String selectCharCity;
    private CarDB carDB;
    private EditText engineNum;
    private TextView tvbodyNum;
    private TextView tvengineNum;
    private String engine;
    private String engineno;
    private String classa;
    private String classno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car);
        carDB = CarDB.getInstance(this);

        setSpinner();
        engineNum.setVisibility(View.VISIBLE);
        bodyNum.setVisibility(View.VISIBLE);
        tvengineNum.setVisibility(View.VISIBLE);
        tvbodyNum.setVisibility(View.VISIBLE);
    }

    public void setSpinner() {
        provinceSp = (Spinner) findViewById(R.id.spProvince);
        citySp = (Spinner) findViewById(R.id.spCity);
        shortProvinceSp = (Spinner) findViewById(R.id.shortProvince);
        charCitySp = (Spinner) findViewById(R.id.charCity);
        carSizeSp = (Spinner) findViewById(R.id.carsize);
        btn = (Button) findViewById(R.id.btn);
        etNum = (EditText) findViewById(R.id.etNum);
        bodyNum = (EditText) findViewById(R.id.bodyNum);
        tvbodyNum = (TextView) findViewById(R.id.tvbodyNum);
        tvengineNum = (TextView) findViewById(R.id.tvengineNum);
        engineNum = (EditText) findViewById(R.id.engineNum);


        provinceAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, province);
        provinceSp.setAdapter(provinceAdaper);
//        provinceSp.setSelection(1, true);
        cityAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, city[1]);
        citySp.setAdapter(cityAdaper);
//        citySp.setSelection(0, true);
        shortProvinceAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, shortProvince);
        shortProvinceSp.setAdapter(shortProvinceAdaper);
//        shortProvinceSp.setSelection(0, true);
        charCityAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, charCity);
        charCitySp.setAdapter(charCityAdaper);
//        charCitySp.setSelection(0, true);
        carSizaAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, carSize);
        carSizeSp.setSelection(0, true);
        carSizeSp.setAdapter(carSizaAdaper);


        provinceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityAdaper = new ArrayAdapter<String>(Car.this, android.R.layout.simple_spinner_item, city[position]);
                citySp.setAdapter(cityAdaper);
                shortProvinceSp.setSelection(position, true);


                selectProvince = province[position];
                provincePosition = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        citySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCity = city[provincePosition][position];
                CityDetail cityDetail2 = new CityDetail();
                cityDetail2.setProvince(selectProvince);
                cityDetail2.setCity_name(selectCity);
                CityDetail dbcitydetail = carDB.queryEngineNum(cityDetail2);
                 engine = dbcitydetail.getEngine();
                 engineno = dbcitydetail.getEngineno();
                 classa = dbcitydetail.getClassa();
                 classno = dbcitydetail.getClassno();
                if (engine.equals("0")) {
                    engineNum.setVisibility(View.GONE);
                    tvengineNum.setVisibility(View.GONE);
                } else {
                    if (engineno.equals("0")) {
                        engineNum.setVisibility(View.VISIBLE);
                        tvengineNum.setVisibility(View.VISIBLE);
                        engineNum.setHint("请输入发动机号");
                    } else {
                        engineNum.setVisibility(View.VISIBLE);
                        tvengineNum.setVisibility(View.VISIBLE);
                        engineNum.setHint("请输入发动机后" + engineno + "位");
                    }
                }
                if (classa.equals("0")) {
                    bodyNum.setVisibility(View.GONE);
                    tvbodyNum.setVisibility(View.GONE);
                } else {
                    if (classno.equals("0")) {
                        bodyNum.setVisibility(View.VISIBLE);
                        tvbodyNum.setVisibility(View.VISIBLE);
                        bodyNum.setHint("请输入车架号");
                    } else {
                        bodyNum.setVisibility(View.VISIBLE);
                        tvbodyNum.setVisibility(View.VISIBLE);
                        bodyNum.setHint("请输入车架号后" + classno + "位");
                    }
                }
                System.out.print("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + selectCity + ">>>>>>>>>>>>>>>>>>>>>>>>>");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shortProvinceSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectShortProvince = shortProvince[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//selectShortProvince = "鲁";

            }
        });

        charCitySp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectCharCity = charCity[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//selectCharCity = "A";
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarDetail car = new CarDetail();
                car.setProvince(selectProvince);
                car.setCity(selectCity);
                car.setShortProvince(selectShortProvince);
                car.setCharCity(selectCharCity);
                car.setCarNumberString(etNum.getText().toString());
                car.setEnginenumber(engineNum.getText().toString());
                carDB.saveCarDetail(car);
                String hphm = new StringBuffer(selectShortProvince).append(selectCharCity).append(etNum.getText().toString()).toString();
if (etNum.getText().length()==0){
    Toast.makeText(Car.this,"请输入车牌号",Toast.LENGTH_SHORT).show();

}else{

    Intent i = new Intent(Car.this, CarReason.class);
    i.putExtra("hphm", hphm);
    Intent reason = new Intent();
    Bundle bundle = new Bundle();
    bundle.putSerializable("car", car);
    reason.putExtra("bundle", bundle);
    i.putExtra("bundle", bundle);
    setResult(2, reason);
    startActivity(i);
    Car.this.finish();
}

//                i.putExtra("selectProvince", selectProvince);
//                i.putExtra("selectCity", selectCity);
//                i.putExtra("selectShortProvince", selectShortProvince);
//                i.putExtra("selectCharCity", selectCharCity);
//                i.putExtra("etNum", etNum.getText().toString());
//                i.putExtra("bodyNum", bodyNum.getText().toString());
//
//
//                startActivity(i);
            }
        });

    }


}
