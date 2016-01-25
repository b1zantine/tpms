package me.sudar.tpms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import me.sudar.tpms.preferences.Preferences;

public class SettingsActivity extends AppCompatActivity{
    int pro1;
    int pro2;
    int pro3;
    int pro4;
    int pro5;
    int old_position1;
    int old_position2;
    int pos1;
    int pos2;
    SeekBar sk1;
    TextView seekvalue1;
    SeekBar sk2;
    TextView seekvalue2;
    SeekBar sk3;
    TextView seekvalue3;
    SeekBar sk4;
    TextView seekvalue4;
    SeekBar sk5;
    TextView seekvalue5;

    TextView seek1;
    TextView seek2;
    TextView seek3;
    TextView seek4;
    TextView seek5;
    TextView seek6;
    TextView seek7;
    TextView seek8;

    String text;

    public RadioGroup rg1;
    public RadioGroup rg2;

    SharedPreferences sp;
    private static Typeface robotoTypeFace;
    private int temp1,temp2,temp3,temp4;

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
        sk5 = (SeekBar) findViewById(R.id.seekBar5);
        seekvalue5 = (TextView) findViewById(R.id.seekvalue5);
        rg1 = (RadioGroup) findViewById(R.id.radio_pressure);
        rg2 = (RadioGroup) findViewById(R.id.radio_temperature);

        seek1 = (TextView) findViewById(R.id.seek1);
        seek2 = (TextView) findViewById(R.id.seek2);
        seek3 = (TextView) findViewById(R.id.seek3);
        seek4 = (TextView) findViewById(R.id.seek4);
        seek5 = (TextView) findViewById(R.id.seek5);
        seek6 = (TextView) findViewById(R.id.seek6);
        seek7 = (TextView) findViewById(R.id.seek7);
        seek8 = (TextView) findViewById(R.id.seek8);

