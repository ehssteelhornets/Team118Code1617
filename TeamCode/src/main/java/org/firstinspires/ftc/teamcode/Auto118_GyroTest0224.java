package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;

@Autonomous(name="Auto118_GyroTest0221", group="Auto118")

public class Auto118_GyroTest0224 extends LinearOpMode {
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
    static HardwareBot robot = new HardwareBot();
   @Override
    public void runOpMode() throws InterruptedException{
       robot.init(hardwareMap);
       robot.RunWithEncoders();
       try{
           gyroSensorComponents = ((FtcRobotControllerActivity) hardwareMap.appContext).getGyroSensorComponents();
       }catch(Exception e){}
       waitForStart();
       int opState = 0;
       while(opModeIsActive()) {
           telemetry.addData("X", gyroSensorComponents.rotationData[0]);
           telemetry.addData("Y", gyroSensorComponents.rotationData[1]);
           telemetry.addData("Z", gyroSensorComponents.rotationData[2]);
           telemetry.update();

           double initialHeading;

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(30, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-30, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(2000);

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(45, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-45, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(2000);

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(90, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-90, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(2000);

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(120, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-120, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(2000);

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(135, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-135, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(200);

           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(180, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);
           initialHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
           turn(-180, initialHeading, gyroSensorComponents, phoneOrientation);
           sleep(1000);



           /*
           switch(opState) {
               case 0:
                   drive(6);
                   opState++;
                   break;
               case 1:
                   currentHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
                   turn(90, currentHeading, gyroSensorComponents, phoneOrientation);
                   opState++;
                   break;
               case 2:
                   drive(6);
                   opState++;
                   break;
               case 3:
                   currentHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
                   turn(5, currentHeading, gyroSensorComponents, phoneOrientation);
                   opState++;
                   break;
               case 4:
                   drive(6);
                   opState++;
                   break;
               case 5:
                   currentHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
                   turn(270, currentHeading, gyroSensorComponents, phoneOrientation);
                   opState++;
                   break;
               case 6:
                   drive(6);
                   opState++;
                   break;

           }
*/
           idle(); // Always call idle() at the bottom
       }
    }

    static void drive(int inches)   {
        int targetTicks = robot.get_ticks_degrees(inches);
        robot.reset_drive_encoders();
        while(!robot.have_encoders_reached(targetTicks))    {
            robot.set_drive_power(DRIVE_POWER);
        }
        robot.set_drive_power(0.0);
        robot.reset_drive_encoders();
    }

    static final double DRIVE_POWER = 0.5;
    //Degrees to turn. Direction indicated by sign. Postive is Clockwise
    //initial Heading in degrees from 0-360
    static void turn(double degrees, double initHeading, GyroSensorComponents gyroSensorComponents, Orientation phoneOrientation)    {
        if(degrees < 180)   {
            degrees += 360;
        }
        else    {
            degrees -= 360;
        }

        double currHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);

        if(degrees <= 0)    {   //Turn CCW
            while(Math.abs(currHeading - initHeading) > degrees)    {
                robot.set_drive_power(-DRIVE_POWER, DRIVE_POWER);
                currHeading = getCurrentHeading(gyroSensorComponents, phoneOrientation);
            }
        }
        else    { //Turn CW
            while(Math.abs(currHeading - initHeading) < degrees)    {
                robot.set_drive_power(DRIVE_POWER, -DRIVE_POWER);
                getCurrentHeading(gyroSensorComponents, phoneOrientation);
            }
        }
        robot.set_drive_power(0.0);
    }

    static double getCurrentHeading(GyroSensorComponents gyroSensorComponents, Orientation phoneOrientation)   {
        double currentHeading;
        if(phoneOrientation == Orientation.PERPENDICUALAR) {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[1]);
        }
        else {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[2]);
        }
        return currentHeading;
    }

    static double geoVectorToDegrees(double sinThetaOverTwo)    {
        double theta = 2 * Math.asin(sinThetaOverTwo);
        if (theta < 0.0) {
            theta += 360;
        }
        return theta;
    }
}

//use initail then as follows for each individual turn
//lower values <180 add 360 ex 5 becomes 365 then all values inside loop have 360 added as long as they remain below 180
//higher values >180 subtract 360 ex 270 becomes -90 all values inside loop subtract 360 as long as they remain above 180