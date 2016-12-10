package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


@Autonomous(name="Auto118_EXPERIMENTAL", group="Auto118")

public class Auto118_EXPERIMENTAL extends AutoMethods {

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
            busySleep(2000000000);
            shooter.setPower(0);
            queue.setPosition(.5);
            busySleep(800000000);
            queue.setPosition(0);

            shooter.setPower(1);
            telemetry.addData("shoot 2", 2);
            printTelemetry();
            busySleep(180000000);
            shooter.setPower(0);
            telemetry.addData("Checkpoint 1", 1);
            int k = 0;
            while (!have_encoders_reset()) {
                reset_drive_encoders();

            }
            telemetry.addData("Checkpoint 2", 2);
            while (!have_encoders_reached(getNumTicks(70))) {
                printTelemetry();
                r$front.setPower(1);
                r$rear.setPower(1);

                l$front.setPower(1);
                l$rear.setPower(1);

                telemetry.addData("Tick Goal Reached", have_encoders_reached(getNumTicks(70)));
                printTelemetry();
            }
            r$front.setPower(0);
            r$rear.setPower(0);

            l$front.setPower(0);
            l$rear.setPower(0);

            set_drive_power(0);
            telemetry.addData("Checkpoint 3", 3);
            opState ++;

        }

        printTelemetry();

    }
}
