package com.d4us.memoline_rb;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by BlueBear on 3/22/2017.
 */

public class MainActivity extends FragmentActivity {

    int no=0;
    EditActivity editActivity;
    Button bt_goedit;
    Button bt_gochart;
    Button bt_gocheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callkeyno();
        callview();

        //Button bt_check = (Button) findViewById(R.id.bt_check);
        ImageView iv_goedit = (ImageView) findViewById(R.id.iv_goedit);
        ImageView iv_golist = (ImageView) findViewById(R.id.iv_golist);
        ImageView title_1 = (ImageView) findViewById(R.id.title_1);
        ImageView title_2 = (ImageView) findViewById(R.id.title_2);
        ImageView title_3 = (ImageView) findViewById(R.id.title_3);
        ImageView title_4 = (ImageView) findViewById(R.id.title_4);
        ImageView title_5 = (ImageView) findViewById(R.id.title_5);

        findViewById(R.id.bt_golist).setOnClickListener(mClickListener);
        findViewById(R.id.bt_goedit).setOnClickListener(mClickListener);
        findViewById(R.id.bt_check).setOnClickListener(mClickListener);
        findViewById(R.id.bt_goset).setOnClickListener(mClickListener);


        title_1.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.post_title_setting)));
        title_2.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.post_title_leftlane)));
        title_3.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.post_title_title)));
        title_4.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.post_title_leftlane)));



        callfragment();


    }

    // 버튼 클릭으로 프래그먼트 변경
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            Fragment fr = null;
            switch (v.getId()) {
                case R.id.bt_goedit:
                    //  isedit = 1;
                    fr = new EditActivity();
                    try {
                        //      bt_goedit.setSelected(true);
                        //      bt_gochart.setSelected(false);
                    } catch (NullPointerException ex) {

                    }

                    gofrag(fr);


                    break;
                case R.id.bt_golist:
                    //  isedit = 0;
                    fr = new ChartActivity();
                    try {
                        //      bt_goedit.setSelected(false);
                        //      bt_gochart.setSelected(true);
                    } catch (NullPointerException ex) {
                    }
                    gofrag(fr);

                    break;
                case R.id.bt_check:

                 /*
                 Log.d("check", "체크 실행");
                 EditActivity ttm = (EditActivity) getFragmentManager().findFragmentById(R.id.fragment_base);
                    ttm.noti(no);
                    Log.d("text", text.getText().toString());
                    no++;
                Log.d("text",iv.getBackground().toString());
                */

                    break;
                case R.id.bt_goset:
                    Toast.makeText(getApplicationContext(),"고셋",Toast.LENGTH_LONG).show();
                    break;
            }


        }
    };



    @Override
    protected void onPause() {
        super.onPause();
        SaveScore();
    }
    @Override
    protected void onResume() {
        LoadScore();
        super.onResume();
    }
//no값 저장
    private void SaveScore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("score",no);
        editor.commit();
    }
//no값 불러오기
    private void LoadScore(){
        SharedPreferences pref = getSharedPreferences("gostop", Activity.MODE_PRIVATE);
        no = pref.getInt("score",0);
        Toast.makeText(getApplicationContext(),"Load Score : "+no,Toast.LENGTH_LONG).show();
    }



//키보드 자동띄우기, no값 처음선언
    public void callkeyno(){

        // 실행시 가상키보드 뜨게하기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 이거 커서 조절하는건가?
//        imm.hideSoftInputFromWindow(text.getWindowToken(), 0);


        // 최초 실행시 no값 주기
        if (no==(0))
        {
            no = 1;
        }
    }

//id를 선언하는 함수
    public void callview(){


}

// 프래그먼트 처음에 나오기
    public void callfragment(){



        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        editActivity = new EditActivity();
        fragmentTransaction.add(R.id.fragment_base, editActivity);
        fragmentTransaction.commit();

           }



    public void gofrag(Fragment fr){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_base, fr);
        fragmentTransaction.commit();
    }
}
