package web.com.shoppingcart.ui.detail;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import web.com.shoppingcart.Constants;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.db.Variant;

public class DetailFragment extends DaggerFragment
        implements DetailContract.View {

    public static final String TAG = "DetailFragment_Cart741";

    @Inject
    DetailContract.Presenter mDetailPresenter;

    @BindView(R.id.idRecyclerViewHorizontalList)
    RecyclerView mVariantsRecyclerView;

    @BindView(R.id.variant_color_value)
    TextView variantColorValue;

    @BindView(R.id.product_actual_price)
    TextView productActualPrice;

    @BindView(R.id.product_current_price)
    TextView productCurrentPrice;

    @BindView(R.id.product_name)
    TextView productName;

    @BindView(R.id.product_review_info)
    TextView productReviewInfo;

    @BindView(R.id.textViewProductColor)
    TextView textViewProductColor;

    @BindView(R.id.textViewProductColorCount)
    TextView textViewProductColorCount;

    @BindView(R.id.textViewProductSize)
    TextView textViewProductSize;

    @BindView(R.id.textViewProductSizeCount)
    TextView textViewProductSizeCount;

    @Inject
    VariationsAdapter mVariationsAdapter;

    @Inject
    Context mContext;

    @Override
    public void onResume() {
        super.onResume();
        mDetailPresenter.takeView(this);

        Bundle bundle = getArguments();
        ProductFullInfo productFullInfo= bundle.getParcelable(Constants.bundle_key_productFullInfo);
        int productId = productFullInfo.getId();
        mDetailPresenter.setDetails(productFullInfo);
        mDetailPresenter.getVariationsForProduct(productId);
        Log.d(TAG,"product info-->"+productFullInfo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDetailPresenter.dropView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Inject
    public DetailFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);


        mVariationsAdapter.setPresenter(mDetailPresenter);
        mVariantsRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL));
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mVariantsRecyclerView.setLayoutManager(horizontalLayoutManager);
        mVariantsRecyclerView.setAdapter(mVariationsAdapter);

        mVariantsRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mVariationsAdapter.dataLoadingCompleted();
                Log.e(TAG,"--------finished data loading----------");
                mVariantsRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shop by Category");
    }

    @Override
    public void updateList(List<Variant> tempList) {
        Log.d(TAG,"Variant list-->"+tempList.size());
        mVariationsAdapter.clearData();
        mVariationsAdapter.updateList(tempList);
        mVariationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectedVariant(Variant variant) {
        Log.d(TAG,"@@selectedVariant Variant -->"+variant);
        variantColorValue.setText(variant.color);
        textViewProductColor.setText(variant.color);
        productCurrentPrice.setText(String.valueOf(variant.price));
        productActualPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        productActualPrice.setText(String.valueOf((2*variant.price)));
    }

    @Override
    public void setDetails(String sharedInfo, String name) {
        productName.setText(name);
        productReviewInfo.setText(sharedInfo);
    }

    @Override
    public void updateSizeInfo(String sizeCount, String sizeString) {
        textViewProductSizeCount.setText(sizeCount);
        textViewProductSize.setText(sizeString);
    }

    @Override
    public void updateColorInfo(String colorCount) {
        textViewProductColorCount.setText(colorCount);
    }
}
