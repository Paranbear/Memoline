package com.d4us.memoline_rb;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.io.ByteArrayOutputStream;

/**
 * Created by BlueBear on 3/27/2017.
 */

public class EditActivity extends Fragment {

    //변수 정의
    public DisplayMetrics metrics;
    // 뷰의 높이설정을 위한 변수선언
    Display display ;
    final static int asX = 1440;
    final static int asY = 252;
    DatabaseActivity manager;

    // 배경 값
    Bitmap dr_1;
    Bitmap dr_2;
    Bitmap dr_trans;
    Bitmap dr_4;
    Bitmap dr_5;
    Bitmap dr_6;
    Bitmap dr_7;
    Bitmap dr_8;
    Bitmap dr_9;
    Bitmap dr_10;

    ImageView iv_bg;

    SeekBar bar_font;
    SeekBar bar_color;

    static int a = 125;
    static int r = 255;
    static int g = 0;
    static int b = 0;
    static float[] hsv = new float[3];
    int color;
    int font_color;



    // 갤러리에서 배경 가져오기 위한 변수선언
    private static final int PICK_FROM_GALLERY = 1;

    // 컬러배경 설정을 위한 변수 선언 미완

    Canvas canvas = new Canvas();
    Paint paint = new Paint();

    // DB에 들어갈 변수들
    Bitmap btbg;
    Bitmap draw = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
    String fc= String.format("#%06X", (0xFFFFFF & font_color));
    EditText edittext;
    byte[] bg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_edit, container, false);

