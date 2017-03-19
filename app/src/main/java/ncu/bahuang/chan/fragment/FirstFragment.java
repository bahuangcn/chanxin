package ncu.bahuang.chan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ncu.bahuang.chan.R;

/**
 * Author: BaHuang
 * Date: 2017/3/16 00:34
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class FirstFragment extends Fragment {

    private static final String BUNDLE_KEY_POSITION = "position";

    public static FirstFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_POSITION, position);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_position);
        int position = getArguments().getInt(BUNDLE_KEY_POSITION);
        tv.setText(String.valueOf(position));
        int[] colorArray = getResources().getIntArray(R.array.view_page_background);
//        view.setBackgroundColor(colorArray[position]);
        return view;
    }
}
