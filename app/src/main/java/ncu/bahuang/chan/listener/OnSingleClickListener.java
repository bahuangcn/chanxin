package ncu.bahuang.chan.listener;

import android.os.SystemClock;
import android.view.View;

/**
 * Author: BaHuang
 * Date: 2017/3/18 21:26
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public abstract class OnSingleClickListener implements View.OnClickListener {

    private long mLastClickTime = 0L;
    private int mLastViewId = -1;
    private static final long CLICK_TIME_SPACE = 500L;

    @Override
    public void onClick(View view) {
        // think about click different view  || view.getId() != mLastViewId
        if (SystemClock.elapsedRealtime() - mLastClickTime > CLICK_TIME_SPACE) {
            mLastClickTime = SystemClock.elapsedRealtime();
            mLastViewId = view.getId();
            onSingleClick(view);
        }
    }

    public abstract void onSingleClick(View view);
}
