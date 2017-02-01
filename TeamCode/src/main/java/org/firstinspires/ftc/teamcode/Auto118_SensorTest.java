package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Auto118_SensorTest", group="Auto118")

public class Auto118_SensorTest extends LinearOpMode {
    HardwareBot robot = new HardwareBot();

   @Override
    public void runOpMode() throws InterruptedException{
       robot.init(hardwareMap);
       robot.RunWithEncoders();
       waitForStart();
       while(opModeIsActive()) {

               int red1 = robot.leftSensor.red();
               telemetry.addData("left Sensors", red1);

               int red2 = robot.rightSensor.red();
               telemetry.addData("right Sensors", red2);
               telemetry.update();



           robot.printTelemetry(telemetry);
           telemetry.update();
       }
    }
}
