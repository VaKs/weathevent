package weathevent.weathevent;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import Fragment.*;
import POJO.Event;


public class WeatheventActivity extends AppCompatActivity {


    // Toolbar and NavigationView
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ConstraintLayout weatheventMainLayout;


    // Fragments usability
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    /*  activeFragment =
     *   0 DashboardFragment
     *   1 ExploreFragment
     *   2 RecommendedFragment
     *   3 WeatherFragment
     *   4 FavouriteEventsFragment
     *   5 MapFragment
     *   6 EventPreviewFragment
     *   7 FriendsFragment
     *   8 PreferencesFragment
     */
    private String activeFragmentTag;

    // variables
    private Boolean isDrawerOpen;
    MapFragment mapFragment;


    //https://stackoverflow.com/questions/19013225/best-way-to-switch-between-two-fragments





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathevent);

        //references
        mDrawerLayout = findViewById(R.id.weathevent_dl);
        toolbar = findViewById(R.id.weathevent_toolbar);
        weatheventMainLayout = findViewById(R.id.weathevent_cl_main);
        //variables
        isDrawerOpen = false;
        if (activeFragmentTag == null) {
            activeFragmentTag = DashboardFragment.TAG;
        }

        //functions
        setupDrawerLayout();

        //Fragments
        setupFragments();
        showActiveFragment();
    }


    /*
        This function provide funcionality of DrawerLayout -
        initializes views, sets icons
     */
    private void setupDrawerLayout() {

        mDrawerToggle = new ActionBarDrawerToggle(
                this,            /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,               /* new Action Bar */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                isDrawerOpen = false;
                syncState();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isDrawerOpen = true;
                syncState();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.weathevent_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Snackbar.make(weatheventMainLayout, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();

                switch (item.getItemId()) {
                    case R.id.menuitem_drawer_dashboard:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        showDashboardFragment();
                        // or come back to Dashboard
                        return true;
                    case R.id.menuitem_drawer_friends:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        showFriendsFragment();
                        return true;

                    case R.id.menuitem_drawer_settings:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        showPreferencesFragment();
                        return true;

                    case R.id.menuitem_drawer_logout:
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        actionLogout();
                        return true;
                }

                //mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });

        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    /*
     *   This function create all Fragments and put them as a content of the view, in special list
     *
     */
    public void setupFragments() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.weathevent_cl_main, new DashboardFragment(), DashboardFragment.TAG);
        fragmentTransaction.commit();
    }




    /*
        This function is to set actions for all available menu items in actionBar
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (!isDrawerOpen) {
                    isDrawerOpen = true;
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {
                    isDrawerOpen = false;
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /*
     *   All methods which provides Fragment changes and set them as main fragment
     */

    public void showActiveFragment() {
        showDashboardFragment();
    }

    public void showDashboardFragment() {
        if (activeFragmentTag != DashboardFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            DashboardFragment dashboardFragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag(DashboardFragment.TAG);
            if (dashboardFragment == null) {
                dashboardFragment = new DashboardFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, dashboardFragment, DashboardFragment.TAG);
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = DashboardFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showExploreFragment() {
        if (activeFragmentTag != ExploreFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            ExploreFragment exploreFragment = (ExploreFragment) getSupportFragmentManager().findFragmentByTag(ExploreFragment.TAG);
            if (exploreFragment == null) {
                exploreFragment = new ExploreFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, exploreFragment, ExploreFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = ExploreFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showRecommendedFragment() {
        if (activeFragmentTag != RecommendFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            RecommendFragment recommendFragment = (RecommendFragment) getSupportFragmentManager().findFragmentByTag(RecommendFragment.TAG);
            if (recommendFragment == null) {
                recommendFragment = new RecommendFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, recommendFragment, RecommendFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = RecommendFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showWeatherFragment() {
        if (activeFragmentTag != WeatherFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager().findFragmentByTag(WeatherFragment.TAG);
            if (weatherFragment == null) {
                weatherFragment = new WeatherFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, weatherFragment, WeatherFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = WeatherFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showFavouriteEventsFragment() {
        if (activeFragmentTag != FavouriteEventsFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FavouriteEventsFragment favouriteEventsFragment = (FavouriteEventsFragment) getSupportFragmentManager().findFragmentByTag(FavouriteEventsFragment.TAG);
            if (favouriteEventsFragment == null) {
                favouriteEventsFragment = new FavouriteEventsFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, favouriteEventsFragment, FavouriteEventsFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = FavouriteEventsFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showMapFragment() {
        if (activeFragmentTag != MapFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentByTag(MapFragment.TAG);
            if (mapFragment == null) {
                mapFragment = new MapFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, mapFragment, MapFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = MapFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showEventPreviewFragment(Event event) {
        if (activeFragmentTag != EventPreviewFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            EventPreviewFragment eventPreviewFragment = (EventPreviewFragment) getSupportFragmentManager().findFragmentByTag(EventPreviewFragment.TAG);
            if (eventPreviewFragment == null) {
                eventPreviewFragment = new EventPreviewFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, eventPreviewFragment, EventPreviewFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = EventPreviewFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showFriendsFragment() {
        if (activeFragmentTag != FriendsFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            FriendsFragment friendsFragment = (FriendsFragment) getSupportFragmentManager().findFragmentByTag(FriendsFragment.TAG);
            if (friendsFragment == null) {
                friendsFragment = new FriendsFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, friendsFragment, FriendsFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = FriendsFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }


    public void showPreferencesFragment() {
        if (activeFragmentTag != PreferencesFragment.TAG) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            PreferencesFragment preferencesFragment = (PreferencesFragment) getSupportFragmentManager().findFragmentByTag(PreferencesFragment.TAG);
            if (preferencesFragment == null) {
                preferencesFragment = new PreferencesFragment();
            }
            fragmentTransaction.replace(R.id.weathevent_cl_main, preferencesFragment, PreferencesFragment.TAG);
            fragmentTransaction.addToBackStack(DashboardFragment.TAG);

            fragmentTransaction.commitAllowingStateLoss();
            activeFragmentTag = PreferencesFragment.TAG;
            getFragmentManager().executePendingTransactions();
        }
    }

    //Location Permissions Check
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MapFragment.MY_PERMISSIONS_REQUEST_LOCATION){
            mapFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }










    /*
        This function is called when user want logout.
        All status saving should be implemented here.
     */
    protected void actionLogout() {
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //setNavigationDrawer icon status
        //set activeFragment

    }
}


