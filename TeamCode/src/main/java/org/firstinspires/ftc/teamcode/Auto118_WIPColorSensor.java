package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Auto118_WIPColorSensor", group="Auto118")

public class Auto118_WIPColorSensor extends LinearOpMode {

    public enum Color {RED, BLUE};

    public Color teamColor = Color.RED;

    HardwareBot robot = new HardwareBot();

   int opState = 1;

   @Override
    public void runOpMode() throws InterruptedException{
       robot.init(hardwareMap);
       robot.RunWithEncoders();
       waitForStart();
       while(opModeIsActive()) {
           if (opState == 1)   //Launch 2 balls - Works. Don't touch.
           {
               robot.shooter.setPower(1);
               telemetry.addData("shoot 1", 1);
               robot.printTelemetry(telemetry);
               sleep(1450);
               robot.shooter.setPower(0);
               robot.queue.setPosition(.5);
               sleep(1000);
               robot.queue.setPosition(0);
               robot.shooter.setPower(1);
               telemetry.addData("shoot 2", 2);
               robot.printTelemetry(telemetry);
               sleep(1450);
               robot.shooter.setPower(0);

               sleep(6100);
               opState++;
           }

           if(opState == 2) {
               while (!robot.have_encoders_reset()) {
                   robot.reset_drive_encoders();

               }
               robot.RunWithEncoders();
               final int pos1 = 30;
               telemetry.addData("Robot get Ticks", robot.getNumTicks(pos1));
               telemetry.update();

               while (!robot.have_encoders_reached(robot.getNumTicks(pos1))) {
                   robot.set_drive_power(1);

                   telemetry.addData("Robot get Ticks", robot.getNumTicks(pos1));
                   telemetry.addData("Right ticks", robot.r$front.getCurrentPosition());
                   telemetry.addData("Left ticks", robot.l$front.getCurrentPosition());
                   telemetry.addData("Tick Goal Reached", robot.have_encoders_reached(robot.getNumTicks(pos1)));
                   telemetry.update();
               }
               robot.set_drive_power(0);

               doColorSensor();

               opState++;
           }
           robot.printTelemetry(telemetry);
           telemetry.update();
           idle(); // Always call idle() at the bottom
       }
    }

    void doColorSensor()    {
        int leftRed = 0;
        int rightRed = 0;
        robot.leftSensorOn.setState(true);
        leftRed = robot.leftSensor.red();
        robot.leftSensorOn.setState(false);
        robot.rightSensorOn.setState(true);
        rightRed = robot.rightSensor.red();
        robot.rightSensorOn.setState(false);

        if(leftRed < rightRed)  {
            switch (teamColor)  {
                case RED:

            }

        }

    }
}
