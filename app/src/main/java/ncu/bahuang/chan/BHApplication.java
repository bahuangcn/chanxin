package ncu.bahuang.chan;

import android.app.Application;

import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.metrics.MetricsManager;

/**
 * Author: BaHuang
 * Date: 2017/3/18 23:15
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class BHApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initHockeyAppSDK();
    }

    private void initHockeyAppSDK() {
        MetricsManager.register(this);
        FeedbackManager.register(this);
    }
}
