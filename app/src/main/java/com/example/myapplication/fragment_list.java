package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import org.jetbrains.annotations.NotNull;

public class fragment_list extends Fragment {
    DemoCollectionAdapter demoCollectionAdapter;
    ViewPager2 viewPager;

    public fragment_list() {
        super(R.layout.fragment_list);
    }

    public class DemoCollectionAdapter extends FragmentStateAdapter {
        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            return null;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
