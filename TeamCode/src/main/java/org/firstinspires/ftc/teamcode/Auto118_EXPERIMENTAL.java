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

    public double[] drivePower = new double[2];



   int opState = 1;

    @Override
    public void loop() {
        if (opState % 2 == 0) {
            telemetry.addData("2", 2);
            reset_drive_encoders();
            if (have_encoders_reset()) {
                opState++;
            }
        }

       switch (opState) {
           case 1:
               telemetry.addData("1", 1);
                shooter.setPower(1);
                busySleep(1800000000);
                shooter.setPower(0);
                queue.setPosition(.5);
                busySleep(250000000);
                shooter.setPower(1);
                busySleep(1800000000);
                shooter.setPower(0);
                queue.setPosition(0);
                opState++;
                busySleep(1000);

                break;

           case 3:
               telemetry.addData("3", 3);
                set_drive_power(1.0);
               telemetry.addData("4", 4);
                if(have_encoders_reached(getNumTicks(5))) {
                    telemetry.addData("5", 5);
                    set_drive_power(0);
                    opState++;
                }
                break;

           case 5:
               telemetry.addData("6", 6);
               set_drive_power(0);
               break;



       }
        printTelemetry();

    }
}
