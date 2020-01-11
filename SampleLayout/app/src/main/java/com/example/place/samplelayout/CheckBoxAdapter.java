package com.example.place.samplelayout;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.List;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.CheckBoxViewHolder> {


    private Activity activity;
    private String maxselected;
    List<CustomOptionList> customOptions;
    private int currentSelected = 0;
    private boolean onBind;

    public CheckBoxAdapter(Activity activity, List<CustomOptionList> option, String maxselected) {
        this.customOptions = option;
        this.maxselected = maxselected;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CheckBoxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_item, null);
        return new CheckBoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBoxViewHolder viewHolder, final int position) {

        viewHolder.checkBox.setText(customOptions.get(position).getName());
        onBind = true;
        if (customOptions.get(position).getIsSelected() != null
                && customOptions.get(position).getIsSelected().equalsIgnoreCase("1"))
            viewHolder.checkBox.setChecked(true);
        else
            viewHolder.checkBox.setChecked(false);
        onBind = false;
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!onBind) {
                    if (b) {

                        if (currentSelected < Integer.parseInt(maxselected)) {
                            currentSelected++;
                            customOptions.get(position).setIsSelected("1");
                        } else {
                            Toast.makeText(activity, "you can select maximum " + maxselected + " items", Toast.LENGTH_SHORT).show();
                            customOptions.get(position).setIsSelected("0");
                        }
                    } else {
                        currentSelected--;
                        customOptions.get(position).setIsSelected("0");
                    }
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customOptions.size();
    }

    class CheckBoxViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;

        public CheckBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

}
