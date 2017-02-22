package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;

@Autonomous(name="Auto118_GyroTest0221", group="Auto118")

public class Auto118_GyroTest0221 extends LinearOpMode {
    HardwareBot robot = new HardwareBot();
    float[] data = new float[3];
    GyroSensorComponents gyroSensorComponents = null;
    //With Rear Camera Facing Forward
    //Rock Back and Forth is around X axis Tangental to ground 0 is approxamate East
    //Twist/pan is around y axis Tangental to ground 0 is Geomagnetic North
    //Tilt Side to side around z axis  perpendicular to ground plane 0 is screen skyward

    //With Rear Camera Facing Ground
    //Pitch is Around X Axis running from left to right of screen
    //Roll is Around Y Axis running from bottom to top of screen
    //Yaw is Around Z Axis which is the normal vector of the screen (Perpendicular)
    public enum Orientation {TANGENTAL, PERPENDICUALAR}
    public Orientation phoneOrientation = Orientation.PERPENDICUALAR;

   int opState = 1;
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
           idle(); // Always call idle() at the bottom
       }
    }
}
