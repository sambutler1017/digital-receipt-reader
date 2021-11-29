package com.ridge.digitalreceiptreader.ui.home.adaptar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.common.utils.CommonUtils;

import java.util.List;

public class ReceiptListAdaptar extends RecyclerView.Adapter<ReceiptListAdaptar.ViewHolder> {

    private List<Receipt> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Data is passed into the constructor
    public ReceiptListAdaptar(Context context, List<Receipt> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.receipt_list, parent, false);
        return new ViewHolder(view);
    }

    // Binds the data to each element in the list
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F6F7F9"));
        }
        holder.mLocation.setText(mData.get(position).getLocation());
        holder.mLabel.setText(mData.get(position).getLabel());
        holder.mDate.setText(CommonUtils.formatDate(mData.get(position).getInsertDate()));
    }

    // Total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mLocation;
        TextView mLabel;
        TextView mDate;
        ViewHolder(View itemView) {
            super(itemView);
            mLocation = itemView.findViewById(R.id.location__textView);
            mLabel = itemView.findViewById(R.id.label__textView);
            mDate  = itemView.findViewById(R.id.date__textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // Convenience method for getting data at click position
    public Receipt getItem(int id) {
        return mData.get(id);
    }

    // Allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void updateList(List<Receipt> list) {
        //mDisplayedList = list;
        //notifyDataSetChanged();
    }
}
