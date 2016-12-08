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
    public void set_drive_power(double pwr) {
        r$front.setPower(pwr);
        r$rear.setPower(pwr);

        l$front.setPower(pwr);
        l$rear.setPower(pwr);
    }
    @Override
    public void loop() {
            telemetry.addData(""+opState,0);
        switch(opState) {

            case 1:     //Launch 2 balls - Works. Don't touch.
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
                busySleep(10000);
                telemetry.addData("Checkpoint 1", 1);

                while (!have_encoders_reset())  {
                    reset_drive_encoders();
                    opState++;
                }
                break;

            case 2:

                while (!have_encoders_reached(getNumTicks(70))) {
                    set_drive_power(1);
                }
                set_drive_power(0);
                opState++;
                telemetry.addData("Tick Goal Reached", hsvr);

                telemetry.addData("Checkpoint 2", 2);

                break;

            case 3:
                set_drive_power(0);
                telemetry.addData("Checkpoint 3", 3);
                break;

        }

        printTelemetry();

    }
}
