package thread;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.suntt.swipemenulistview.GetJsonToProvince;
import com.suntt.swipemenulistview.HttpUtil;

/**
 * Created by Administrator on 2015/8/14.
 */
public class ProvinceTask extends AsyncTask<Context,Void,String> {
    private Context mContext;
    private String response ;
    private GetJsonToProvince getJsonToProvince = new GetJsonToProvince();
    private HttpUtil httpUtil = new HttpUtil();
    @Override
    protected String doInBackground(Context... params) {
        response = httpUtil.sendHttpRequest("http://v.juhe.cn/wz/citys?key=346955208f255276119effc3d3ec96cf");
//        new GetJsonToProvince(params[0], HttpUtil.sendHttpRequest("http://v.juhe.cn/wz/citys?key=346955208f255276119effc3d3ec96cf"));
//        getJsonToProvince.JsonToProvince(params[0], response);

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        if(TextUtils.isEmpty(s)){
            Toast.makeText(mContext,"meiyoushuju",Toast.LENGTH_LONG).show();
        }else {
            getJsonToProvince.JsonToProvince(mContext, s);
        }
        super.onPostExecute(s);
    }
}
