package com.example.latihanwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;
    private Sensor mSensorPressure;
    private Sensor mSensorAmbient;
    private Sensor mSensorMagnetic;



    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorPressure;
    private TextView mTextSensorAmbient;
    private TextView mTextSensorMagnetic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//        StringBuilder sensorText = new StringBuilder();
//        for (Sensor currentSensor : sensorList) {
//            sensorText.append(currentSensor.getName())
//                    .append(System.getProperty("line.separator"));
//        }
//        TextView sensorTextView = findViewById(R.id.sensor_list);
//        sensorTextView.setText(sensorText);

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        mTextSensorPressure = findViewById(R.id.label_pressure);
        mTextSensorAmbient = findViewById(R.id.label_ambient);
        mTextSensorMagnetic = findViewById(R.id.label_magnetic);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorAmbient = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        String sensor_error = "No Sensor";

        if (mSensorLight == null){
            mTextSensorLight.setText(sensor_error);
        }

        if (mSensorProximity == null){
            mTextSensorProximity.setText(sensor_error);
        }

        if (mSensorPressure == null){
            mTextSensorPressure.setText(sensor_error);
        }

        if (mSensorAmbient == null){
            mTextSensorAmbient.setText(sensor_error);
        }
        if (mSensorMagnetic == null){
            mTextSensorMagnetic.setText(sensor_error);
        }

    }
    @Override
    protected void onStart(){
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAmbient != null) {
            mSensorManager.registerListener(this, mSensorAmbient,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetic != null) {
            mSensorManager.registerListener(this, mSensorMagnetic,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(
                        String.format("Light sensor : %1$.2f",currentValue));
                changeBackgroundColor(currentValue);



                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(
                        String.format("Proximity sensor : %1$.2f",currentValue));



                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(
                        String.format("Pressure sensor : %1$.2f",currentValue));



                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorPressure.setText(
                        String.format("Ambient Temperature sensor : %1$.2f",currentValue));



                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mTextSensorMagnetic.setText(
                        String.format("Magnetic sensor : %1$.2f",currentValue));



                break;
            default:
                //
        }
    }

    private void changeBackgroundColor(float currentValue) {
        ConstraintLayout layout = findViewById(R.id.layout_constraint);
        if(currentValue <= 150 && currentValue >= 50) layout.setBackgroundColor(Color.RED);
        else if(currentValue <50 && currentValue >= 0) layout.setBackgroundColor(Color.BLUE);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}