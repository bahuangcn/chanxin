package ncu.bahuang.chan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.utils.Util;

import ncu.bahuang.chan.R;
import ncu.bahuang.chan.fragment.FirstFragment;
import ncu.bahuang.chan.listener.AutoUploadCrashManagerListener;
import ncu.bahuang.chan.util.EventTracker;

/**
 * Author: BaHuang
 * Date: 2017/3/16 00:00
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class MainActivity extends AbstractBHBaseActivity implements View.OnClickListener {

    private static final int DEFAULT_FRAGMENT_COUNT = 4;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateView(@Nullable Bundle savedInstanceState) {
        checkForUpdates();
        EventTracker.trackStartApp();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            FeedbackManager.showFeedbackActivity(this);
        }
    }

    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FirstFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return DEFAULT_FRAGMENT_COUNT;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CrashManager.register(this, Util.getAppIdentifier(this), new AutoUploadCrashManagerListener());
    }

    @Override
    public void onPause() {
        super.onPause();
        UpdateManager.unregister();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UpdateManager.unregister();
    }

    private void checkForUpdates() {
        UpdateManager.register(this);
    }

}
