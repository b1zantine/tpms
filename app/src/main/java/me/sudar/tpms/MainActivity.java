package me.sudar.tpms;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import me.sudar.tpms.preferences.Defaults;
import me.sudar.tpms.preferences.Preferences;


public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView dataTextView;
    private SharedPreferences sp;
    private int maxPressure, minPressure, maxPressureLeak, maxTemperature, minVoltage, pressureUnit, temperatureUnit;
    private boolean carInDanger = false;

    private BluetoothSPP bt = new BluetoothSPP(this);
    private BluetoothSPP.OnDataReceivedListener  btListener = new BluetoothSPP.OnDataReceivedListener() {

        @Override
        public void onDataReceived(byte[] data, String message) {
            int i=0;
            int[] dataCapsule = new int[15];
            while (i < data.length){
                Byte b = data[i];
                dataCapsule[counter] = b.intValue();
                dataTextView.setText(dataTextView.getText() + "" + dataCapsule[counter] + " " );
                if((counter+1) % 15 == 0) {
                    parseData(dataCapsule);
                    dataTextView.setText(dataTextView.getText() + "\n");
                }
                counter = (counter + 1) % 15;
                i++;
            }
        }
    };

    public void parseData(int[] dataCapsule){
        carInDanger = false;
        frontLeft(pressConversion(dataCapsule[0]), tempConversion(dataCapsule[1]), dataCapsule[2]);
        frontRight(pressConversion(dataCapsule[3]), tempConversion(dataCapsule[4]), dataCapsule[5]);
        rearLeft(pressConversion(dataCapsule[6]), tempConversion(dataCapsule[7]), dataCapsule[8]);
        rearRight(pressConversion(dataCapsule[9]), tempConversion(dataCapsule[9]), dataCapsule[11]);
        spare(pressConversion(dataCapsule[12]), tempConversion(dataCapsule[13]), dataCapsule[14]);
        if(carInDanger)
            ((ImageView)findViewById(R.id.car)).setImageResource(R.drawable.car_red);
        else
            ((ImageView)findViewById(R.id.car)).setImageResource(R.drawable.car);
    }

    public void frontLeft(int press, int temp, int volt){
        TextView tv = (TextView) findViewById(R.id.pressure_value_fl);
        tv.setText(press + "");
        if(press > maxPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_fl)).setImageResource(R.drawable.hp_red);
            carInDanger = true;
        }else if(press < minPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_fl)).setImageResource(R.drawable.lp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.pressure_icon_fl)).setImageResource(R.drawable.n);
        }

        tv = (TextView) findViewById(R.id.temp_value_fl);
        tv.setText(temp + "");
        if(press > maxTemperature){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.temperature_icon_fl)).setImageResource(R.drawable.temp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.temperature_icon_fl)).setImageResource(R.drawable.temp);
        }

        if((volt * 100) < minVoltage)
            ((ImageView)findViewById(R.id.battery_icon_fl)).setImageResource(R.drawable.battery_red);
        else
            ((ImageView)findViewById(R.id.battery_icon_fl)).setImageResource(R.drawable.battery);
    }

    public void frontRight(int press, int temp, int volt){
        TextView tv = (TextView) findViewById(R.id.pressure_value_fr);
        tv.setText(press + "");
        if(press > maxPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_fr)).setImageResource(R.drawable.hp_red);
            carInDanger = true;
        }else if(press < minPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_fr)).setImageResource(R.drawable.lp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.pressure_icon_fr)).setImageResource(R.drawable.n);
        }

        tv = (TextView) findViewById(R.id.temp_value_fr);
        tv.setText(temp + "");
        if(press > maxTemperature){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.temperature_icon_fr)).setImageResource(R.drawable.temp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.temperature_icon_fr)).setImageResource(R.drawable.temp);
        }

        if((volt * 100) < minVoltage)
            ((ImageView)findViewById(R.id.battery_icon_fr)).setImageResource(R.drawable.battery_red);
        else
            ((ImageView)findViewById(R.id.battery_icon_fr)).setImageResource(R.drawable.battery);
    }

    public void rearLeft(int press, int temp, int volt){
        TextView tv = (TextView) findViewById(R.id.pressure_value_rl);
        tv.setText(press + "");
        if(press > maxPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_rl)).setImageResource(R.drawable.hp_red);
            carInDanger = true;
        }else if(press < minPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_rl)).setImageResource(R.drawable.lp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.pressure_icon_rl)).setImageResource(R.drawable.n);
        }

        tv = (TextView) findViewById(R.id.temp_value_rl);
        tv.setText(temp + "");
        if(press > maxTemperature){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.temperature_icon_rl)).setImageResource(R.drawable.temp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.temperature_icon_rl)).setImageResource(R.drawable.temp);
        }

        if((volt * 100) < minVoltage)
            ((ImageView)findViewById(R.id.battery_icon_rl)).setImageResource(R.drawable.battery_red);
        else
            ((ImageView)findViewById(R.id.battery_icon_rl)).setImageResource(R.drawable.battery);
    }

    public void rearRight(int press, int temp, int volt){
        TextView tv = (TextView) findViewById(R.id.pressure_value_rr);
        tv.setText(press + "");
        if(press > maxPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_rr)).setImageResource(R.drawable.hp_red);
            carInDanger = true;
        }else if(press < minPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_rr)).setImageResource(R.drawable.lp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.pressure_icon_rr)).setImageResource(R.drawable.n);
        }

        tv = (TextView) findViewById(R.id.temp_value_rr);
        tv.setText(temp + "");
        if(press > maxTemperature){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.temperature_icon_rr)).setImageResource(R.drawable.temp_red);
            carInDanger = true;
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.temperature_icon_rr)).setImageResource(R.drawable.temp);
        }

        if((volt * 100) < minVoltage)
            ((ImageView)findViewById(R.id.battery_icon_rr)).setImageResource(R.drawable.battery_red);
        else
            ((ImageView)findViewById(R.id.battery_icon_rr)).setImageResource(R.drawable.battery);
    }

    public void spare(int press, int temp, int volt){
        TextView tv = (TextView) findViewById(R.id.pressure_value_spare);
        tv.setText(press + "");
        if(press > maxPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_spare)).setImageResource(R.drawable.hp_red);
        }else if(press < minPressure){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.pressure_icon_spare)).setImageResource(R.drawable.lp_red);
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.pressure_icon_spare)).setImageResource(R.drawable.n);
        }

        tv = (TextView) findViewById(R.id.temp_value_spare);
        tv.setText(temp + "");
        if(press > maxTemperature){
            tv.setTextColor(getResources().getColor(R.color.WarningRed));
            ((ImageView)findViewById(R.id.temperature_icon_spare)).setImageResource(R.drawable.temp_red);
        }else{
            tv.setTextColor(getResources().getColor(R.color.Green));
            ((ImageView)findViewById(R.id.temperature_icon_spare)).setImageResource(R.drawable.temp);
        }

        if((volt * 100) < minVoltage)
            ((ImageView)findViewById(R.id.battery_icon_spare)).setImageResource(R.drawable.battery_red);
        else
            ((ImageView)findViewById(R.id.battery_icon_spare)).setImageResource(R.drawable.battery);
    }

    public int pressConversion(int press){
        if(pressureUnit == 0) return press;
        else if (pressureUnit == 1) return (int) Math.ceil(press * 0.0689);
        else return (int) (press * 6.89);
    }

    public int tempConversion(int temp){
        if(temperatureUnit == 0) return temp;
        else return (int)(temp * 1.8) + 32;
    }

    public void loadData() {
        maxPressure = sp.getInt(Preferences.MAX_PRESSURE, Defaults.maxPressure);
        minPressure = sp.getInt(Preferences.MIN_PRESSURE, Defaults.minPressure);
        maxPressureLeak = sp.getInt(Preferences.LEAK_PRESSURE, Defaults.maxPressureLeak);
        maxTemperature = sp.getInt(Preferences.MAX_TEMPERATURE, Defaults.maxTemperature);
        minVoltage = sp.getInt(Preferences.MIN_BATTERY, Defaults.minVoltage);
        pressureUnit = sp.getInt(Preferences.PRESSURE_UNIT, Defaults.pressureUnit);
        temperatureUnit = sp.getInt(Preferences.TEMPERATURE_UNIT, Defaults.temperatureUnit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataTextView = (TextView)findViewById(R.id.data_textview);
        sp = getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);
        loadData();

        if(!bt.isBluetoothAvailable()) {
            Toast.makeText(this, "This App requires Bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setOnDataReceivedListener(btListener);

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                if(TpmsApp.connectedTimes == 1)
                    Toast.makeText(getApplicationContext()
                            , "Connected to " + name + "\n" + address, Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() {
//                Toast.makeText(getApplicationContext()
//                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if(!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_ANDROID);
                if(TpmsApp.deviceAddress == null){
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }else{
                    bt.connect(TpmsApp.deviceAddress);
                    TpmsApp.connectedTimes++;
                }

                setup();
            }
        }
    }

    public void setup() {
//        Button btnSend = (Button)findViewById(R.id.btnSend);
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                bt.send("Text", true);
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if(resultCode == Activity.RESULT_OK){
                TpmsApp.deviceAddress =  bt.connect(data);
                TpmsApp.connectedTimes++;
            }else {
                Toast.makeText(getApplicationContext()
                        , "No Device Selected"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }

        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_ANDROID);
                if(TpmsApp.deviceAddress == null){
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }else{
                    bt.connect(TpmsApp.deviceAddress);
                    TpmsApp.connectedTimes++;
                }
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bt.stopService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

         new AlertDialog.Builder(this)
                .setMessage("Do you want to exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        TpmsApp.deviceAddress = null;
                        TpmsApp.connectedTimes = 0;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {}
                })
                .show();
    }

}
