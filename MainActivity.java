package com.my.android.activityrecognition;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/*
 *  Get 3D accelerometer values and show in TextView widgets in a blank activity
 */
public class MainActivity extends Activity implements SensorEventListener {	
	
	SensorManager sensorManager;
	TextView textX,textY,textZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textX=(TextView)findViewById(R.id.textView1);
		textY=(TextView)findViewById(R.id.textView2);
		textZ=(TextView)findViewById(R.id.textView3);
		
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL); // play with this to change sampling rate        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
	
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            getSensorValues(event.values[0], event.values[1], event.values[2]);
        }       
    }
   
    public void getSensorValues(Float x, Float y, Float z) {
        textX.setText("x: " + x.toString());
        textY.setText("y: " + y.toString());
        textZ.setText("z: " + z.toString());
        // or save in a memory buffer or in a file etc. ...
    }
}
