package me.sudar.tpms;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.sudar.tpms.preferences.Preferences;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private SharedPreferences sp;
    private View view;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences(Preferences.PREFERENCES_NAME, Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUnits();
    }

    public void setUnits(){
        TextView presUnitView;
        TextView tempUnitView;
        int presUnit = sp.getInt(Preferences.PRESSURE_UNIT, 0);
        int tempUnit = sp.getInt(Preferences.TEMPERATURE_UNIT,0);

        switch(presUnit){
            case 0 :
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fl);
                presUnitView.setText(getString(R.string.unit_psi));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fr);
                presUnitView.setText(getString(R.string.unit_psi));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rl);
                presUnitView.setText(getString(R.string.unit_psi));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rr);
                presUnitView.setText(getString(R.string.unit_psi));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_spare);
                presUnitView.setText(getString(R.string.unit_psi));
                break;
            case 1 :
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fl);
                presUnitView.setText(getString(R.string.unit_bar));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fr);
                presUnitView.setText(getString(R.string.unit_bar));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rl);
                presUnitView.setText(getString(R.string.unit_bar));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rr);
                presUnitView.setText(getString(R.string.unit_bar));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_spare);
                presUnitView.setText(getString(R.string.unit_bar));
                break;
            case 2 :
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fl);
                presUnitView.setText(getString(R.string.unit_kpa));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_fr);
                presUnitView.setText(getString(R.string.unit_kpa));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rl);
                presUnitView.setText(getString(R.string.unit_kpa));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_rr);
                presUnitView.setText(getString(R.string.unit_kpa));
                presUnitView = (TextView) view.findViewById(R.id.pressure_unit_spare);
                presUnitView.setText(getString(R.string.unit_kpa));
                break;
        }

        switch (tempUnit){
            case 0 :
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_fl);
                tempUnitView.setText(getString(R.string.deg_celsius));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_fr);
                tempUnitView.setText(getString(R.string.deg_celsius));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_rl);
                tempUnitView.setText(getString(R.string.deg_celsius));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_rr);
                tempUnitView.setText(getString(R.string.deg_celsius));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_spare);
                tempUnitView.setText(getString(R.string.deg_celsius));
                break;
            case 1 :
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_fl);
                tempUnitView.setText(getString(R.string.deg_fahrenheit));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_fr);
                tempUnitView.setText(getString(R.string.deg_fahrenheit));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_rl);
                tempUnitView.setText(getString(R.string.deg_fahrenheit));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_rr);
                tempUnitView.setText(getString(R.string.deg_fahrenheit));
                tempUnitView = (TextView) view.findViewById(R.id.temp_unit_spare);
                tempUnitView.setText(getString(R.string.deg_fahrenheit));
                break;
        }


    }

}
