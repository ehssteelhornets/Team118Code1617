package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


@Autonomous(name="Auto118_EXPERIMENTAL", group="Auto118")

public class Auto118_EXPERIMENTAL extends AutoMethods {

    int opState = 1;
    @Override
    public void init() {
        super.init();
    }

    public double[] drivePower = new double[2];

    public int k;

    @Override
    public void loop() {

        if (opState % 2 == 0) {
            reset_drive_encoders();
            if (have_encoders_reset()) {
                opState++;
            }
        }
        switch (opState) {

            case 1: //launch 2 balls
                shooter.setPower(1);
                busySleep(1800);
                shooter.setPower(0);
                queue.setPosition(0);
                shooter.setPower(1);
                busySleep(1800);
                shooter.setPower(0);
                opState++;

            case 3:
                busySleep(10000);

            case 5:
                set_drive_power(-1.0);
                if (have_encoders_reached(getNumTicks(78))) {
                    set_drive_power(0);
                    opState++;
                }
                break;
            
            case 7:
                set_drive_power(0);
                break;
        }
    }
}
