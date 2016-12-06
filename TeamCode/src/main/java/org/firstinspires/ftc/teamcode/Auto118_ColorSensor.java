package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="Auto118_ColorSensor", group="Auto118")

public class Auto118_ColorSensor extends AutoMethods {

    @Override
    public void init() {
        super.init();
    }

   int opState = 1;

    @Override
    public void loop() {

        if (opState % 2 == 0) {
            reset_drive_encoders();
            if (have_encoders_reset()) {
                opState++;
            }
        }

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
                opState++;
                telemetry.addData("Checkpoint 1", 1);
                break;

            case 3:
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(70))) {
                    set_drive_power(0);
                    opState++;
                }
                telemetry.addData("Checkpoint 2", 2);

                break;

            case 5:
                set_drive_power(0);
                telemetry.addData("Checkpoint 3", 3);
                break;

        }

        printTelemetry();

    }
}
