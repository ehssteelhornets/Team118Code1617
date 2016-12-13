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
              //Launch 2 balls - Works. Don't touch.

               robot.leftSensorOn.setState(true);
               int red1 = robot.leftSensor.red();
               telemetry.addData("left Sensors", red1);
               robot.leftSensorOn.setState(false);

               robot.rightSensorOn.setState(true);
               int red2 = robot.rightSensor.red();
               telemetry.addData("right Sensors", red2);
               robot.rightSensorOn.setState(false);
               telemetry.update();



           robot.printTelemetry(telemetry);
           telemetry.update();
       }
    }
}