// db 생성
        manager = new DatabaseActivity(getActivity());

        RelativeLayout container2 = (RelativeLayout) view.findViewById(R.id.container2);
        ImageButton bt_bg_trans = (ImageButton) view.findViewById(R.id.bt_bg_trans);
        ImageButton bt_bg_add = (ImageButton) view.findViewById(R.id.bt_bg_add);
        ImageButton bt_bg_1 = (ImageButton) view.findViewById(R.id.bt_bg_1);
        ImageButton bt_bg_2 = (ImageButton) view.findViewById(R.id.bt_bg_2);
        ImageButton bt_bg_3 = (ImageButton) view.findViewById(R.id.bt_bg_3);
        ImageButton bt_bg_4 = (ImageButton) view.findViewById(R.id.bt_bg_4);
        ImageButton bt_bg_5 = (ImageButton) view.findViewById(R.id.bt_bg_5);
        ImageButton bt_bg_6 = (ImageButton) view.findViewById(R.id.bt_bg_6);
        ImageButton bt_bg_7 = (ImageButton) view.findViewById(R.id.bt_bg_7);
        SeekBar bar_color = (SeekBar) view.findViewById(R.id.bar_color); //원래 oncreate 위에 먼저 선언했었음
        SeekBar bar_font = (SeekBar) view.findViewById(R.id.bar_font); //원래 oncreate 위에 먼저 선언했었음

        color = Color.argb(a, r, g, b);

        iv_bg = (ImageView) view.findViewById(R.id.iv_bg);

        edittext = (EditText) view.findViewById(R.id.editText);

        // 변수에 배경 저장
        callbg();

        //뷰 배경 설정
       // bar_color.setBackgroundResource(dr_bar);
        bt_bg_trans.setBackground(new BitmapDrawable(dr_1));
        bt_bg_add.setBackground(new BitmapDrawable(dr_2));
        bt_bg_1.setBackground(new BitmapDrawable(dr_4));
        bt_bg_2.setBackground(new BitmapDrawable(dr_5));
        bt_bg_3.setBackground(new BitmapDrawable(dr_6));
        bt_bg_4.setBackground(new BitmapDrawable(dr_7));
        bt_bg_5.setBackground(new BitmapDrawable(dr_8));
        bt_bg_6.setBackground(new BitmapDrawable(dr_9));
        bt_bg_7.setBackground(new BitmapDrawable(dr_10));

        btbg = dr_trans;

        // TODO : Notification 크기가 dimen.xml에 있다고 한다. 그것을 찾아서 수정할 것
        // layout_notificatiopn 높이,너비 설정
        display = getActivity().getWindowManager().getDefaultDisplay();
        int notiWidth = display.getWidth();
        int notiheight = display.getHeight()/10;
        LinearLayout.LayoutParams r_p = new LinearLayout.LayoutParams(notiWidth, notiheight);
        container2.setLayoutParams(r_p);


        // 배경버튼 높이 지정
        metrics = this.getResources().getDisplayMetrics();
        bt_bg_trans.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_add.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_1.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_2.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_3.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_4.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_5.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_6.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bt_bg_7.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));
        bar_color.setLayoutParams(new LinearLayout.LayoutParams(notiWidth, notiheight));

        bt_bg_trans.setOnClickListener(onClickListener);
        bt_bg_add.setOnClickListener(onClickListener);
        bt_bg_1.setOnClickListener(onClickListener);
        bt_bg_2.setOnClickListener(onClickListener);
        bt_bg_3.setOnClickListener(onClickListener);
        bt_bg_4.setOnClickListener(onClickListener);
        bt_bg_5.setOnClickListener(onClickListener);
        bt_bg_6.setOnClickListener(onClickListener);
        bt_bg_7.setOnClickListener(onClickListener);

        bar_font.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int font = seekBar.getProgress() * 25 ;
                font_color = Color.rgb(255 - font, 255 - font, 255 - font);
                edittext.setTextColor(font_color);
                fc = String.format("#%06X", (0xFFFFFF & font_color));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bar_color.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Color.RGBToHSV(r, g, b, hsv);
                hsv[0] = progress;
                color = Color.HSVToColor(hsv);
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);
                try {
                    iv_bg.setBackgroundColor(color);
                    draw.eraseColor(color);
                } catch (NullPointerException nep) {
                }

                bg = getByteArrayFromBitmap(draw);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    // onClickListener 정의 -> bgOnClickListener을 실행한다.
    private View.OnClickListener onClickListener;
    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// 클릭했을때 아이디를 받아와서 int에 넣음
                int dr = v.getId();
                iv_bg.setBackground(null);
                // 버튼 클릭에 따른 switch문 시작
                switch (v.getId()) {
                    // 사진추가 골랐을 경우
                    case R.id.bt_bg_trans:
                        btbg = dr_trans;
                        // Toast.makeText(getActivity().getApplicationContext(), "투명배경이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_add:
                        // Gallery 호출

                        Intent intentg = new Intent(Intent.ACTION_PICK);
                        intentg.setType
                                (android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                        // 잘라내기 셋팅
                        intentg.putExtra("crop", "true");
                        intentg.putExtra("scale", true);
                        intentg.putExtra("aspectX", asX);
                        intentg.putExtra("aspectY", asY);
                        try {
                            intentg.putExtra("return-data", true);
                            startActivityForResult(Intent.createChooser(intentg,
                                    "Complete action using"), PICK_FROM_GALLERY);
                        } catch (ActivityNotFoundException e) {
                            // Do nothing for now
                        }
                        //  Toast.makeText(getActivity().getApplicationContext(), "배경추가이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_1:
                        btbg = dr_4;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //   Toast.makeText(getActivity().getApplicationContext(), "배경1이 선택됬da", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.bt_bg_2:
                        btbg = dr_5;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //   Toast.makeText(getActivity().getApplicationContext(), "배경2이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_3:
                        btbg = dr_6;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //   Toast.makeText(getActivity().getApplicationContext(), "배경3이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_4:
                        btbg = dr_7;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //  Toast.makeText(getActivity().getApplicationContext(), "배경4이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_5:
                        btbg = dr_8;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //  Toast.makeText(getActivity().getApplicationContext(), "배경5이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_6:
                        btbg = dr_9;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        // Toast.makeText(getActivity().getApplicationContext(), "배경6이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bt_bg_7:
                        btbg = dr_10;
                        iv_bg.setBackground(new BitmapDrawable(btbg));
                        //  Toast.makeText(getActivity().getApplicationContext(), "배경7이 선택됬da", Toast.LENGTH_LONG).show();
                        break;
                }
        bg = getByteArrayFromBitmap(btbg);
            }
        };
    }

    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {


        if (requestCode == PICK_FROM_GALLERY) {
try{
    Bundle extras2 = data.getExtras();if (extras2 != null) {
        btbg = extras2.getParcelable("data");
        iv_bg.setBackground(new BitmapDrawable(btbg));
    }
} catch (Exception e){

}

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    public void callbg(){
        //  int dr_bar = getResources().getIdentifier("@drawable/post_colorpick_gradient","drawable",getActivity().getPackageName());
        dr_1 = BitmapFactory.decodeResource(getResources(), R.drawable.post_clear_bg);
        dr_2 = BitmapFactory.decodeResource(getResources(), R.drawable.post_add_photo);
        dr_4 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_cherryblossom);
        dr_5 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_universe);
        dr_6 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_autumn);
        dr_7 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_house);
        dr_8 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_lake);
        dr_9 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_mountain);
        dr_10 = BitmapFactory.decodeResource(getResources(), R.drawable.bg_westminster);
        dr_trans = BitmapFactory.decodeResource(getResources(), R.drawable.bg_trans);
    }

    public void noti(int no){
            manager.insertData(no,edittext.getText().toString(), bg, fc);
    }

    public byte[] getByteArrayFromBitmap(Bitmap d) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        d.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }

}
