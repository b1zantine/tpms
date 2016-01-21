package me.sudar.tpms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import me.sudar.tpms.preferences.Preferences;

public class SettingsActivity extends AppCompatActivity {
    public int pro1;
    public int pro2;
    public int pro3;
    public int pro4;
    public int pro5;
    public int pos1;
    public int pos2;
    public SeekBar sk1;
    public TextView seekvalue1;
    public SeekBar sk2;
    public TextView seekvalue2;
    public SeekBar sk3;
    public TextView seekvalue3;
    public SeekBar sk4;
    public TextView seekvalue4;

    public RadioGroup rg1;
    public RadioGroup rg2;

    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        sp = getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);

        loadData();

        RadioButton r1 = (RadioButton) rg1.getChildAt(pos1);
        r1.setChecked(true);

        RadioButton r2 = (RadioButton) rg2.getChildAt(pos2);
        r2.setChecked(true);

        switch (pos1) {
            case 0:
                seekvalue1.setText(String.valueOf(pro1) + "" + "PSI");
                sk1.setMax(100);
                sk1.setProgress(pro1);
                break;
            case 1:
                seekvalue1.setText(String.valueOf(pro1) + "" + "Bar");
                sk1.setMax(100);
                sk1.setProgress(pro1);
                break;
            case 2:
                seekvalue1.setText(String.valueOf(pro1) + "" + "KPa");
                sk1.setMax(100);
                sk1.setProgress(pro1);
                break;
        }

        switch (pos1) {
            case 0:
                seekvalue2.setText(String.valueOf(pro2) + "" + "PSI");
                sk2.setMax(100);
                sk2.setProgress(pro2);
                break;
            case 1:
                seekvalue2.setText(String.valueOf(pro2) + "" + "Bar");
                sk2.setMax(100);
                sk2.setProgress(pro2);
                break;
            case 2:
                seekvalue2.setText(String.valueOf(pro2) + "" + "KPa");
                sk2.setMax(100);
                sk2.setProgress(pro2);
                break;
        }

        switch (pos2) {
            case 0:
                seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                sk4.setMax(100);
                sk4.setProgress(pro4);
                break;
            case 1:
                seekvalue4.setText(String.valueOf(pro4) + "" + "°F");
                sk4.setMax(100);
                sk4.setProgress(pro4);
                break;
        }

        seekvalue3.setText(String.valueOf(pro3) + "" + "PSI/min");
        sk3.setProgress(pro3);

        sk1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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

                pro1 = progress;    //we can use the progress value of pro as anywhere
                //pos1 = rg1.getCheckedRadioButtonId();
                switch (pos1) {
                    case 0:
                        seekvalue1.setText(String.valueOf(pro1) + "" + "PSI");
                        break;
                    case 1:
                        seekvalue1.setText(String.valueOf(pro1) + "" + "Bar");
                        break;
                    case 2:
                        seekvalue1.setText(String.valueOf(pro1) + "" + "KPa");
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
                switch (pos2) {
                    case 0:
                        seekvalue2.setText(String.valueOf(progress) + "" + "PSI");
                        break;
                    case 1:
                        seekvalue2.setText(String.valueOf(progress) + "" + "Bar");
                        break;
                    case 2:
                        seekvalue2.setText(String.valueOf(progress) + "" + "KPa");
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
                seekvalue3.setText(String.valueOf(progress) +"" + "PSI/min");
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
                //pos1 = rg1.getCheckedRadioButtonId();
                switch (pos2) {
                    case 0:
                        seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                        sk4.setMax(100);
                        sk4.setProgress(pro4);
                        break;
                    case 1:
                        seekvalue4.setText(String.valueOf(pro4) + "" + "°F");
                        sk4.setMax(100);
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

                switch (pos1) {
                    case 0:
                        seekvalue1.setText(String.valueOf(pro1) + "" + "PSI");
                        seekvalue2.setText(String.valueOf(pro2) + "" + "PSI");
                        sk1.setProgress(pro1);
                        sk2.setProgress(pro2);
                        break;
                    case 1:
                        //PROGRESS VALUE CALCULATION NEED TO BE CHANGED
                        pro1 *= 0.5;
                        pro2 *= 0.5;
                        sk1.setProgress(pro1);
                        sk2.setProgress(pro2);
                        seekvalue1.setText(String.valueOf(pro1) + "" + "Bar");
                        seekvalue2.setText(String.valueOf(pro2) + "" + "Bar");
                        break;
                    case 2:
                        //PROGRESS VALUE CALCULATION NEED TO BE CHANGED
                        pro1 *= 0.25;
                        pro2 *= 0.25;
                        sk1.setProgress(pro1);
                        sk2.setProgress(pro2);
                        seekvalue1.setText(String.valueOf(pro1 * 0.25) + "" + "KPa");
                        seekvalue2.setText(String.valueOf(pro2 * 0.25) + "" + "KPa");
                        break;
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                int prog1;
                // Method 1 For Getting Index of RadioButton
                pos2 = rg2.indexOfChild(findViewById(checkedId));

                switch (pos2) {
                    case 0:
                        seekvalue4.setText(String.valueOf(pro4) + "" + "°C");
                        sk4.setProgress(pro4);
                        break;
                    case 1:
                        //PROGRESS VALUE CALCULATION NEED TO BE CHANGED
                        pro4*=0.5;
                        sk4.setProgress(pro4);
                        seekvalue4.setText(String.valueOf(pro4) + "" + "°F");
                        break;
                }
            }
        });
    }


    public void saveData() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Preferences.MAX_PRESSURE, pro1);
        editor.putInt(Preferences.MIN_PRESSURE, pro2);
        editor.putInt(Preferences.LEAK_PRESSURE, pro3);
        editor.putInt(Preferences.MAX_TEMPERATURE, pro4);
        editor.putInt(Preferences.PRESSURE_UNIT, pos1);
        editor.putInt(Preferences.TEMPERATURE_UNIT,pos2);
        editor.commit();
    }

    public void loadData() {
        pro1 = sp.getInt(Preferences.MAX_PRESSURE, 40);
        pro2 = sp.getInt(Preferences.MIN_PRESSURE, 22);
        pro3 = sp.getInt(Preferences.LEAK_PRESSURE, 10);
        pro4 = sp.getInt(Preferences.MAX_TEMPERATURE, 55);
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
}

