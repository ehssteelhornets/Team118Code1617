package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Auto118_POS", group="Auto118")

public class Auto118_POS extends LinearOpMode {
    HardwareBot robot = new HardwareBot();

    int opState = 1;
    @Override
    public void runOpMode() throws InterruptedException{
        robot.init(hardwareMap);
        robot.RunToPosition();
        waitForStart();
        while(opModeIsActive()) {
            if (opState == 1)   //Launch 2 balls - Works. Don't touch.
            {
                robot.shooter.setPower(1);
                telemetry.addData("shoot 1", 1);
                robot.printTelemetry(telemetry);
                sleep(1500);
                robot.shooter.setPower(0);
                robot.queue.setPosition(.5);
                sleep(1000);
                robot.queue.setPosition(0);
                robot.shooter.setPower(1);
                telemetry.addData("shoot 2", 2);
                robot.printTelemetry(telemetry);
                sleep(1500);
                robot.shooter.setPower(0);

                sleep(10000);
                while (!robot.have_encoders_reset()) {
                    robot.reset_drive_encoders();

                }
                robot.SetTargetPosition(robot.getNumTicks(70));// may need to go before run to position??
                robot.RunToPosition();
                idle();


                opState++;
            }
            robot.printTelemetry(telemetry);
            telemetry.update();
        }
    }
}
