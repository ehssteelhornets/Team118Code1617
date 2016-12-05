package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


@Autonomous(name="Auto118_1", group="Auto118")

public class Auto118_1 extends AutoMethods {

    @Override
    public void init() {
        super.init();
    }

    public double[] drivePower = new double[2];



    int opState = 1;

    @Override
    public void loop() {

        if (opState == 1) {
            RunWithEncoders();
            telemetry.addData("1", 1);
            shooter.setPower(1);
            busySleep(1800000000);
            shooter.setPower(0);
            queue.setPosition(.5);
            busySleep(500000000);
            queue.setPosition(0);
            shooter.setPower(1);
            busySleep(1800000000);
            shooter.setPower(0);
            telemetry.addData("2", 2);
            busySleep(1000);

            opState++;
        }
        printTelemetry();
    }
}
