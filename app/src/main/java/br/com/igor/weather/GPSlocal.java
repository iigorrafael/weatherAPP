package br.com.igor.weather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Aluno on 14/09/2017.
 */

public class GPSlocal implements LocationListener {

    Context context;

    public GPSlocal(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("LOL", "Permissão Negada!");
                return null;
            }else{
                Log.i("LOL", "Permissão ativa!");
            }
        }else{
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("LOL", "Permissão Negada!");
                return null;
            }else{
                Log.i("LOL", "Permissão ativa!");
            }
        }



        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSenabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGPSenabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            Log.i("LOL", "desativado");

//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        }
        return  null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }
}
