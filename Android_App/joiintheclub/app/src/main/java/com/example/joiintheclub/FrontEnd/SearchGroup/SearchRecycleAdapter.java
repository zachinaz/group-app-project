package com.example.joiintheclub.FrontEnd.SearchGroup;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joiintheclub.R;

import java.util.ArrayList;
import java.util.List;

public class SearchRecycleAdapter extends RecyclerView.Adapter<SearchRecycleAdapter.ViewHolder> {

    CardView cardView;


    private static List<String> groupNames;
    private static List<String> groupDetail;
    private static List<String> groupColor;
    private int[] groupIcon =
            {
                    R.drawable.search_icon,
                    R.drawable.profile_icon,
                    R.drawable.close_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
            };



    public SearchRecycleAdapter(List<String> groupNames, List<String> groupDetails, List<String> groupColor) {
        SearchRecycleAdapter.groupNames = groupNames;
        SearchRecycleAdapter.groupDetail = groupDetails;
        SearchRecycleAdapter.groupColor = groupColor;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_search_card_view,viewGroup,false);


        cardView = v.findViewById(R.id.search_card_view);

        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.groupName.setText(groupNames.get(i));
        viewHolder.groupDetail.setText(groupDetail.get(i));
        viewHolder.groupIcon.setImageResource(groupIcon[i]);
        String Color = groupColor.get(i);
      // cardView.setBackgroundColor(R.color.colorPrimary);

    }

    @Override
    public int getItemCount() {

        return groupNames.size();
    }

    public static void filterList(ArrayList<String> newList)
    {
        groupNames = newList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView groupIcon;
        TextView groupName;
        TextView groupDetail;


        @SuppressLint("ResourceType")
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
