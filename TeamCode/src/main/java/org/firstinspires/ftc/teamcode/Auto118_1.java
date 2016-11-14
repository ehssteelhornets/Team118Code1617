package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;


@Autonomous(name="Auto118_1", group="Auto118")

public class Auto118_1 extends AutoMethods {

    int opState = 1;
    public char side;
    @Override
    public void init() {
        super.init();
    }
    public double[] drivePower = new double[2];


    @Override
    public void loop() {

        if (opState % 2 == 0) {
            reset_drive_encoders();
            if (have_encoders_reset()){
                opState ++;
            }
        }
        switch (opState) {


            case 1: //drive forward 38 inches
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(38))) {
                    set_drive_power(0);
                    opState++;
                }

                break;

            case 3: //turn left

                int degrees = 90 % 360;
                if (degrees > 180) {

                    drivePower[0] = 1.0;
                    drivePower[1] = -1.0;

                } else {

                    drivePower[0] = -1.0;
                    drivePower[1] = 1.0;

                }

                int ticks = get_ticks_degrees(degrees);
                set_drive_power(drivePower[0], drivePower[1]);
                if (have_encoders_reached(ticks)) {

                    set_drive_power(0);
                    opState ++;

                }
                break;

            case 5: // 46 forward
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(46))) {
                    set_drive_power(0);
                    opState++;
                }

                break;

            case 7: //check color
                break;

            case 9: //pushers down
                pusher_toggle(side);
                break;

            case 11: //some forward
                break;

            case 13: //some back
                break;

            case 15: //pushers up
                pusher_toggle(side);
                break;

            case 17: //turn right
                reset_drive_encoders();
                degrees = 270;

                if (degrees > 180) {

                    drivePower[0] = 1.0;
                    drivePower[1] = -1.0;

                } else {

                    drivePower[0] = -1.0;
                    drivePower[1] = 1.0;

                }

                ticks = get_ticks_degrees(degrees);
                set_drive_power(drivePower[0], drivePower[1]);
                if (have_encoders_reached(ticks)) {

                    set_drive_power(0);
                    opState ++;

                }
                break;

            case 19: //38 forward
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(38))) {
                    set_drive_power(0);
                    opState++;
                }

                break;

            case 21: //turn left

                degrees = 90 % 360;
                if (degrees > 180) {

                    drivePower[0] = 1.0;
                    drivePower[1] = -1.0;

                } else {

                    drivePower[0] = -1.0;
                    drivePower[1] = 1.0;

                }

                ticks = get_ticks_degrees(degrees);
                set_drive_power(drivePower[0], drivePower[1]);
                if (have_encoders_reached(ticks)) {

                    set_drive_power(0);
                    opState ++;

                }
                break;

            case 23: //check color
                break;

            case 25: //pusher down
                pusher_toggle(side);
                break;

            case 27: //some forward
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(2))) {
                    set_drive_power(0);
                    opState++;
                }
                break;

            case 29: //some back
                set_drive_power(-1.0);
                if (have_encoders_reached(getNumTicks(2))) {
                    set_drive_power(0);
                    opState++;
                }
                break;

            case 31: //pushers up
                pusher_toggle(side);
                break;

            case 33: //5 back
                set_drive_power(-1.0);
                if (have_encoders_reached(getNumTicks(5))) {
                    set_drive_power(0);
                    opState++;
                }
                break;

            case 35: //turn left

                degrees = 90 % 360;
                if (degrees > 180) {

                    drivePower[0] = 1.0;
                    drivePower[1] = -1.0;

                } else {

                    drivePower[0] = -1.0;
                    drivePower[1] = 1.0;

                }

                ticks = get_ticks_degrees(degrees);
                set_drive_power(drivePower[0], drivePower[1]);
                if (have_encoders_reached(ticks)) {

                    set_drive_power(0);
                    opState ++;

                }
                break;

            case 37: //72 forward
                set_drive_power(1.0);
                if (have_encoders_reached(getNumTicks(38))) {
                    set_drive_power(0);
                    opState++;
                }
                break;
            }


    }


    }



