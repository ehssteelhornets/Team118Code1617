package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;

@Autonomous(name="Auto118_GyroTest0221", group="Auto118")

public class Auto118_GyroTest0221 extends LinearOpMode {
    float[] data = new float[3];
    GyroSensorComponents gyroSensorComponents = null;
    //With Rear Camera Facing Forward
    //Rock Back and Forth is around X axis Tangental to ground 0 is approxamate East
    //Twist/pan is around y axis Perpindicular to ground 0 is Geomagnetic North
    //Tilt Side to side around z axis  Tangental to ground plane 0 is screen skyward

    //With Rear Camera Facing Ground
    //Pitch is Around X Axis running from left to right of screen
    //Roll is Around Y Axis running from bottom to top of screen
    //Yaw is Around Z Axis which is the normal vector of the screen (Perpendicular)
    public enum Orientation {TANGENTAL, PERPENDICUALAR}
    public Orientation phoneOrientation = Orientation.PERPENDICUALAR;

    int opState = 1;
    HardwareBot robot = new HardwareBot();
   @Override
    public void runOpMode() throws InterruptedException{
       robot.init(hardwareMap);
       robot.RunWithEncoders();
       try{
           gyroSensorComponents = ((FtcRobotControllerActivity) hardwareMap.appContext).getGyroSensorComponents();
       }catch(Exception e){}
       waitForStart();
       while(opModeIsActive()) {
           telemetry.addData("X", gyroSensorComponents.rotationData[0]);
           telemetry.addData("Y", gyroSensorComponents.rotationData[1]);
           telemetry.addData("Z", gyroSensorComponents.rotationData[2]);
           telemetry.update();

           double currentHeading;
           if(phoneOrientation == Orientation.PERPENDICUALAR) {
                currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[1]);
           }
           else {
                currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[2]);
           }
            //Driving uses basic heading ranging from -180 to 180 degrees

           

           idle(); // Always call idle() at the bottom
       }
    }

    static double geoVectorToDegrees(double sinThetaOverTwo)    {
        double theta = 2 * Math.asin(sinThetaOverTwo);
        if (theta < 0.0) {
            theta += 360
        }
        return theta;
    }
}

//use initail then as follows for each individual turn
//lower values <180 add 360 ex 5 becomes 365 then all values inside loop have 360 added as long as they remain below 180
//higher values >180 subtract 360 ex 270 becomes -90 all values inside loop subtract 360 as long as they remain above 180