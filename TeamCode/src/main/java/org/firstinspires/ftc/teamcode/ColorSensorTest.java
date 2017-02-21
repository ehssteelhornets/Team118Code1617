package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
/**
 * Created by ehsst on 2/14/2017.
 */

@Autonomous(name="MuxColorSensor Test", group="Auto118")
public class ColorSensorTest extends HardwareMethods118_1617 {

    MultiplexColorSensor muxColor;
    int[] ports = {0, 1};
    enum Color {RED, BLUE};
    Color teamColor = Color.BLUE;

    @Override
    public void init()    {
        super.init();
        try {
            int milliSeconds = 48;
            muxColor = new MultiplexColorSensor(hardwareMap, "mux", "ada",
                    ports, milliSeconds,
                    MultiplexColorSensor.GAIN_16X);
        }
        catch(Exception e) {

        }
    }

    int opState = 1;
    @Override
    public void loop()  {
        switch (opState)  {
            case 1:
                int meanLeft = 0;
                int meanRight = 0;
                int[] crgb;
                for(int x=0; x<10; x++) {
                    crgb = muxColor.getCRGB(0);
                    meanLeft += crgb[1];
                    crgb = muxColor.getCRGB(1);
                    meanRight += crgb[1];
                    //sleep(50);
                }
                meanLeft /= 10;
                meanRight /= 10;
                telemetry.addData("Left",meanLeft);
                telemetry.addData("Right",meanRight);

                if (meanLeft > meanRight) {
                    switch (teamColor) {
                        case RED:
                            lPusher.setPosition(lServoDown);
                            //sleep(500);
                            lPusher.setPosition(lServoUp);
                            break;
                        case BLUE:
                            rPusher.setPosition(rServoDown);
                            //sleep(500);
                            rPusher.setPosition(rServoUp);
                            break;
                    }
                }
                else {
                    switch (teamColor) {
                        case RED:
                            lPusher.setPosition(lServoDown);
                            //sleep(500);
                            lPusher.setPosition(lServoUp);
                            break;
                        case BLUE:
                            rPusher.setPosition(rServoDown);
                            //sleep(200);
                            rPusher.setPosition(rServoUp);
                            break;
                    }
                }
                break;
        }


        for (int i=0; i<ports.length; i++) {
            int[] crgb = muxColor.getCRGB(ports[i]);

            telemetry.addLine("Sensor " + ports[i]);
            telemetry.addData("CRGB", "%5d %5d %5d %5d",
                    crgb[0], crgb[1], crgb[2], crgb[3]);
        }
        telemetry.update();
        //sleep(2000);
    }
}
