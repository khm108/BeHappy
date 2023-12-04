package com.hello.ourApplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hello.ourApplication.Tutorial.FragmentTutorial1;
import com.hello.ourApplication.Tutorial.FragmentTutorial2;
import com.hello.ourApplication.Tutorial.FragmentTutorial3;
import com.hello.ourApplication.Tutorial.FragmentTutorial4;
import com.hello.ourApplication.Tutorial.FragmentTutorial5;
import com.hello.ourApplication.Tutorial.FragmentTutorial6;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentTutorial1();
            case 1:
                return new FragmentTutorial2();
            case 2:
                return new FragmentTutorial3();
            case 3:
                return new FragmentTutorial4();
            case 4:
                return new FragmentTutorial5();
            default:
                return new FragmentTutorial6();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
