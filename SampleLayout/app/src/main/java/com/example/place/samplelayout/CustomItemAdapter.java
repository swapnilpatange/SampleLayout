package com.example.place.samplelayout;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class CustomItemAdapter extends RecyclerView.Adapter<CustomItemAdapter.CustomItemViewHolder> {
    private List<CustomOption> customOptions;
    private Activity activity;

    public CustomItemAdapter(Activity activity, List<CustomOption> customOptions) {
        this.customOptions = customOptions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item, null);
        return new CustomItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomItemViewHolder viewHolder, final int position) {

        viewHolder.title.setText(customOptions.get(position).getTitle());
        if (customOptions.get(position).getType() != null &&
                customOptions.get(position).getType().equalsIgnoreCase("1")) {
            for (int i = 0; i < customOptions.get(position).getOption().size(); i++) {
                RadioButton radioButton = new RadioButton(activity);
                radioButton.setText(customOptions.get(position).getOption().get(i).getName());
                //radioButton.setId(1234);//set radiobutton id and store it somewhere
                viewHolder.radioButtonList.addView(radioButton);
            }
            viewHolder.radioButtonList.setVisibility(View.VISIBLE);
            viewHolder.checkBoxList.setVisibility(View.GONE);
        } else {
            CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(activity,customOptions.get(position).getOption(),customOptions.get(position).getMaxselected());
            viewHolder.checkBoxList.setAdapter(checkBoxAdapter);
            viewHolder.checkBoxList.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));

            viewHolder.checkBoxList.setVisibility(View.VISIBLE);
            viewHolder.radioButtonList.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return customOptions.size();
    }

    class CustomItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public RadioGroup radioButtonList;
        public RecyclerView checkBoxList;

        public CustomItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            radioButtonList = itemView.findViewById(R.id.radioButtonList);
            checkBoxList = itemView.findViewById(R.id.checkBoxList);
        }
    }
}
