package com.gka.gkamobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.gka.gkamobile.R;
import com.gka.gkamobile.adapter.fragment.FragmentTabAdapter;
import com.gka.gkamobile.ui.dashboard.DashboardFragment;
import com.gka.gkamobile.ui.discover.DiscoverFragment;
import com.gka.gkamobile.ui.events.EventsFragment;
import com.gka.gkamobile.ui.news.NewsFragment;
import com.gka.gkamobile.ui.notifications.NotificationsFragment;
import com.gka.gkamobile.ui.register.RegisterFragment;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

 private HomeViewModel homeViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fixtures_new_tabs,container, false);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);
        return view;

    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

        FragmentTabAdapter adapter = new FragmentTabAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new EventsFragment(), "Events");
        adapter.addFragment(new RegisterFragment(), "Register");
        adapter.addFragment(new DiscoverFragment(), "Discover");
        viewPager.setAdapter(adapter);



    }
}