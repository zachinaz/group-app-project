package com.example.joiintheclub.FrontEnd.Group;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joiintheclub.R;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailRecycleAdapter extends RecyclerView.Adapter<GroupDetailRecycleAdapter.ViewHolder> {


    private static List<String> AnnName;
    private static List<String> pollName;
    private static ArrayList<String> All = new ArrayList<String>();


    private int[] userProfile =
            {
                    R.drawable.search_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
                    R.drawable.profile_icon,
            };

    public GroupDetailRecycleAdapter(List<String> pollName, List<String> AnnName) {
        GroupDetailRecycleAdapter.AnnName = AnnName;
        GroupDetailRecycleAdapter.pollName = pollName;
        All.addAll(pollName);
        All.addAll(AnnName);
        String s;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_even_card_view,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.activity_Title.setText(All.get(i));
        viewHolder.user_Profile.setImageResource(userProfile[i]);

    }

    @Override
    public int getItemCount() {
        return All.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView user_Profile;
        TextView activity_Title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_Profile = itemView.findViewById(R.id.user_profile);
            activity_Title = itemView.findViewById(R.id.activityTitle);

            user_Profile.bringToFront();
            itemView.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,"Click detected on detail item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                }
            });
        }


    }
}
