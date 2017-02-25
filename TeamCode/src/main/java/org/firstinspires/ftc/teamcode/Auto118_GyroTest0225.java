package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Auto118_GyroTest0225", group="Auto118")

public class Auto118_GyroTest0225 extends LinearOpMode {
    float[] data = new float[3];
    private static GyroSensorComponents gyroSensorComponents = null;
    //With Rear Camera Facing Forward
    //Rock Back and Forth is around X axis Tangental to ground 0 is approxamate East
    //Twist/pan is around y axis Perpindicular to ground 0 is Geomagnetic North
    //Tilt Side to side around z axis  Tangental to ground plane 0 is screen skyward

    //With Rear Camera Facing Ground
    //Pitch is Around X Axis running from left to right of screen
    //Roll is Around Y Axis running from bottom to top of screen
    //Yaw is Around Z Axis which is the normal vector of the screen (Perpendicular)
    public enum Orientation {TANGENTAL, PERPENDICUALAR}
    private static Orientation phoneOrientation = Orientation.PERPENDICUALAR;
    private static double initialHeading;
    private static Telemetry telem ;
    private static int driven = 0;
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
       telem=telemetry;
       while(opModeIsActive()) {
           telemetry.addData("X", gyroSensorComponents.rotationData[0]);
           telemetry.addData("Y", gyroSensorComponents.rotationData[1]);
           telemetry.addData("Z", gyroSensorComponents.rotationData[2]);
           telemetry.addData("Driven",driven);
           telemetry.update();

           switch(opState) {
               case 0:
                   drive(6);
                   opState++;
                   break;
               case 1:
                   initialHeading = getCurrentHeading();
                   telemetry.addData("Init:", initialHeading);
                   telemetry.update();
                   turn(90);
                   driven =1;
                   opState++;
                   break;
           }

           idle(); // Always call idle() at the bottom
       }
    }

    static void drive(int inches)   {
        int targetTicks = robot.getNumTicks(inches);
        robot.reset_drive_encoders();
        robot.RunWithEncoders();

        while(!robot.have_encoders_reached(targetTicks))    {
            robot.set_drive_power(-DRIVE_POWER);
        }
        robot.set_drive_power(0.0);
    }

    static final double DRIVE_POWER = 0.5;
    //Degrees to turn. Direction indicated by sign. Postive is Clockwise
    //initial Heading in degrees from 0-360
    static void turn(double degrees)    {

        if(initialHeading < 180)   {
            initialHeading += 360;
        }
        else    {
            initialHeading -= 360;
        }

        double currHeading = getCurrentHeading();
        if(currHeading < 180)   {
            currHeading += 360;
        }
        else    {
            currHeading -= 360;
        }

        if(degrees < 0)    {   //Turn CCW
            while(-Math.abs(currHeading - initialHeading) > degrees)    {
                robot.set_drive_power(DRIVE_POWER, -DRIVE_POWER);
                currHeading = getCurrentHeading();
                if(currHeading < 180)   {
                    currHeading += 360;
                }
                else    {
                    currHeading -= 360;
                }
                telem.addData("Init:", initialHeading);
                telem.addData("Heading", currHeading);
                telem.update();
            }
        }
        else    { //Turn CW
            while(Math.abs(currHeading - initialHeading) < degrees)    {
                robot.set_drive_power(-DRIVE_POWER, DRIVE_POWER);
                currHeading = getCurrentHeading();
                if(currHeading < 180)   {
                    currHeading += 360;
                }
                else    {
                    currHeading -= 360;
                }
                telem.addData("Init:", initialHeading);
                telem.addData("Heading", currHeading);
                telem.update();
            }
        }
        robot.set_drive_power(0.0);
    }

    static double getCurrentHeading()   {
        double currentHeading;
        if(phoneOrientation == Orientation.PERPENDICUALAR) {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[0]);
        }
        else {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[0]);//?
        }
        return currentHeading;
    }

    static double geoVectorToDegrees(double angle)    {
        double theta =  Math.toDegrees(angle);
        if (theta < 0.0) {
            theta += 360;
        }
        return theta;
    }
}

//use initail then as follows for each individual turn
//lower values <180 add 360 ex 5 becomes 365 then all values inside loop have 360 added as long as they remain below 180
//higher values >180 subtract 360 ex 270 becomes -90 all values inside loop subtract 360 as long as they remain above 180