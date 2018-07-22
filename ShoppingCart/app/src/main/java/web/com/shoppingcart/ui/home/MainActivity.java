package web.com.shoppingcart.ui.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import web.com.shoppingcart.Constants;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.ui.category.CategoryFragment;
import web.com.shoppingcart.ui.detail.DetailFragment;
import web.com.shoppingcart.ui.home.fragment.HomeFragment;
import web.com.shoppingcart.ui.product.ProductFragment;

public class MainActivity extends DaggerAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity_Cart741";
    private static final String BACK_STACK_ROOT_TAG = "home_fragment";

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    CategoryFragment mCategoryFragment;

    @Inject
    ProductFragment mProductFragment;

    @Inject
    DetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }

        FragmentManager fragments = getSupportFragmentManager();
        Fragment homeFrag = fragments.findFragmentByTag(HomeFragment.TAG);
        Log.d(TAG,"on back pressed fragments.getBackStackEntryCount()->"+fragments.getBackStackEntryCount());
        Log.d(TAG,"on back pressedhomeFrag-->"+homeFrag);
        if (fragments.getBackStackEntryCount() > 1) {
            // We have fragments on the backstack that are poppable
            fragments.popBackStackImmediate();
        } else if (homeFrag == null || !homeFrag.isVisible()) {
            // We aren't showing the home screen, so that is the next stop on the back journey
            displaySelectedScreen(R.id.nav_home);
        } else {
            // We are already showing the home screen, so the next stop is out of the app.
            supportFinishAfterTransition();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        String tag = HomeFragment.TAG;
        switch (itemId) {
            case R.id.nav_home:
                fragment = mHomeFragment;
                tag = HomeFragment.TAG;
                break;
            case R.id.nav_shop_by_cat:
                fragment = mCategoryFragment;
                tag = CategoryFragment.TAG;
                break;
        }

        //replacing the fragment
        setMainView(fragment, tag);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void setMainView(Fragment fragment, String fragmentTag){
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            //String fragmentTag = fragment.getTag();//fragment.getClass().getSimpleName();
            if (fragmentTag != null && fragmentTag.equalsIgnoreCase(HomeFragment.TAG)) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }


            Log.d(TAG,"@@is fragmentTag -->"+fragmentTag);
            boolean fragmentPopped = fragmentManager
                    .popBackStackImmediate(fragmentTag , 0);

            if (!fragmentPopped && fragmentManager.findFragmentByTag(fragmentTag) == null) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Log.e(TAG,"--fragment-->"+fragment);
                ft.replace(R.id.content_frame, fragment, fragmentTag);
                ft.addToBackStack(null);
                ft.commit();
                fragmentManager.executePendingTransactions();
            }
            Fragment homeFrag = fragmentManager.findFragmentByTag(HomeFragment.TAG);
            Log.d(TAG,"@@is homeFrag null-->"+homeFrag);
            Log.d(TAG,"@@fragments.getBackStackEntryCount()->"+fragmentManager.getBackStackEntryCount());
        }
    }

    public void launchProductView(int catId){
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.bundle_key_id, catId);
        mProductFragment.setArguments(bundle);
        setMainView(mProductFragment, ProductFragment.TAG);
    }

    public void launchProductDetailView(ProductFullInfo productFullInfo){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.bundle_key_productFullInfo, productFullInfo);
        mDetailFragment.setArguments(bundle);
        setMainView(mDetailFragment, DetailFragment.TAG);
    }
}
