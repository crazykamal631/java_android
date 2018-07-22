package web.com.shoppingcart.ui.product;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.ProductFullInfo;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ProductViewHolder> {
    private static final String TAG = "ProductGridAdapter_Cart741";
    List<ProductFullInfo> products;
    private ProductContract.GridRecyclerItemClickListener gridRecyclerItemClickListener;

    public ProductGridAdapter(List<ProductFullInfo> products) {
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_with_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ProductViewHolder vh = new ProductViewHolder(parent.getContext(), v); // pass the view to View Holder
        return vh;
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        holder.name.setText(products.get(position).name);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setGridRecyclerItemClickListener(ProductContract.GridRecyclerItemClickListener gridRecyclerItemClickListener) {
        this.gridRecyclerItemClickListener = gridRecyclerItemClickListener;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
        @BindView(R.id.product_name)
        TextView name;

        @BindView(R.id.product_current_price)
        TextView productPrice;

        @BindView(R.id.product_actual_price)
        TextView productActualPrice;

        @BindView(R.id.product_discout_info)
        TextView productDiscountInfo;

        @BindView(R.id.product_emi_info)
        TextView productEmiInfo;

        @BindView(R.id.image)
        ImageView image;

        public ProductViewHolder(Context context, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            // get the reference of item view's
            productActualPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            productEmiInfo = (TextView) itemView.findViewById(R.id.product_emi_info);
            productEmiInfo.setText(getSpannableText(context, "  No Cost EMI"));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int productId = products.get(getAdapterPosition()).id;
            Log.d(TAG, "item clicked->" + getAdapterPosition() + "productId:-->" + productId);
            gridRecyclerItemClickListener.onItemClickListener(products.get(getAdapterPosition()));
        }
    }

    public void clearData() {
        products.clear();
    }

    public void updateList(List<ProductFullInfo> tempList) {
        products.addAll(tempList);
    }

    private SpannableStringBuilder getSpannableText(Context context, String text) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(text);
        ImageSpan searchImageSpan = new ImageSpan(context, R.drawable.emi_icon);
        ssBuilder.setSpan(
                searchImageSpan, // Span to add
                0, // Start of the span (inclusive)
                1, // End of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // Do not extend the span when text add later
        );
        return ssBuilder;
    }
}
