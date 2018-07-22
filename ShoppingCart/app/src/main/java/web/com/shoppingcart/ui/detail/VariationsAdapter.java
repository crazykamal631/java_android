package web.com.shoppingcart.ui.detail;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.Variant;

public class VariationsAdapter extends RecyclerView.Adapter<VariationsAdapter.VariantViewHolder> {
    private static final String TAG = "VariationsAdapter_Cart741";
    private List<Variant> mVariantList;
    private int selected = 0;
    private boolean dispatchViewloaded = false;

    public VariationsAdapter(List<Variant> variantList) {
        this.mVariantList = variantList;
    }

    private DetailContract.Presenter mPresenter;

    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public VariantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG,"onCreateViewHolder"+selected);
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_horizontal_list_item, parent, false);
        VariantViewHolder gvh = new VariantViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(VariantViewHolder holder, final int position) {
        //holder.imageView.setImageResource(mVariantList.get(position).getProductImage());
        if (position == selected) {
            holder.linearLayout.setBackgroundColor(Color.BLUE);
        } else {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        }
        holder.textView.setText(mVariantList.get(position).color.toUpperCase());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String productName = mVariantList.get(position).getProductName().toString();
                //Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
                selected = position;
                notifyDataSetChanged();
                mPresenter.selectedVariant(mVariantList.get(position));
            }
        });
        if(!dispatchViewloaded){
            dispatchViewloaded = true;
            dataLoadingCompleted();
        }
    }

    @Override
    public int getItemCount() {
        return mVariantList.size();
    }

    public void clearData() {
        selected = 0;
        dispatchViewloaded = false;
        mVariantList.clear();
    }

    public void updateList(List<Variant> tempList) {
        mVariantList.addAll(tempList);
    }

    public void dataLoadingCompleted() {
        if (mVariantList.size() > 0) {
            mPresenter.selectedVariant(mVariantList.get(0));
        }
    }

    public class VariantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.idProductImage)
        ImageView imageView;

        @BindView(R.id.idProductName)
        TextView textView;

        @BindView(R.id.item_container)
        LinearLayout linearLayout;

        public VariantViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
