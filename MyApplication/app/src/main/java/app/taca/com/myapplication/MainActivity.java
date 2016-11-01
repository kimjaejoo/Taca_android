package app.taca.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면 전환
        Intent i = new Intent( this, IntroActivity.class);
        startActivity(i);
        // 현재 화면 종료
        finish();
    }
    // 버튼을 누르면 통신 테스트 진행
    public void onSend(View view)
    {
        // 1. domain : 로컬서버=>10.0.2.2 외부서버:ec2-52-78-222-172.ap-northeast-2.compute.amazonaws.com
        String url      = E.NET.DOMAIN + E.API.API_MAIN;
        String param    = "";
        // 2. 통신 모듈 세팅
        RequestQueue queue = U.getInstance().getQueue( this );
        // 3. 요청 등록
        queue.add( new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.i("NET", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

        });
    }
}









