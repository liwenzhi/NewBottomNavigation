package com.example.bottom.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bottom.R;

/**
 * Created with IntelliJ IDEA.
 * User: wanpu_oyf
 * Date: 17-5-17
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("第一个页面");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.a1);
        return textView;
    }
}
