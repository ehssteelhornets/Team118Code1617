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
    float[] gData = new float[3];           // Gravity or accelerometer
    float[] mData = new float[3];           // Magnetometer

    float[] Rmat = new float[9];
    float[] R2 = new float[9];
    float[] Imat = new float[9];
    boolean haveGrav = false;
    boolean haveAccel = false;
    boolean haveMag = false;
    private SensorManager sensorManager;

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void registerListener(FtcRobotControllerActivity ftcRobotControllerActivity) {
        sensorManager = (SensorManager) ftcRobotControllerActivity.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener(FtcRobotControllerActivity ftcRobotControllerActivity) {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch( event.sensor.getType() ) {
            case Sensor.TYPE_GRAVITY:
                gData[0] = event.values[0];
                gData[1] = event.values[1];
                gData[2] = event.values[2];
                haveGrav = true;
                break;
            case Sensor.TYPE_ACCELEROMETER:
                if (haveGrav) break;    // don't need it, we have better
                gData[0] = event.values[0];
                gData[1] = event.values[1];
                gData[2] = event.values[2];
                haveAccel = true;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mData[0] = event.values[0];
                mData[1] = event.values[1];
                mData[2] = event.values[2];
                haveMag = true;
                break;
            default:
                return;
        }

        if ((haveGrav || haveAccel) && haveMag) {
            SensorManager.getRotationMatrix(Rmat, Imat, gData, mData);
            SensorManager.remapCoordinateSystem(Rmat,
                    SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, R2);
            // Orientation isn't as useful as a rotation matrix, but
            // we'll show it here anyway.
            SensorManager.getOrientation(R2, rotationData);

        }
    }
}


