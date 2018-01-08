package com.example.mydialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydialog.activity.ScrollerActivity;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_custom_dialog:
                title = "S7";
                content = "        字字八划,年年八强,明凯一定会证明谁是世界第一打野";
                new AlertView(this,this,title,content).setCancelable(true).show();
                break;
            case R.id.bt_scroller:
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(Object o, int position) {
        Toast.makeText(this,"ssssss",Toast.LENGTH_LONG).show();
    }
}
