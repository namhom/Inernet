package th.ac.kmutnb.inernet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {
//    private static final String TAG = "my_app";
//    TextView tv;
//    ProgressDialog pDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        WebView wv = findViewById(R.id.webView);
//
////        String urlStr = "http://www.bbc.com";
//        String url = "http://cs.kmutnb.ac.th";
//
//        wv.setWebViewClient(new WebViewClient());
//        wv.getSettings().setJavaScriptEnabled(true);
//        //wv.getSettings().setDefaultFontSize(40);
//        wv.getSettings().setBuiltInZoomControls(true);
//        wv.loadUrl(url);
//
//        TextView tv = new TextView(this);
//        //setContentView(tv); // ใช้ Layout บรรทัดนี้แทน
//        tv.setTextSize(20);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) { // response
//                        // ผลจาก Response แสดงข้อความเว็บที่ 600อักษร บนหน้าจอ
//                        tv.setText(response.substring(0,600)); // เอ้าพุทที่ TextView
//                    }
//                },
//                new Response.ErrorListener() { // กรณี Error
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        tv.setText("onErrorResponse(): "+ error.getMessage());
//                    }
//                });
//        // สร้างอินสแตนซ์ queue แล้วส่ง Request ไปเข้าคิวเพื่อรัน
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest); // ส่ง Request ไปเข้าคิวเพื่อรัน
//
//        String url2 = "http://cs.kmutnb.ac.th/IMG_SHOW/img/temp/98/0.png";
//        ImageView imv = findViewById(R.id.imageView);
//        Picasso.get()
//                .load(url2)
////                .placeholder(R.drawable.user_placeholder)
////                .error(R.drawable.error)
//                .into(imv);
//
//        tv = new TextView(this);
//        setContentView(tv);
//        tv.setTextSize(20);
//
//        String url = "http://itpart.com/android/json/test1data.php";
        //String url = "http://192.168.43.100";   // for localhost

        // Request a Json response
//        JsonObjectRequest jsObjReq = new JsonObjectRequest(Request.Method.GET,url,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {  // if OK
//                        String title, desc;
//                        int id;
//                        try {
//                            title = response.getString("title");
//                            desc = response.getString("description");
//                            id = response.getInt("id");
//                            tv.setText(id + "\n" + title + "\n" + desc );
//                        } catch (JSONException e) {
//                            Log.d(TAG,e.getMessage());
//                            e.printStackTrace();
//                        }
//                        Log.i(TAG, response.toString());
//
//                        //pDialog.hide();
//                    }
//                },
//                new Response.ErrorListener() {  // if Error
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Error handling
//                        //pDialog.hide();
//                        tv.setText("onErrorResponse(): "+ error.getMessage());
//                    }
//                });
//
//        // Add the request to the queue
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsObjReq);

        // String url = "http://web.com/json/test.php";
//        String url = "http://itpart.com/android/json/test8records.php";
//
//        tv = new TextView(this);
//        setContentView(tv);
//
//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading..");
//        pDialog.show();
//
//        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        tv.setText(response.toString());
//
//                        JSONObject jsObj;
//                        for (int i=0; i < response.length(); i++ ) {
//                            try {
//                                jsObj = response.getJSONObject(i);
//                                int id = jsObj.getInt("id");
//                                String title = jsObj.getString("title");
//                                String desc = jsObj.getString("description");
//                                String img = jsObj.getString("img");
//                                Log.d(TAG,id + " , " + title + " , " + desc + " , " + img);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        pDialog.hide();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Error handling
//                        tv.setText("onErrorResponse(): "+
//                                error.getMessage());
//                        pDialog.hide();
//                    }
//                });  // stringRequest
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsRequest);
////////////////////////////////////////////////////////////////////////////////////////

    private static final String TAG = "my_app";
    public static final String REQUEST_TAG = "myrequest";
    private RequestQueue mQueue;
    ProgressDialog pDialog;
    private List<DataModel> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        datas.add(new DataModel(1, "แผงวงจรควบคุมรีโมท", "แผงวงจรแบบไร้สายระยะไกล", "pic6.jpg"));
//        datas.add(new DataModel(2, "เครื่องกรองน้ำดื่ม", "เครื่องกรองน้ำดื่ม 50 L/hr", "pic6.jpg"));
//        datas.add(new DataModel(3, "เครื่องกรองน้ำ RO", "เครื่องกรองน้ำดื่มแบบ RO 3.5 L/hr", "pic6.jpg"));
//        datas.add(new DataModel(4, "เครื่องสำรองไฟ 500W", "อุปกรณ์สำรองไฟขนาด 500 W", "pic6.jpg"));

//        String url = "http://web.com/android/json/browse.php";
        String url = "http://itpart.com/android/json/test8records.php";

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading..");
        pDialog.show();

        JsonArrayRequest jsRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Gson gson = new Gson();

                        JSONObject jsObj;   // = null;
                        for (int i=0; i < response.length(); i++ ) {
                            try {
                                jsObj = response.getJSONObject(i);
                                String title = jsObj.getString("title");
                                Log.d(TAG, title);

                                DataModel dataitem = gson.fromJson(String.valueOf(jsObj), DataModel.class);
                                datas.add(dataitem);
                                Log.d(TAG,"gson "+ dataitem.getTitle());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (datas.size() > 0){
                            displayListview();
                        }

                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,error.toString());
                        Toast.makeText(getBaseContext(),error.toString(), Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                });  // Request

        mQueue = Volley.newRequestQueue(this);
        jsRequest.setTag(REQUEST_TAG);
        mQueue.add(jsRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    public void displayListview(){
        MyAdapter adapter = new MyAdapter(this,datas);
        ListView lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

    // for json listview
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, String.valueOf(i));
        Intent itn = new Intent(this, DetailpageActivity.class);
        itn.putExtra("recID", i);
        startActivity(itn);
    }


}
