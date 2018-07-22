package web.com.shoppingcart.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = "CategoryAdapter_Cart741";
    private List<Category> mList;

    public CategoryAdapter(List<Category> list) {
        this.mList = list;
    }

    private CategoryContract.RecyclerItemClickListener recyclerItemClickListener;
    public void setRecyclerItemClickListener(CategoryContract.RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    public void clearData() {
        mList.clear();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lyt_list_row, parent, false);
        //itemView.setOnClickListener(mOnClickListener);
        return new CategoryViewHolder(itemView, recyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category weatherRes = mList.get(position);
        holder.categoryLabel.setText(weatherRes.name);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList(List<Category> tempList) {
        mList.addAll(tempList);
    }

    public int getCatId(int position) {
        return mList.get(position).id;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "MyViewHolder_Cart741";
        @BindView(R.id.cat_label)
        public TextView categoryLabel;
        CategoryContract.RecyclerItemClickListener recyclerItemClickListener;

        CategoryViewHolder(View view, CategoryContract.RecyclerItemClickListener recyclerItemClickListener) {
            super(view);
            this.recyclerItemClickListener = recyclerItemClickListener;
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"onClick-->"+view);
            if (recyclerItemClickListener != null) {
                view.getTag();
                recyclerItemClickListener.onItemClickListener(getAdapterPosition());
            }
        }
    }
}
