package web.com.shoppingcart.ui.product;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import web.com.shoppingcart.Constants;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.ui.home.MainActivity;

public class ProductFragment extends DaggerFragment
        implements ProductContract.View, ProductContract.GridRecyclerItemClickListener {

    public static final String TAG = "ProductFragment_Cart741";

    @Inject
    ProductContract.Presenter mProductPresenter;

    @BindView(R.id.recyclerViewForGrid)
    RecyclerView mRecyclerViewForGrid;

    @BindView(R.id.sort_container)
    LinearLayout sortContainer;

    @BindView(R.id.group_action_container)
    RelativeLayout headerView;

    @BindView(R.id.no_data_container)
    LinearLayout noDataContainer;

    @Inject
    ProductGridAdapter mProductGridAdapter;

    @Inject
    Context mContext;

    private List<ProductFullInfo> mListOfProducts;

    @Override
    public void onResume() {
        super.onResume();
        mProductPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProductPresenter.dropView();
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
    public ProductFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        int categoryId = bundle.getInt(Constants.bundle_key_id, -1);
        mProductPresenter.loadData(categoryId);
        mProductGridAdapter.setGridRecyclerItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerViewForGrid.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        mRecyclerViewForGrid.setAdapter(mProductGridAdapter);
        sortContainer.setOnClickListener(sortProductsListener);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shop by Products");
    }

    @Override
    public void updateListCustom(List<ProductFullInfo> tempList) {
        mListOfProducts = tempList;
        if (tempList == null || tempList.size() <= 0) {
            headerView.setVisibility(View.INVISIBLE);
            noDataContainer.setVisibility(View.VISIBLE);
        }
        Log.d(TAG, "$$tempList-->" + tempList.size());
        for (ProductFullInfo prod : tempList) {
            Log.d(TAG, "prod info-->" + prod);
        }

        mProductGridAdapter.clearData();
        mProductGridAdapter.updateList(tempList);
        mProductGridAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener sortProductsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "start sorting");
            PopupMenu dropDownMenu = new PopupMenu(view.getContext(), sortContainer);
            dropDownMenu.getMenuInflater().inflate(R.menu.sort_drop_down_menu, dropDownMenu.getMenu());
            //showMenu.setText("DropDown Menu");
            dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Log.d(TAG, "onMenuItemClick->" + menuItem.getTitle() + "<-->" + menuItem.getItemId());
                    mProductPresenter.sortData(mListOfProducts, menuItem.getItemId());
                    return true;
                }
            });
            dropDownMenu.show();
        }
    };

    @Override
    public void onItemClickListener(ProductFullInfo productFullInfo) {
        ((MainActivity) getActivity()).launchProductDetailView(productFullInfo);
    }
}
