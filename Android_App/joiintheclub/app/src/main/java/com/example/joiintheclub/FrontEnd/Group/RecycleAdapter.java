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

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

  /*  ArrayList<GroupMain> arrayList = new ArrayList<>();
    Context c;

    RecycleAdapter(Context c, ArrayList<GroupMain> arrayList)
    {
        this.c=c;
        this.arrayList =arrayList;
    }*/


    private String[] groupNames =
            {
                    "Group one",
                    "Group two",
                    "Group three",
                    "Group four",
                    "Group five",
                    "Group six",
                    "Group seven",
                    "Group eight",
                    "Group nine",
            };

    private String[] details =
            {
                    "Group one details",
                    "Group two details",
                    "Group three details",
                    "Group four details",
                    "Group five details",
                    "Group six details",
                    "Group seven details",
                    "Group eight details",
                    "Group nine details",
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
                .inflate(R.layout.activity_card_view,viewGroup,false);
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
                    Snackbar.make(v,"Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action",null).show();
                }
            });
        }


    }
}
