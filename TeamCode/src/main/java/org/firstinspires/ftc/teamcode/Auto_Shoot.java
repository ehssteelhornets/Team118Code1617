package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Auto118_Shoot", group="Auto118")

public class Auto_Shoot extends LinearOpMode {
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
                opState++;
            }
            idle(); // Always call idle() at the bottom
        }
    }
}
