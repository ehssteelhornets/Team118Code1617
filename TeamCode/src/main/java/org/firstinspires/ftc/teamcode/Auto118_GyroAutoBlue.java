package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Auto118_GyroAutoBlue", group="Auto118")

public class Auto118_GyroAutoBlue extends LinearOpMode {
    //With Rear Camera Facing Forward
    //Rock Back and Forth is around X axis Tangental to ground 0 is approxamate East
    //Twist/pan is around y axis Perpindicular to ground 0 is Geomagnetic North
    //Tilt Side to side around z axis  Tangental to ground plane 0 is screen skyward

    //With Rear Camera Facing Ground
    //Pitch is Around X Axis running from left to right of screen
    //Roll is Around Y Axis running from bottom to top of screen
    //Yaw is Around Z Axis which is the normal vector of the screen (Perpendicular)
    private static int driven = 0;
    static HardwareBot robot = new HardwareBot();
   @Override
    public void runOpMode() throws InterruptedException{
       robot.init(hardwareMap);
       robot.RunWithEncoders();
       try{
           robot.gyroSensorComponents = ((FtcRobotControllerActivity) hardwareMap.appContext).getGyroSensorComponents();
       }catch(Exception e){}

       waitForStart();
       int opState = 1;
       robot.telem=telemetry;
       robot.teamColor = HardwareBot.Color.BLUE;//BLUE TEAM
       boolean leftRed = false;
       while(opModeIsActive()) {
           telemetry.addData("X", robot.gyroSensorComponents.rotationData[0]);
           telemetry.addData("Y", robot.gyroSensorComponents.rotationData[1]);
           telemetry.addData("Z", robot.gyroSensorComponents.rotationData[2]);
           telemetry.addData("Driven",driven);
           telemetry.update();

           switch(opState) {

               case 1:
                   robot.shooter.setPower(1);
                   sleep(1450);
                   robot.shooter.setPower(0);
                   robot.queue.setPosition(.5);
                   sleep(1000);
                   robot.queue.setPosition(0);
                   robot.shooter.setPower(1);
                   sleep(1450);
                   robot.shooter.setPower(0);
                   opState ++;
                   break;

               case 2:
                   robot.drive(70);
                   opState++;
                   break;
               case 3:
                   robot.initialHeading = robot.getCurrentHeading();
                   telemetry.addData("Init:", robot.initialHeading);
                   telemetry.update();
                   robot.turn(-135);
                   driven =1;
                   opState++;
                   break;
               case 4:
                   robot.drive(45);
                   opState++;
                   break;
               case 5://Check Color and lower arm
                   leftRed=robot.doColorSensor();
                   opState++;
                   break;
               case 6:
                   robot.drive(3); //adjust as needed
                   opState++;
                   break;
               case 7:
                   robot.drive(-6);
                   opState++;
                   break;
               case 8:
                   //pusher up
                   if(leftRed)
                       robot.lPusher.setPosition(robot.lServoUp);
                   else
                   robot.rPusher.setPosition(robot.rServoUp);
                   opState++;
                   break;
               case 9:
                   robot.turn(-90);
                   opState++;
                   break;
               case 10:
                   robot.drive(48);
                   opState++;
                   break;
               case 11:
                   robot.turn(90);
                   opState++;
                   break;
               case 12:
                   robot.drive(3);
                   opState++;
                   break;
               case 13:
                   //check color and lower arm
                   leftRed = robot.doColorSensor();
                   opState++;
                   break;
               case 14:
                   robot.drive(3);
                   opState++;
                   break;
               case 15:
                   robot.drive(-6);
                   opState++;
                   break;
               case 16:
                   if(leftRed)
                       robot.lPusher.setPosition(robot.lServoUp);
                   else
                       robot.rPusher.setPosition(robot.rServoUp);
                   opState++;
                   break;
               case 17:
                   robot.turn(135);
                   opState++;
                   break;
               case 18:
                   robot.drive(67);
                   opState++;
                   break;

           }

           idle(); // Always call idle() at the bottom
       }
    }
}