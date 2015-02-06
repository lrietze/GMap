package demo.gmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


	 public class MainActivity extends Activity implements OnMapReadyCallback {

		 private GoogleMap mMap;
		 // CONSTANTS FOR REVERSE GEOCODING
		 	public static final int SUCCESS_RESULT = 0;
		    public static final int FAILURE_RESULT = 1;
		    public static final String PACKAGE_NAME =
		        "com.google.android.gms.location.sample.locationaddress";
		    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
		    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
		        ".RESULT_DATA_KEY";
		    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
		        ".LOCATION_DATA_EXTRA";
		 // END
		 		 
		 @Override
	     protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.activity_main);

	         MapFragment mapFragment = (MapFragment) getFragmentManager()
	                 .findFragmentById(R.id.map);
	         mapFragment.getMapAsync(this);
	         
	         
	     }

	     
	     @Override
	     public void onMapReady(GoogleMap map) {
	    
	    	 mMap =  map;
	    	 LatLng kamloops = new LatLng(50.6761, -120.3408);

	         mMap.setMyLocationEnabled(true);
	         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kamloops, 13));
	         //if(x == 0)
	        	// map.setMapType(GoogleMap.MAP_TYPE_NONE);
	         //else if(x == 1)
	        //	 map.setMapType(x);
	         //else if(x == 2)
	        	 //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	        // else if(x == 3)
	        	 //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	         
	         

	         mMap.addMarker(new MarkerOptions()
	                 .title("Kamloops")
	                 .snippet("Land of the Roaming Jeffs")
	                 .position(kamloops));
	     }
	     
	     
	 
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	    switch (item.getItemId())
	    {
	    	case R.id.map_normal:
	        //if (id == R.id.map_normal) {
	    		Log.i("MapFuck",mMap + " ");
	    		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	    		
	        	
	        	//onMapReady(map);
	        	//MAP_TYPE_NORMAL = 1;
	        		        	
	            break;
	        //}
	        //if (id == R.id.map_satellite) {
	    	case R.id.map_satellite:
	    		//MAP_TYPE_NORMAL = 2;
	    		
	    		//x = 2;
	        	mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	            //return true;
	        //}
	        //if (id == R.id.map_hybrid) {
	    		break;
	    	case R.id.map_hybrid:
	    		//MAP_TYPE_NORMAL = 4;
	    		
	        	//x = 4;
	    		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	            //return true;
	    		break;
	    	case R.id.map_random:
	    		randomLocation();
	    		break;
	        }
	        return super.onOptionsItemSelected(item);
	    }


		private void randomLocation() {
			
			final double CANADA_TOPLEFT_Y = -138.00;
			final double CANADA_TOPLEFT_X = 75.00;
			
			final double CANADA_BOTTOMRIGHT_Y = -80.00;
			final double CANADA_BOTTOMRIGHT_X = 48.00;
			
			String name = "";
			String address = "";
			List<Address> addresses = null;
			
			double latt = randomCoords(CANADA_TOPLEFT_X, CANADA_BOTTOMRIGHT_X);
			double longi = randomCoords(CANADA_TOPLEFT_Y, CANADA_BOTTOMRIGHT_Y);

			LatLng RANDOM = new LatLng(latt, longi);
						
			try {
				Geocoder geocoder = new Geocoder(this, Locale.getDefault());
				addresses = geocoder.getFromLocation(latt, longi, 1);
				if(addresses.size() != 0)
					address = addresses.get(0).getAddressLine(0);
				else
					address = "Sorry, the princess is in another castle";
				Log.i("Geocoder","Returned " + addresses.get(0).getAddressLine(0) + " for " + latt + " " + longi);
				//addresses = gc.getFromLocation(latt, longi, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			mMap.addMarker(new MarkerOptions()
            	.title("Random!")
            	.snippet(address)
            	.position(RANDOM))
            	.showInfoWindow();
			CameraPosition cameraPosition = new CameraPosition.Builder()
		    	.target(RANDOM)      // Sets the center of the map to Mountain View
		    	.zoom(13)                   // Sets the zoom
		    	//.bearing(90)                // Sets the orientation of the camera to east
		    	.tilt(30)                   // Sets the tilt of the camera to 30 degrees
		    	.build();                   // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

			
		}


		private double randomCoords(double a,
				double b) {
			Random rand = new Random();
						
			return (a + (b - a) * rand.nextDouble());
		}
	 
	 
	 protected void onHandleIntent(Intent intent) {
	     Geocoder geocoder = new Geocoder(this, Locale.getDefault());
	     
	 }
	 }
	/* 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	 mLocationView = new TextView(this);
    	 setContentView(mLocationView);
    	 mGoogleApiClient = new GoogleApiClient.Builder(this)
    	 .addApi(LocationServices.API)
    	 .addConnectionCallbacks(this)
    	 .addOnConnectionFailedListener(this)
    	 .build();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onStart()
     * 
     * Life Cycle Management
     */
	 /*
    
    @Override
    protected void onStart() {
	    super.onStart();
	    // Connect the client.
	    mGoogleApiClient.connect();
    }
    @Override
    protected void onStop() {
	    // Disconnecting the client invalidates it.
	    mGoogleApiClient.disconnect();
	    super.onStop();
    }
    
    final int RQS_GooglePlayServices = 1;
    @Override
    protected void onResume() {
	    // TODO Auto-generated method stub
	    super.onResume();
	    int resultCode =
	    GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
	
	    if (resultCode == ConnectionResult.SUCCESS){
	    	Toast.makeText(getApplicationContext(), "Google Play services is available.", Toast.LENGTH_LONG).show();
	    }else{
	    	GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices).show();
	    }
	
    }


    @Override
    public void onLocationChanged(Location location) {
    	counter++;
    	mLocationView.setText("Location received: " +
    	location.toString() + "\nSpeed: " + Double.toString(location.getSpeed()));
   
    	String locationtxt = "Counter " + counter + " Latitude = " +
    	Double.toString(location.getLatitude()) + 
   			",Longitude = " + Double.toString(location.getLongitude()) + 
   			", Altitude " + Double.toString(location.getAltitude()) +
   			", Speed " + Double.toString(location.getSpeed());
    	Log.i(TAG, locationtxt);
    }


	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
	if (connectionResult.hasResolution()) {
	try {
		// Start an Activity that tries to resolve the error
		connectionResult.startResolutionForResult(this,
		CONNECTION_FAILURE_RESOLUTION_REQUEST);
	} catch (IntentSender.SendIntentException e) {
			// Log the error
			e.printStackTrace();
		}
	} else {
			// 	If no resolution is available, display a dialog to the user
			GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this,
			CONNECTION_FAILURE_RESOLUTION_REQUEST).show();
		}
	}



	@Override
	 public void onConnected(Bundle bundle) {
		 mLocationRequest = LocationRequest.create();
		 mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		 mLocationRequest.setInterval(500); // Update location every second
		 LocationServices.FusedLocationApi.requestLocationUpdates(
		 mGoogleApiClient, mLocationRequest, this);
	 }


	@Override
	 public void onConnectionSuspended(int i) {
		Log.i(TAG, "GoogleApiClient connection has been suspended");
	 }
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 // Decide what to do based on the original request code
		 switch (requestCode) {
			 case CONNECTION_FAILURE_RESOLUTION_REQUEST :
			 /*
			 * If the result code is Activity.RESULT_OK, try
			 * to connect again
			 *//*
			 switch (resultCode) {
			 	case Activity.RESULT_OK :
			 		/*
			 		 * Try the request again
			 		 *//*
			 	break;
			 }
		 }
	}
    
    
    
}
*/