package me.sudar.tpms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

import me.sudar.tpms.preferences.Preferences;

public class SettingsActivity extends AppCompatActivity{
    int pro1;
    int pro2;
    int pro3;
    int pro4;
    int old_position1;
    int old_position2;
    int pos1;
    int pos2;
    int converted_seekvalue4;
    String battery_value;
    SeekBar sk1;
    TextView seekvalue1;
    SeekBar sk2;
    TextView seekvalue2;
    SeekBar sk3;
    TextView seekvalue3;
    SeekBar sk4;
    TextView seekvalue4;
    EditText battery_threshold;

    TextView seek1;
    TextView seek2;
    TextView seek3;
    TextView seek4;
    TextView seek5;
    TextView seek6;
    TextView seek7;
    TextView seek8;

    public RadioGroup rg1;
    public RadioGroup rg2;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sk1 = (SeekBar) findViewById(R.id.seekBar1);
        seekvalue1 = (TextView) findViewById(R.id.seekvalue1);
        sk2 = (SeekBar) findViewById(R.id.seekBar2);
        seekvalue2 = (TextView) findViewById(R.id.seekvalue2);
        sk3 = (SeekBar) findViewById(R.id.seekBar3);
        seekvalue3 = (TextView) findViewById(R.id.seekvalue3);
        sk4 = (SeekBar) findViewById(R.id.seekBar4);
        seekvalue4 = (TextView) findViewById(R.id.seekvalue4);
        rg1 = (RadioGroup) findViewById(R.id.radio_pressure);
        rg2 = (RadioGroup) findViewById(R.id.radio_temperature);
        battery_threshold = (EditText) findViewById(R.id.battery_threshold);

        seek1 = (TextView) findViewById(R.id.seek1);
        seek2 = (TextView) findViewById(R.id.seek2);
        seek3 = (TextView) findViewById(R.id.seek3);
        seek4 = (TextView) findViewById(R.id.seek4);
        seek5 = (TextView) findViewById(R.id.seek5);
        seek6 = (TextView) findViewById(R.id.seek6);
        seek7 = (TextView) findViewById(R.id.seek7);
        seek8 = (TextView) findViewById(R.id.seek8);

