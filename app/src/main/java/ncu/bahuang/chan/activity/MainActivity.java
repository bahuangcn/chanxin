package ncu.bahuang.chan.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.utils.Util;

import java.util.Locale;

import ncu.bahuang.chan.R;
import ncu.bahuang.chan.fragment.DashboardFragment;
import ncu.bahuang.chan.listener.AutoUploadCrashManagerListener;
import ncu.bahuang.chan.listener.OnSingleClickListener;
import ncu.bahuang.chan.util.BHCountDownTimer;
import ncu.bahuang.chan.util.EventTracker;

/**
 * Author: BaHuang
 * Date: 2017/3/16 00:00
 * GitHub: https://github.com/bahuangcn
 * Website: http://linyuange.site
 */

public class MainActivity extends AbstractBHBaseActivity {

    private static final int DEFAULT_FRAGMENT_COUNT = 1;
    private LinearLayout mLlBtnContent;
    private TextView mTvStart, mTvPause, mTvSecond, mTvMinute, mTvDone;
    private BHCountDownTimer mCountDownTimer;
    private int mMinute, mSecond;
    private int mCountDownTimeInSecond;
    private SparseArray<View> mClickableViewArray = new SparseArray<>();


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

        mLlBtnContent = (LinearLayout) findViewById(R.id.ll_2btn_layout);
        mTvStart = (TextView) findViewById(R.id.tv_start);
        mTvPause = (TextView) findViewById(R.id.tv_pause);
        mTvDone = (TextView) findViewById(R.id.tv_done);
        mTvMinute = (TextView) findViewById(R.id.tv_minute);
        mTvSecond = (TextView) findViewById(R.id.tv_second);
        TextView tvRestart = (TextView) findViewById(R.id.tv_restart);
        TextView tvStop = (TextView) findViewById(R.id.tv_stop);
        mCountDownTimeInSecond = 70;
        initCountDownTimer();
        setOnSingleClickListener(mTvStart, mTvPause, mTvDone, tvRestart, tvStop);
        addViewInClickableArray(mTvStart, mTvPause, mTvDone, mLlBtnContent);
    }

    private void setOnSingleClickListener(@NonNull View... views) {
        for (View view : views) {
            view.setOnClickListener(mOnSingleClickListener);
        }
    }

    private OnSingleClickListener mOnSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                case R.id.tv_start:
                    setClickableViewVisible(mTvPause);
                    mCountDownTimer.start();
                    break;
                case R.id.tv_pause:
                    setClickableViewVisible(mLlBtnContent);
                    mCountDownTimer.stop();
                    break;
                case R.id.tv_restart:
                    setClickableViewVisible(mTvPause);
                    mCountDownTimer.reStart();
                    break;
                case R.id.tv_stop:
                    setClickableViewVisible(mTvStart);
                    mCountDownTimer.cancel();
                    initCountDownTimer();
                    break;
                case R.id.tv_done:
                    setClickableViewVisible(mTvStart);
                    initCountDownTimer();
                    break;
            }
        }
    };

    private void addViewInClickableArray(View... views) {
        for (View view : views) {
            if (mClickableViewArray.get(view.getId()) == null) {
                mClickableViewArray.append(view.getId(), view);
            }
        }
    }

    private void setClickableViewVisible(View view) {
        for (int index = 0, size = mClickableViewArray.size(); index < size; index++) {
            int key = mClickableViewArray.keyAt(index);
            if (key == view.getId()) {
                view.setVisibility(View.VISIBLE);
            } else {
                mClickableViewArray.get(key).setVisibility(View.GONE);
            }
        }
    }

    private void initCountDownTimer() {
        initCountDownTimerView();
        mCountDownTimer = new BHCountDownTimer(mCountDownTimeInSecond * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mSecond > 0) {
                    mSecond--;
                } else if (mMinute > 0) {
                    mMinute--;
                    mSecond = 59;
                }
                if (millisUntilFinished < 1000) {
                    Toast.makeText(MainActivity.this, "Congratulation!", Toast.LENGTH_SHORT).show();
                }
                mTvMinute.setText(String.format(Locale.getDefault(), "%02d", mMinute));
                mTvSecond.setText(String.format(Locale.getDefault(), "%02d", mSecond));
            }

            @Override
            public void onFinish() {
                finishCountDown();
            }
        };
    }

    private void finishCountDown() {
        mTvDone.setVisibility(View.VISIBLE);
        mTvStart.setVisibility(View.GONE);
        mTvPause.setVisibility(View.GONE);
        mLlBtnContent.setVisibility(View.GONE);
    }

    private void initCountDownTimerView() {
        mMinute = mCountDownTimeInSecond / 60;
        mSecond = mCountDownTimeInSecond % 60;
        mTvMinute.setText(String.format(Locale.getDefault(), "%02d", mMinute));
        mTvSecond.setText(String.format(Locale.getDefault(), "%02d", mSecond));
    }

    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DashboardFragment.newInstance(position);
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
