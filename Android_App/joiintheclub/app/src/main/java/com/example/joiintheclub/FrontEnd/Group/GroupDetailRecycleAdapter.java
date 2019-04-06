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

public class GroupDetailRecycleAdapter extends RecyclerView.Adapter<GroupDetailRecycleAdapter.ViewHolder> {



    private String[] activityTitle =
            {
                    "activity 1",
                    "activity 2",
                    "activity 3",
                    "activity 4",
                    "activity 5",
                    "activity 6",
                    "activity 7",
                    "activity 8",
                    "activity 9",

            };


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


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_even_card_view,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.activity_Title.setText(activityTitle[i]);
        viewHolder.user_Profile.setImageResource(userProfile[i]);

    }

    @Override
    public int getItemCount() {
        return activityTitle.length;
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
