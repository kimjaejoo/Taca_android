package app.taca.com.myapplication;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

// 모든 앱 공통 요소 적용
public class BaseActivity extends AppCompatActivity
{
    // 로딩
    private ProgressDialog pd;
    public void showLoading()
    {
        if( pd == null ){
            pd = new ProgressDialog(this);
            pd.setCancelable(false);        // 취소를 못하게 처리
            pd.setMessage("..로딩..");
        }
        pd.show();
    }
    public void hideLoading()
    {
        if( pd != null && pd.isShowing() )
            pd.dismiss();
    }
}

