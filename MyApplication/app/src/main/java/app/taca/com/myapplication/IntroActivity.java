package app.taca.com.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static java.lang.System.exit;

//
public class IntroActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        // 버전체크 !!
        // 동일 버전이면 그대로 서비스, 버전이 다르면 업데이트 창 띠운다 !!
        showLoading();
        onVersion();
    }
    public void onVersion()
    {
        // 1. url
        String url = E.NET.DOMAIN + E.API.API_VERSION;
        // 2. param
        // os:a
        // 3. 통신
        StringRequest req = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // 4. 통신후 처리
                        hideLoading();
                        //Log.i("NET", response);
                        Gson gson       = new Gson();
                        try {
                            // 성공 전문
                            final ResVersion res = gson.fromJson(response, ResVersion.class);
                            Log.i("NET", res.getMsg().getVersion());
                            // 앱 버전 획득
                            PackageInfo pi =
                                    IntroActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
                            // 버전이 일치하지 않으면!!
                            if( !res.getMsg().getVersion().equals(pi.versionName) ){
                                new SweetAlertDialog(IntroActivity.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("알림")
                                        .setContentText("업데이트가 존재합니다. 업데이트 하시겠습니까?")
                                        .setConfirmText("확인")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                                Intent i = new Intent(Intent.ACTION_VIEW);
                                                i.setData(Uri.parse(res.getMsg().getUrl()));
                                                startActivity(i);
                                            }
                                        })
                                        .setCancelText("취소")
                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                Toast.makeText(IntroActivity.this, "앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                                                exit(0);
                                            }
                                        })
                                        .show();
                                return;
                            }
                            Log.i("NET", "로그인");
                        }catch(Exception e){
                            // 실패 전문
                            ResError err = gson.fromJson(response, ResError.class);
                            Log.i("NET", err.getMsg());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 4. 통신후 처리
                        Log.i("NET", "[에러]:"+error.toString());
                        hideLoading();
                    }
                })
        {
            // POST일때만 파라미터 전달됨.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //return super.getParams();
                Map<String, String> param = new HashMap<String, String>();
                param.put("os", "a");
                return param;
            }
        };
        U.getInstance().getQueue(this).add(req);

    }


}


