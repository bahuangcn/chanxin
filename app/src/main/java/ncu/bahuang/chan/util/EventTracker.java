package ncu.bahuang.chan.util;

import net.hockeyapp.android.metrics.MetricsManager;

import java.util.Map;

/**
 * Author: BaHuang
 * Date: 2017/3/19 00:33
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class EventTracker {

    private static final String EVENT_START_APP = "start app";

    private static void track(String eventName) {
        track(eventName, null);
    }

    private static void track(final String eventName, final Map<String, String> properties) {
        MetricsManager.trackEvent(eventName, properties);
    }

    public static void trackStartApp() {
        track(EVENT_START_APP);
    }
}
