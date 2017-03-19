package ncu.bahuang.chan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: BaHuang
 * Date: 2017/3/18 23:48
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public abstract class AbstractBHBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        onCreateView(savedInstanceState);
    }

    protected abstract int getLayoutResId();

    protected abstract void onCreateView(@Nullable Bundle savedInstanceState);

}
