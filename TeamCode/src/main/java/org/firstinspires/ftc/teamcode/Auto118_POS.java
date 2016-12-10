package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


@Autonomous(name="Auto118_POS", group="Auto118")

public class Auto118_POS extends AutoMethods {

    @Override
    public void init() {
        super.init();
    }

    int opState = 1;
    @Override
    public void loop() {
        telemetry.addData(""+opState,0);
        if (opState == 1)   //Launch 2 balls - Works. Don't touch.
        {
            shooter.setPower(1);
            telemetry.addData("shoot 1", 1);
            printTelemetry();
            busySleep(1800000000);
            shooter.setPower(0);
            queue.setPosition(.5);
            busySleep(800000000);
            queue.setPosition(0);

            shooter.setPower(1);
            telemetry.addData("shoot 2", 2);
            printTelemetry();
            busySleep(1800000000);
            shooter.setPower(0);
            busySleep(80000000);
            telemetry.addData("Checkpoint 1", 1);
            int k = 0;
            RunToPosition();
            SetTargetPosition(getNumTicks(70));

            opState ++;
        }
        else if (opState == 2) {
            if (getPos() == getNumTicks(70)) {
                set_drive_power(0);
                opState ++;
            }
            else {
                set_drive_power(1);
            }

        }

        printTelemetry();

    }
}
