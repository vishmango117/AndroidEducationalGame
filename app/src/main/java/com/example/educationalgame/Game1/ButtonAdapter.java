package com.example.educationalgame.Game1;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ButtonAdapter extends BaseAdapter {

    private Context context;

    private int btn_id;
    private int total_buttons=20;
    private Random prng;
    private ArrayList<String> numberList;
    private boolean is_more_than_fourty;

    public ButtonAdapter(Context context) {
        this.context = context;
        prng = new Random();
        this.numberList = new ArrayList<>();
        is_more_than_fourty = false;
    }

    @Override
    public int getCount() {
        return total_buttons;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final Button btn;
        if(view == null) {
            btn = new Button(context);
            String myvalue = String.valueOf(prng.nextInt(20));
            btn.setText(myvalue);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn.setClickable(false);
                }
            });
        }
        else {
            btn = (Button) view;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), btn.getText(), Toast.LENGTH_LONG).show();
                btn.setClickable(false);
                btn.setBackgroundColor(Color.GREEN);
                numberList.add((String) btn.getText());
            }
        });



        return btn;
    }

    public boolean runGame () {
        int sum = 0;
        for(String currentNum: numberList) {
            if(sum > 40) {
                is_more_than_fourty = true;
            }
            else if(sum == 40) {
                return true;
            }
            else {
                sum += Integer.parseInt(currentNum);
                System.out.println("Sum:"+sum);
            }
        }
        return false;
    }

    public ArrayList<String> getNumberList() {
        return numberList;
    }

    public boolean isIs_more_than_fourty() {
        return is_more_than_fourty;
    }
}
