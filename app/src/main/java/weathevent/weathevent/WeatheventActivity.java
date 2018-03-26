package weathevent.weathevent;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class WeatheventActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ConstraintLayout weatheventMainLayout;

    private Boolean isDrawerOpen;

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

        //functions
        setupDrawerLayout();

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
                Snackbar.make(weatheventMainLayout, item.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();

                switch (item.getItemId()) {
                    case R.id.menuitem_drawer_dashboard:

                        return true;
                    case R.id.menuitem_drawer_friends:

                        return true;

                    case R.id.menuitem_drawer_settings:

                        return true;

                    case R.id.menuitem_drawer_logout:
                        actionLogout();
                        return true;
                }

                mDrawerLayout.closeDrawers();
                return true;

            }
        });

        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
        This function is called when user want logout.
        All status saving should be implemented here.
     */
    protected void actionLogout() {
        finish();
    }


}


