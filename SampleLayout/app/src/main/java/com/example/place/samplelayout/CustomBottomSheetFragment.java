package com.example.place.samplelayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomBottomSheetFragment extends BottomSheetDialogFragment {
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            if (slideOffset < -0.01) {

            } else {

            }
        }
    };
    private View ratingBreakupView;
    private RecyclerView mainList;
    private TextView addButton;
    private List<CustomOption> customOptions;
    private String result = "{\n" +
            "\t\"customlist\": [{\n" +
            "\t\t\"title\": \"choose any 1\",\n" +
            "\t\t\"option\": [{\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"chapati\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"bhakari\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"fulka\"\n" +
            "\t\t}],\n" +
            "\t\t\"type\": \"1\",\n" +
            "\t\t\"maxselected\": \"\"\n" +
            "\t}, {\n" +
            "\t\t\"title\": \"choose any 2\",\n" +
            "\t\t\"option\": [{\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"pizza\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"burger\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"isSelected\": \"0\",\n" +
            "\t\t\t\"name\": \"frankie\"\n" +
            "\t\t}],\n" +
            "\t\t\"type\": \"2\",\n" +
            "\t\t\"maxselected\": \"2\"\n" +
            "\t}]\n" +
            "}";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainList = ratingBreakupView.findViewById(R.id.mainList);
        addButton=ratingBreakupView.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){

                }
            }
        });
        try {
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getJSONArray("customlist") != null) {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<CustomOption>>() {
                }.getType();
                 customOptions = gson.fromJson(jsonObject.getJSONArray("customlist").toString(), listType);
                CustomItemAdapter customItemAdapter = new CustomItemAdapter(getActivity(),customOptions);
                mainList.setAdapter(customItemAdapter);
                mainList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //initializeViews();
        //setAdapter();
    }

    private boolean validate() {
        for(int i=0;i<customOptions.size();i++){
            if (customOptions.get(i).getType().equalsIgnoreCase("2")){

            }
        }
        return true;
    }

    public static CustomBottomSheetFragment newInstance() {
        final CustomBottomSheetFragment fragment = new CustomBottomSheetFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ratingBreakupView = inflater.inflate(R.layout.fragment_rating_breakup_bottomsheet, container, false);
        return ratingBreakupView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                View bottomSheet = bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet);

                if (null != bottomSheet) {
                    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
                }
            }
        });
        bottomSheetDialog.getWindow().getAttributes().windowAnimations = R.style.bottom_dialog_animation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setWhiteNavigationBar(bottomSheetDialog);
        }

        return bottomSheetDialog;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setWhiteNavigationBar(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            GradientDrawable dimDrawable = new GradientDrawable();
            // ...customize your dim effect here

            GradientDrawable navigationBarDrawable = new GradientDrawable();
            navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
            navigationBarDrawable.setColor(Color.WHITE);

            Drawable[] layers = new Drawable[2];
            layers[0] = dimDrawable;
            layers[1] = navigationBarDrawable;

            LayerDrawable windowBackground = new LayerDrawable(layers);
            windowBackground.setLayerInsetTop(1, metrics.heightPixels);

            window.setBackgroundDrawable(windowBackground);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog != null) {
            final View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            final View view = getView();

            view.post(new Runnable() {
                @Override
                public void run() {
                    View parent = (View) view.getParent();
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
                    CoordinatorLayout.Behavior behavior = params.getBehavior();
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
                    bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
                    ((View) bottomSheet.getParent()).setBackgroundColor(Color.TRANSPARENT);
                }
            });


        }

    }

}
