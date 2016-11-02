package app.taca.com.myapplication;

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

public class RegistActivity extends BaseActivity
{
    EditText uid, name, upw;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        uid     = (EditText)this.findViewById(R.id.uid);
        name    = (EditText)this.findViewById(R.id.name);
        upw     = (EditText)this.findViewById(R.id.upw);
    }
    // 사용자가 회원가입 버튼 누르면 호출
    public void onRegist( View view)
    {
        // 1. 입력값 체크
        String uidStr   = uid.getText().toString();
        String nameStr  = name.getText().toString();
        String upwStr   = upw.getText().toString();
        if( uidStr.length() == 0 || nameStr.length() == 0 || upwStr.length() == 0) {
            Toast.makeText(this, "입력값을 정확하게 넣어주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 통신
        showLoading();
        String url = E.NET.DOMAIN + E.API.API_VERSION;
        Gson gson  = new Gson();
        ReqRegistDto reqRegistDto   = new ReqRegistDto();
        UserDao userDao             = new UserDao();
        userDao.setUid(  uidStr  );
        userDao.setName( nameStr );
        userDao.setUpw(  upwStr  );
        userDao.setOs(    "a"     );
        userDao.setUuid(  "--1--" );
        userDao.setToken( "--2--" );
        userDao.setModel( "--3--" );
        userDao.setPhone( "01000000001" );
        reqRegistDto.setHead("RT");
        reqRegistDto.setBody(userDao);
        String jsonStr   = gson.toJson(reqRegistDto);
        JSONObject param = null;
        try{
            param        = new JSONObject(jsonStr);
        }catch(Exception e){
        }
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                url,
                param,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("NET", response.toString());
                        hideLoading();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideLoading();
                    }
                }
        );
        U.getInstance().getQueue(this).add(req);
    }
}