        sp = getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);

        loadData();

        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        seekbar_range_pos1(pos1);
        seekbar_range_pos2(pos2);

        old_position1=pos1;
        old_position2=pos2;

        battery_threshold.setText(battery_value);
        RadioButton r1 = (RadioButton) rg1.getChildAt(pos1);
        r1.setChecked(true);

        RadioButton r2 = (RadioButton) rg2.getChildAt(pos2);
        r2.setChecked(true);

        switch (pos1) {
            case 0:
                seekvalue1.setText(String.valueOf(pro1) + " " + "PSI");
                sk1.setProgress(pro1);
                break;
            case 1:
                seekvalue1.setText(String.valueOf(pro1) + " " + "Bar");
                sk1.setProgress(pro1);
                break;
            case 2:
                seekvalue1.setText(String.valueOf(pro1) + " " + "KPa");
                sk1.setProgress(pro1);
                break;
        }

        switch (pos1) {
            case 0:
                seekvalue2.setText(String.valueOf(pro2) + " " + "PSI");
                sk2.setProgress(pro2);
                break;
            case 1:
                seekvalue2.setText(String.valueOf(pro2) + " " + "Bar");
                sk2.setProgress(pro2);
                break;
            case 2:
                seekvalue2.setText(String.valueOf(pro2) + " " + "KPa");
                sk2.setProgress(pro2);
                break;
        }

        switch (pos1) {
            case 0:
                seekvalue3.setText(String.valueOf(pro3) + " " + "PSI/min");
                sk3.setProgress(pro3);
                break;
            case 1:
                seekvalue3.setText(String.valueOf(pro3) + " " + "Bar/min");
                sk3.setProgress(pro3);
                break;
            case 2:
                seekvalue3.setText(String.valueOf(pro3) + " " + "KPa/min");
                sk3.setProgress(pro3);
                break;
        }

        switch (pos2) {
            case 0:
                seekvalue4.setText(String.valueOf(converted_seekvalue4) + " " + "°C");
                sk4.setProgress(pro4);
                break;
            case 1:
                seekvalue4.setText(String.valueOf(converted_seekvalue4) + " " + "°F");
                sk4.setProgress(pro4);
                break;
        }

        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if(pro1 < pro2)
                    sk1.setProgress(pro2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                pro1 = progress;    //we can use the progress value of pro as anywhere
                switch (pos1) {
                    case 0:
                        seekvalue1.setText(String.valueOf(pro1) + " " + "PSI");
                        break;
                    case 1:
                        seekvalue1.setText(String.valueOf(pro1) + " " + "Bar");
                        break;
                    case 2:
                        seekvalue1.setText(String.valueOf(pro1) + " " + "KPa");
                        break;
                }
            }
        });

        sk2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if (pro2 > pro1)
                    sk2.setProgress(pro1);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                pro2 = progress;    //we can use the progress value of pro as anywhere
                switch (pos1) {
                    case 0:
                        seekvalue2.setText(String.valueOf(progress) + " " + "PSI");
                        break;
                    case 1:
                        seekvalue2.setText(String.valueOf(progress) + " " + "Bar");
                        break;
                    case 2:
                        seekvalue2.setText(String.valueOf(progress) + " " + "KPa");
                        break;
                }
            }
        });

        sk3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                pro3 = progress;    //we can use the progress value of pro as anywhere
                switch (pos1) {
                    case 0:
                        seekvalue3.setText(String.valueOf(pro3) + " " + "PSI/min");
                        break;
                    case 1:
                        seekvalue3.setText(String.valueOf(pro3) + " " + "Bar/min");
                        break;
                    case 2:
                        seekvalue3.setText(String.valueOf(pro3) + " " + "KPa/min");
                        break;
                };
            }
        });

        sk4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

                pro4 = progress;    //we can use the progress value of pro as anywhere
                switch (pos2) {
                    case 0:
                        seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                        sk4.setProgress(pro4);
                        break;
                    case 1:
                        seekvalue4.setText(String.valueOf(pro4 + 32) + "" + "°F");
                        sk4.setProgress(pro4);
                        break;
                }

            }
        });


        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                int prog1;
                // Method 1 For Getting Index of RadioButton
                pos1 = rg1.indexOfChild(findViewById(checkedId));
                seekbar_range_pos1(pos1);
                conversion_pressure(pos1);
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                int prog1;
                // Method 1 For Getting Index of RadioButton
                pos2 = rg2.indexOfChild(findViewById(checkedId));
                seekbar_range_pos2(pos2);
                conversion_temperature(pos2);
            }
        });


        battery_threshold.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                battery_value = s.toString();
            }
        });
    }

    private void seekbar_range_pos1(int pos1) {
        switch (pos1){
            case 0:
                sk1.setMax(99);
                sk2.setMax(99);
                sk3.setMax(99);
                seek1.setText("0");
                seek2.setText("99");
                seek3.setText("0");
                seek4.setText("99");
                seek5.setText("0");
                seek6.setText("99");
                break;
            case 1:
                sk1.setMax(70);
                sk2.setMax(70);
                sk3.setMax(70);
                seek1.setText("0");
                seek2.setText("7");
                seek3.setText("0");
                seek4.setText("7");
                seek5.setText("0");
                seek6.setText("7");
                break;
            case 2:
                sk1.setMax(682);
                sk2.setMax(682);
                sk3.setMax(682);
                seek1.setText("0");
                seek2.setText("682");
                seek3.setText("0");
                seek4.setText("682");
                seek5.setText("0");
                seek6.setText("682");
                break;
        }
    }

    private void seekbar_range_pos2(int pos2){
        switch (pos2){
            case 0:
                sk4.setMax(70);
                seek7.setText("0");
                seek8.setText("70");
                break;
            case 1:
                sk4.setMax(126);
                seek7.setText("32");
                seek8.setText("158");
                break;
        }
    }

    public void conversion_pressure(int pos){
        switch(old_position1){
            case 0:if(pos==1){
                pro1 =(int)(pro1/14.513);
                pro2 =(int)(pro1/14.513);
                pro3 =(int)(pro1/14.513);
                sk1.setProgress(pro1);
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "Bar");
                seekvalue2.setText(String.valueOf(pro2) + " " + "Bar");
                seekvalue3.setText(String.valueOf(pro3) + " " + "Bar/min");
                old_position1=1;
            }
            else if(pos==2){
                pro1 *= 6.89;
                pro2 *= 6.89;
                pro3 *= 6.89;
                sk1.setProgress(pro1);
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "KPa");
                seekvalue2.setText(String.valueOf(pro2) + " " + "KPa");
                seekvalue3.setText(String.valueOf(pro3) + " " + "KPa/min");
                old_position1=2;
            }
                break;

            case 1:if(pos==0){
                pro1 /= 0.0689;
                pro2 /= 0.0689;
                pro3 /= 0.0689;
                sk1.setProgress(pro1);
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "PSI");
                seekvalue2.setText(String.valueOf(pro2) + " " + "PSI");
                seekvalue3.setText(String.valueOf(pro3) + " " + "PSI/min");
                old_position1=0;
            }
            else if(pos==2){
                pro1 *= 100;
                pro2 *= 100;
                pro3 *= 100;
                sk1.setProgress(pro1);
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "KPa");
                seekvalue2.setText(String.valueOf(pro2) + " " + "KPa");
                seekvalue3.setText(String.valueOf(pro3) + " " + "KPa/min");
                old_position1=2;
            }
                break;

            case 2:if(pos==0){
               // pro1 *= Math.round(pro1 * 0.145);
                pro2 *= Math.ceil(pro2 * 0.145);
                pro3 *= Math.ceil(pro3 * 0.145);
                sk1.setProgress((int)(pro1*0.145));
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "PSI");
                seekvalue2.setText(String.valueOf(pro2) + " " + "PSI");
                seekvalue3.setText(String.valueOf(pro3) + " " + "PSI/min");
                old_position1=0;
            }
            else if(pos==1){
                pro1 *= Math.ceil(pro1 * 0.01);
                pro2 *= Math.ceil(pro2 * 0.01);
                pro3 *= Math.ceil(pro3 * 0.01);
                sk1.setProgress(pro1);
                sk2.setProgress(pro2);
                sk3.setProgress(pro3);
                seekvalue1.setText(String.valueOf(pro1) + " " + "Bar");
                seekvalue2.setText(String.valueOf(pro2) + " " + "Bar");
                seekvalue3.setText(String.valueOf(pro3) + " " + "Bar/min");
                old_position1=1;
            }
                break;

        }
    }

    public void conversion_temperature(int pos){
        switch (old_position2){
            case 0:if(pos==1){
                pro4 = (int)(pro4 * 1.8) + 32;
                pro4-=32;
                sk4.setProgress(pro4);
                seekvalue4.setText(String.valueOf(pro4+32) + "" + "°F");
                old_position2 = pos;
            }
                break;

            case 1:if(pos==0){
                pro4+=32;
                pro4 = (int) ((pro4 - 32)/1.8);
                sk4.setProgress(pro4);
                seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                old_position2 = pos;
            }
                break;
        }
    }

    public void saveData() {
        String text = seekvalue4.getText().toString();
      //  converted_seekvalue4 = Integer.parseInt(text.substring(0, text.indexOf('°')));
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Preferences.MAX_PRESSURE, pro1);
        editor.putInt(Preferences.MIN_PRESSURE, pro2);
        editor.putInt(Preferences.LEAK_PRESSURE, pro3);
        editor.putInt(Preferences.MAX_TEMPERATURE, pro4);
        editor.putInt(Preferences.MAX_TEMPERATURE, pro4);
        editor.putInt(Preferences.CONVERTED_MAX_TEMERATURE,converted_seekvalue4);
        editor.putInt(Preferences.PRESSURE_UNIT, pos1);
        editor.putInt(Preferences.TEMPERATURE_UNIT, pos2);
        editor.putString(Preferences.MIN_BATTERY, battery_value);
        editor.commit();
    }

    public void loadData() {
        pro1 = sp.getInt(Preferences.MAX_PRESSURE, 40);
        pro2 = sp.getInt(Preferences.MIN_PRESSURE, 22);
        pro3 = sp.getInt(Preferences.LEAK_PRESSURE, 10);
        pro4 = sp.getInt(Preferences.MAX_TEMPERATURE, 55);
        converted_seekvalue4 = sp.getInt(Preferences.CONVERTED_MAX_TEMERATURE,55);
        pos1 = sp.getInt(Preferences.PRESSURE_UNIT, 0);
        pos2 = sp.getInt(Preferences.TEMPERATURE_UNIT, 0);
        battery_value = sp.getString(Preferences.MIN_BATTERY,"22");

    }

    @Override
    public void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}