        sp = getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);

        setRobotoFont(SettingsActivity.this, (ViewGroup) findViewById(R.id.focusPuller).getParent());

        loadData();

        seekbar_range_pos1(pos1);
        seekbar_range_pos2(pos2);

        old_position1=pos1;
        old_position2=pos2;

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
                seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                sk4.setProgress(pro4);
                break;
            case 1:
                seekvalue4.setText(String.valueOf(pro4 + 32) + "" + "°F");
                sk4.setProgress(pro4);
                break;
        }


        sk5.setProgress(pro5);
        seekvalue5.setText(String.format("%.2f",(float)pro5 / 1000) + " V");

        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(pro1 < pro2) {
                    sk1.setProgress(pro2);
                    Toast.makeText(SettingsActivity.this,"HP can't be lower than LP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
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
                if (pro2 > pro1) {
                    sk2.setProgress(pro1);
                    Toast.makeText(SettingsActivity.this, "LP can't be higher than HP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
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
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
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
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
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

        sk5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                pro5 = progress;    //we can use the progress value of pro as anywhere
                seekvalue5.setText(String.format("%.2f", (float) pro5 / 1000) + " V");
                sk5.setProgress(pro5);
            }
        });


        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Method 1 For Getting Index of RadioButton
                pos1 = rg1.indexOfChild(findViewById(checkedId));
                conversion_pressure(pos1);
                seekbar_range_pos1(pos1);
                setPressureProgress();
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Method 1 For Getting Index of RadioButton
                pos2 = rg2.indexOfChild(findViewById(checkedId));
                conversion_temperature(pos2);
                seekbar_range_pos2(pos2);
                setTempProgress();
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
                sk1.setMax(7);
                sk2.setMax(7);
                sk3.setMax(7);
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
            case 0:
                if(pos==1){
                    temp1 =(int) Math.ceil(pro1 * 0.0689);
                    temp2 =(int) Math.ceil(pro2 * 0.0689);
                    temp3 =(int) Math.ceil(pro3 * 0.0689);
                    old_position1=1;
                }
                else if(pos==2){
                    temp1 = (int) (pro1 * 6.89);
                    temp2 = (int) (pro2 * 6.89);
                    temp3 = (int) (pro3 * 6.89);
                    old_position1=2;
                }
                break;

            case 1:
                if(pos==0){
                    temp1 = (int) (pro1 / 0.0689);
                    temp2 = (int) (pro2 / 0.0689);
                    temp3 = (int) (pro3 / 0.0689);
                    old_position1=0;
                }
                else if(pos==2){
                    temp1 = pro1 * 100;
                    temp2 = pro2 * 100;
                    temp3 = pro3 * 100;
                    old_position1=2;
                }
                break;

            case 2:
                if(pos==0){
                    temp1 = (int) Math.ceil(pro1 * 0.145);
                    temp2 = (int) Math.ceil(pro2 * 0.145);
                    temp3 = (int) Math.ceil(pro3 * 0.145);
                    old_position1=0;
                }
                else if(pos==1){
                    temp1 = (int) Math.ceil(pro1 * 0.01);
                    temp2 = (int) Math.ceil(pro2 * 0.01);
                    temp3 = (int) Math.ceil(pro3 * 0.01);
                    old_position1=1;
                }
                break;
        }
    }

    public void setPressureProgress(){
        sk1.setProgress(temp1);
        sk2.setProgress(temp2);
        sk3.setProgress(temp3);
    }

    public void conversion_temperature(int pos){
        switch (old_position2){
            case 0:if(pos==1){
                temp4 = (int)(pro4 * 1.8);
                old_position2 = pos;
            }
                break;

            case 1:if(pos==0){
                temp4 = (int) (pro4 / 1.8);
                old_position2 = pos;
            }
                break;
        }
    }

    public void setTempProgress(){sk4.setProgress(temp4);}

    public void saveData() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Preferences.MAX_PRESSURE, pro1);
        editor.putInt(Preferences.MIN_PRESSURE, pro2);
        editor.putInt(Preferences.LEAK_PRESSURE, pro3);
        editor.putInt(Preferences.MAX_TEMPERATURE, pro4);
        editor.putInt(Preferences.MIN_BATTERY, pro5);
        editor.putInt(Preferences.PRESSURE_UNIT, pos1);
        editor.putInt(Preferences.TEMPERATURE_UNIT, pos2);
        editor.apply();
    }

    public void loadData() {
        pro1 = sp.getInt(Preferences.MAX_PRESSURE, 40);
        pro2 = sp.getInt(Preferences.MIN_PRESSURE, 22);
        pro3 = sp.getInt(Preferences.LEAK_PRESSURE, 10);
        pro4 = sp.getInt(Preferences.MAX_TEMPERATURE, 55);
        pro5 = sp.getInt(Preferences.MIN_BATTERY, 22);
        pos1 = sp.getInt(Preferences.PRESSURE_UNIT, 0);
        pos2 = sp.getInt(Preferences.TEMPERATURE_UNIT, 0);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.revert_settings) {
            revertSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void revertSettings(){
        ((RadioButton) rg1.getChildAt(0)).setChecked(true);
        ((RadioButton) rg2.getChildAt(0)).setChecked(true);
        pro1 = 40;
        pro2 = 22;
        pro3 = 10;
        pro4 = 55;
        pro5 = 22;

        seekbar_range_pos1(0);
        seekbar_range_pos2(0);

        sk1.setProgress(pro1);
        sk2.setProgress(pro2);
        sk3.setProgress(pro3);
        sk4.setProgress(pro4);
        sk5.setProgress(pro5);
    }

    public void setRobotoFont (Context context, View view)
    {
        if (robotoTypeFace == null)
        {
            robotoTypeFace = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        }
        setFont(view, robotoTypeFace);
    }

    private void setFont (View view, Typeface robotoTypeFace)
    {
        if (view instanceof ViewGroup)
        {
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
            {
                setFont(((ViewGroup)view).getChildAt(i), robotoTypeFace);
            }
        }
        else if (view instanceof TextView)
        {
            ((TextView) view).setTypeface(robotoTypeFace);
        }
    }
}

