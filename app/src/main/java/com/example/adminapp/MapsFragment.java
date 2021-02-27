package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adminapp.GPS.GPSHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsFragment extends Fragment {
    private static final int LOCATION_PERMISSION = 100;
    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap currengoogleMap;
    public Marker ridermaeker;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsFragment.super.getContext());
            currengoogleMap = googleMap;
            UpdateCustomerLocation();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION) {
            if (permissions.length > 0) {
                UpdateCustomerLocation();
            }

        }
    }

    private void UpdateCustomerLocation() {
        if (ActivityCompat.checkSelfPermission(MapsFragment.super.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsFragment.super.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION);

            return;
        }


        Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
        System.out.println(lastLocation);
        lastLocation.addOnSuccessListener(new OnSuccessListener<Location>() {
            LatLng customertlocation;
            LatLng dragtlocation;
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    customertlocation = new LatLng(location.getLatitude(), location.getLongitude());

                    Toast.makeText(MapsFragment.super.getContext(), "location>>" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    BitmapDescriptor bitcurrent = getBitmapDesc(getContext(), R.drawable.ic_man_silhouette);
                    BitmapDescriptor bitmdrag =  getBitmapDesc(getContext(),R.drawable.ic_shop);
                    MarkerOptions markerOptions = new MarkerOptions().icon(bitcurrent).draggable(true).position(customertlocation).title(" Im here");
                    MarkerOptions marker = new MarkerOptions().icon(bitmdrag).draggable(false).position(customertlocation).title(" Im here");

                    currengoogleMap.addMarker(markerOptions);
                    currengoogleMap.addMarker(marker);
                    currengoogleMap.moveCamera(CameraUpdateFactory.newLatLng(customertlocation));
                    currengoogleMap.moveCamera(CameraUpdateFactory.zoomTo(19));

                   // ((Map)getActivity()).setLatlang(customertlocation);
currengoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        dragtlocation = marker.getPosition();
        Toast.makeText(getContext(), dragtlocation.latitude+","+dragtlocation.longitude, Toast.LENGTH_SHORT).show();
        ((Map)getActivity()).setLatlang(dragtlocation);
    }
});

                } else {
                    Toast.makeText(MapsFragment.super.getContext(), "location Not Found>>", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MapsFragment.super.getContext(), "location Not Foundddddddddd>>" + e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private BitmapDescriptor getBitmapDesc(Context context, int ic_baseline_add_location_24) {
        Drawable LAYER_1 = ContextCompat.getDrawable(context, ic_baseline_add_location_24);
        LAYER_1.setBounds(0, 0, LAYER_1.getIntrinsicWidth(), LAYER_1.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(LAYER_1.getIntrinsicWidth(), LAYER_1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        LAYER_1.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    GPSHelper gpsHelper = new GPSHelper(this);
  //  gpsHelper.getCurrentLocationListner(getActivity());


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    public void showRiderLocation(double currentrider_lat, double currentrider_lon) {
        if(currengoogleMap!=null){
            if(ridermaeker==null){
              //  ridermaeker=currengoogleMap.addMarker(new MarkerOptions().position(new LatLng(currentrider_lat,currentrider_lon)).icon(getBitmapDesc(getActivity(),R.drawable.ic_baseline_directions_bike_24)).title("Roider"));

            }
            ridermaeker.setPosition(new LatLng(currentrider_lat,currentrider_lon));
        }else{
            Log.d("MAP FRAGMNET","MAP NOT READY SKIPPPPPPPP");

        }
    }
}