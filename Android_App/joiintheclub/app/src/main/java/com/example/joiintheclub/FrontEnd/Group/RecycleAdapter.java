package com.example.joiintheclub.FrontEnd.Group;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joiintheclub.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private OnNoteListener mOnNoteListener;
    private static List<String> groupNames;
    private List<String> groupDetail;

    public RecycleAdapter(OnNoteListener mOnNoteListener, List<String> groupNames, List<String> groupDetail) {
        this.mOnNoteListener = mOnNoteListener;
        RecycleAdapter.groupNames = groupNames;
        this.groupDetail = groupDetail;
    }

    private int[] groupIcon =
            {
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
            };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_card_view,viewGroup,false);
        return new ViewHolder(v,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.groupName.setText(groupNames.get(i));
        viewHolder.groupDetail.setText(groupDetail.get(i));
        viewHolder.groupIcon.setImageResource(groupIcon[i]);

    }

    @Override
    public int getItemCount() {

        return groupNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView groupIcon;
        TextView groupName;
        TextView groupDetail;
        OnNoteListener onNoteListener;

        ViewHolder(@NonNull final View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            groupIcon = itemView.findViewById(R.id.item_image);
            groupName = itemView.findViewById(R.id.item_title);
            groupDetail = itemView.findViewById(R.id.item_detail);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
