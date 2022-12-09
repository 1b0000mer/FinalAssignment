package com.liepu.finalassignment.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liepu.finalassignment.R;
import com.liepu.finalassignment.assignment.models.Card;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Card> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    boolean mIsGiveUp;
    Context mContext;
    View view;

    public GameAdapter(Context context, List<Card> data, boolean isGiveUp) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mIsGiveUp = isGiveUp;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.f4_item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        if (mIsGiveUp) {
            int id = view.getResources().getIdentifier(getItem(position).getUrl(), "drawable",
                    mContext.getPackageName());
            holder.myImageView.setImageResource(id);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.iv);
            if (!mIsGiveUp) {
                myImageView.setImageResource(R.drawable.suit);
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, myImageView, getAdapterPosition());
            }
        }
    }

    Card getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, ImageView iv, int position);
    }
}
