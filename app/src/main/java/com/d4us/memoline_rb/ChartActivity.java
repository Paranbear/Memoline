package com.d4us.memoline_rb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BlueBear on 3/27/2017.
 */

public class ChartActivity extends Fragment {

    // onCreate 시작
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_chart, container, false);




        return v;
    }
}
