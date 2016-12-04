package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp1020", group="TeleOp118")
public class TeleOp1020 extends HardwareMethods118_1617 {

    @Override
    public void init() {
       super.init();
    }

    @Override
    public void start() {}

    @Override

    public void loop() {


/**
 *  Controller 1
 *  -Driving - sticks, left trigger
 *  -Button Pushers - bumpers
  */
        //Driving
        right = gamepad1.right_stick_y;
        left = gamepad1.left_stick_y;
        boolean precisionMode = false;
        if(gamepad1.left_trigger != 0)
            precisionMode = true;
        drive(precisionMode);

        //Left Button Pusher
        if(lPusher != null) {
            if (gamepad1.right_bumper) {
                lPusherDown ^= true;
                if (lPusherDown) {
                    lPusher.setPosition(lServoDown);
                } else {
                    lPusher.setPosition(lServoUp);
                }
                busySleep(500);
            }
        }

        //Right Button Pusher
        if(rPusher != null){
            if (gamepad1.left_bumper) {
                rPusherDown ^= true;
                if (rPusherDown) {
                    rPusher.setPosition(rServoDown);
                } else {
                    rPusher.setPosition(rServoUp);
                }
                busySleep(500);
            }
        }

/**
 *  Controller 2
 *  -Shooting motor - right trigger
 *  -Elevator and Intake - left Trigger and y
 *  -Queue servos - left bumper
 *  -
  */

        if(shooter != null) {
            if (gamepad2.right_trigger != 0) {
                shooter.setPower(1);
            } else {
                shooter.setPower(0);
            }
        }

        if(elevator != null) {
            if (gamepad2.left_trigger != 0) {
                if(gamepad2.y)
                    elevator.setPower(-1);
                else
                    elevator.setPower(1);
            } else {
                elevator.setPower(0);
            }
        }
        if(intake != null) {
            if (gamepad2.left_trigger != 0) {
                if(gamepad2.y)
                    intake.setPower(-1);
                else
                    intake.setPower(1);
            } else {
                intake.setPower(0);
            }
        }

        if(queue != null) {
            if (gamepad2.left_bumper) {
                queue.setPosition(0.5);

            } else {
                queue.setPosition(0);
            }
        }



        printTelemetry();
    }

    static double right_scaled;
    static double left_scaled;
    static void drive(boolean precise) {
        right_scaled = scaleMotor(right,precise);
        left_scaled = scaleMotor(left,precise);

        r$front.setPower(right_scaled);
        r$rear.setPower(right_scaled);

        l$front.setPower(left_scaled);
        l$rear.setPower(left_scaled);
    }



}
