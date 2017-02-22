package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

/**
 * Created by ian on 2/21/2017.
 */

public class GyroSensorComponents implements SensorEventListener {
    public float rotationData[] = new float[3];
    private SensorManager sensorManager;


    public void getRotVec(SensorEvent event){
        for (int i = 0; i<3; i++){
            rotationData[i] = event.values[i];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void registerListener(FtcRobotControllerActivity ftcRobotControllerActivity) {
        sensorManager = (SensorManager) ftcRobotControllerActivity.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener(FtcRobotControllerActivity ftcRobotControllerActivity) {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ROTATION_VECTOR){
           getRotVec(event);
        }
    }
}

