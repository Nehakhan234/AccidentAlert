package neha.accidentalert;
/**
 * Created by Neha on 12/3/2017.
 */

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import static android.location.LocationManager.GPS_PROVIDER;



public class GPStracker implements LocationListener {

    Context context;

    public GPStracker(Context c) {
        context = c;
    }

    public Location getLocation() {

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(GPS_PROVIDER);
        if (isGPSEnabled) {
            lm.requestLocationUpdates(GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(GPS_PROVIDER);
            return l;
        } else {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
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

    }
}
