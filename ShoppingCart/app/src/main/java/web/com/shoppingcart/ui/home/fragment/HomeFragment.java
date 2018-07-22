package web.com.shoppingcart.ui.home.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import web.com.shoppingcart.R;
import web.com.shoppingcart.ui.home.MainActivity;

public class HomeFragment extends DaggerFragment implements HomeContract.View {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.SliderDots)
    LinearLayout sliderDotspanel;

    @Inject
    Context mContext;

    @Inject
    ViewPagerAdapter viewPagerAdapter;

    private int dotscount;
    private ImageView[] dots;
    Activity mActivity;
    public static final String TAG = "HomeFragment_Cart741";

    @Inject
    HomeContract.Presenter mHomePresenter;

    @Inject
    public HomeFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mHomePresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomePresenter.dropView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
        doMagic(view);


        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(mContext);
            dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shopping");
    }

    private void doMagic(View view) {
        String s = "Shop by \n Category";
        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 8, 18, 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.BLACK), 8, 18, 0);// set color
        TextView tv = (TextView) view.findViewById(R.id.shop_by_category);
        tv.setText(ss1);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) mActivity).displaySelectedScreen(R.id.nav_shop_by_cat);
            }
        });
    }
}
