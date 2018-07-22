package web.com.shoppingcart.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.Category;
import web.com.shoppingcart.ui.home.MainActivity;

public class CategoryFragment extends DaggerFragment
        implements CategoryContract.View, CategoryContract.RecyclerItemClickListener {

    public static final String TAG = "CategoryFragment_Cart741";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    CategoryAdapter mCategoryAdapter;

    @Inject
    CategoryContract.Presenter mCategoryPresenter;

    @Inject
    Context mContext;

    @Override
    public void onResume() {
        super.onResume();
        mCategoryPresenter.takeView(this);
        mCategoryPresenter.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCategoryPresenter.dropView();
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
    public CategoryFragment() {
        // Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        mCategoryAdapter.setRecyclerItemClickListener(this);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mCategoryAdapter);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Shop by Category");
    }

    @Override
    public void updateList(List<Category> tempList) {
        Log.d(TAG,"updateList-->"+tempList.size());
        mCategoryAdapter.clearData();
        mCategoryAdapter.updateList(tempList);
        mCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int position) {
        Log.d(TAG,"onItemClickListener-->" + position);
        int catId = mCategoryAdapter.getCatId(position);
        ((MainActivity)getActivity()).launchProductView(catId);
    }
}
