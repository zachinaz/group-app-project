package com.example.joiintheclub.FrontEnd.SearchGroup;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joiintheclub.R;

public class SearchRecycleAdapter extends RecyclerView.Adapter<SearchRecycleAdapter.ViewHolder> {


    private String[] groupNames =
            {
                    "Group 1",
                    "Group 2",
                    "Group 3",
                    "Group 4",
                    "Group 5",
                    "Group 6",
                    "Group 7",
                    "Group 8",
                    "Group 9",
            };

    private String[] details =
            {
                    "Group 1 details",
                    "Group 2 details",
                    "Group 3 details",
                    "Group 4 details",
                    "Group 5 details",
                    "Group 6 details",
                    "Group 7 details",
                    "Group 8 details",
                    "Group 9 details",
            };

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
                .inflate(R.layout.activity_search_card_view,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.groupName.setText(groupNames[i]);
        viewHolder.groupDetail.setText(details[i]);
        viewHolder.groupIcon.setImageResource(groupIcon[i]);

    }

    @Override
    public int getItemCount() {
        return groupNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView groupIcon;
        TextView groupName;
        TextView groupDetail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            groupIcon = itemView.findViewById(R.id.item_image);
            groupName = itemView.findViewById(R.id.item_title);
            groupDetail = itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,"Click detected on search item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                }
            });
        }


    }
}
