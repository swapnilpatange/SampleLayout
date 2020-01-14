package com.example.place.samplelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomBottomSheetFragment.FragmentListener {

    private TextView openDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDialog = findViewById(R.id.openDialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomBottomSheetFragment customBottomSheetFragment = CustomBottomSheetFragment.newInstance();
                customBottomSheetFragment.setListener(MainActivity.this);
                customBottomSheetFragment.show(getSupportFragmentManager(), "custom");
            }
        });
    }

    @Override
    public void setContent(List<CustomSelected> customSelecteds) {
        String content = "";
        for (int i = 0; i < customSelecteds.size(); i++) {
            content = content + customSelecteds.get(i).getTitle() + ": " + customSelecteds.get(i).getContent() + "\n";
        }
        openDialog.setText(content);
    }
}
