package ncu.bahuang.chan.listener;

import net.hockeyapp.android.CrashManagerListener;

/**
 * Author: BaHuang
 * Date: 2017/3/18 23:55
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class AutoUploadCrashManagerListener extends CrashManagerListener {
    @Override
    public boolean shouldAutoUploadCrashes() {
        return true;
    }
}