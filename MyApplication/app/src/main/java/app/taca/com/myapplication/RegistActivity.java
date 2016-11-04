package app.taca.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

public class RegistActivity extends BaseActivity {
    EditText uid, name, upw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 회원 정보가 저장되어 있으면 자동 로그인
        if( !U.getInstance().getStoreString(this, "uid").equals("") &&
                !U.getInstance().getStoreString(this, "upw").equals("") ){
            onLogin(U.getInstance().getStoreString(this, "uid"), U.getInstance().getStoreString(this, "upw"));
        }

        setContentView(R.layout.activity_regist);
        uid = (EditText) this.findViewById(R.id.uid);
        name = (EditText) this.findViewById(R.id.name);
        upw = (EditText) this.findViewById(R.id.upw);
    }

    // 사용자가 회원가입 버튼 누르면 호출
    public void onRegist(View view) {
        // 1. 입력값 체크
        String uidStr = uid.getText().toString();
        String nameStr = name.getText().toString();
        String upwStr = upw.getText().toString();
        if (uidStr.length() == 0 || nameStr.length() == 0 || upwStr.length() == 0) {
            Toast.makeText(this, "입력값을 정확하게 넣어주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 통신
        showLoading();
        String url = E.NET.DOMAIN + E.API.API_REGIST;
        final Gson gson = new Gson();
        ReqRegistDto reqRegistDto = new ReqRegistDto();
        UserDao userDao = new UserDao();
        userDao.setUid(uidStr);
        userDao.setName(nameStr);
        userDao.setUpw(upwStr);
        userDao.setOs("a");
        userDao.setUuid("--1--");
        userDao.setToken("--2--");
        userDao.setModel("--3--");
        userDao.setPhone("01000000001");
        reqRegistDto.setHead("RT");
        reqRegistDto.setBody(userDao);
        String jsonStr = gson.toJson(reqRegistDto);
        JSONObject param = null;
        try {
            param = new JSONObject(jsonStr);
        } catch (Exception e) {
        }
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                param,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("NET", response.toString());
                        hideLoading();
                        // 응답 전문 확인
                        ResRegistDto res = gson.fromJson(response.toString(), ResRegistDto.class);
                        // 성공이면 -> 회원 정보 저장(일부)
                        if (res.getCode() == 1) {
                            // 로그인 수행 -> 성공이면 저장 -> 메인 서비스로 이동
                            onLogin(res.getData().getUid(), res.getData().getUpw());
                        } else {
                            Toast.makeText(RegistActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideLoading();
                    }
                }
        );
        U.getInstance().getQueue(this).add(req);
    }
    // 로그인 처리
    public void onLogin(final String uid, final String upw)
    {
        String url                  = E.NET.DOMAIN + E.API.API_LOGIN;

        final Gson gson             = new Gson();
        ReqRegistDto reqRegistDto   = new ReqRegistDto();
        UserDao userDao             = new UserDao();
        userDao.setUid(uid);
        userDao.setUpw(upw);
        reqRegistDto.setBody(userDao);
        reqRegistDto.setHead("LG");
        String jsonStr              = gson.toJson(reqRegistDto);
        JSONObject param            = null;
        try {
            param                   = new JSONObject(jsonStr);
        } catch (Exception e) {
        }
        showLoading();
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                param,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("NET", response.toString());
                        hideLoading();
                        ResRegistDto res = gson.fromJson(response.toString(), ResRegistDto.class);
                        if (res.getCode() == 1) {
                            Toast.makeText(RegistActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            // 로그인 정보 저장
                            U.getInstance().setStoreString(RegistActivity.this, "uid", uid);
                            U.getInstance().setStoreString(RegistActivity.this, "upw", upw);

                            Intent i = new Intent(RegistActivity.this, ServiceActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegistActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideLoading();
                    }
                }
        );
        U.getInstance().getQueue(this).add(req);
    }
}

