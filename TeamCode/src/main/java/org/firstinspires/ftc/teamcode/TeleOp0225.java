package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp0225", group="TeleOp118")
public class TeleOp0225 extends HardwareMethods118_1617 {
    boolean armdown = false;

    @Override
    public void init() {
       super.init();
    }

    @Override
    public void start() {
        LEDs.setPower(1.0);
    }

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
        if (gamepad1.left_trigger != 0)
            precisionMode = true;
        boolean reverseMode = false;
        if (gamepad1.right_trigger != 0)
            reverseMode = true;
        drive(precisionMode, reverseMode);


        //Left Button Pusher
        //Left Button Pusher
        if (lPusher != null) {
            if (gamepad1.left_bumper) {
                lPusher.setPosition(lServoDown);

            }else {
                lPusher.setPosition(lServoUp);

            }
            //busySleep(500);
        }


            //Right Button Pusher
            if (rPusher != null) {
                if (gamepad1.right_bumper) {
                    // rPusherDown ^= true;
                    // if (rPusherDown) {
                    rPusher.setPosition(rServoDown);
                } else {
                    rPusher.setPosition(rServoUp);
                }
                //busySleep(500);
            }

            if (release != null) {
                if (gamepad1.b && gamepad1.x) {
                    release.setPosition(releaseDown);
                    releaseTog = true;
                }
                if (releaseTog)
                {
                    if (gamepad1.dpad_down)
                    {
                        release.setPosition(releaseUp);
                        releaseTog = false;
                    }
                }
            }


/**
 *  Controller 2
 *  -Shooting motor - right trigger
 *  -Elevator and Intake - left Trigger and y
 *  -Queue servos - left bumper
 *  -
 */

            if (shooter != null) {
                if (gamepad2.a) {
                    if (gamepad2.right_trigger != 0) {
                        shooter.setPower(-.1);
                    } else {
                        shooter.setPower(0);
                    }
                } else {
                    if (gamepad2.right_trigger != 0) {
                        shooter.setPower(1);
                    } else {
                        shooter.setPower(0);
                    }
                }
            }

            if (elevator != null) {
                if (gamepad2.left_trigger != 0)
                    elevator.setPower(-1);
                else if (gamepad2.y)
                    elevator.setPower(1);
                else
                    elevator.setPower(0);

            }
            if (intake != null) {
                if (gamepad2.left_trigger != 0)
                    intake.setPower(1);
                else if (gamepad2.y)
                    intake.setPower(-1);
                else
                    intake.setPower(0);
            }

            if (queue != null) {
                if (gamepad2.left_bumper) {
                    queue.setPosition(0.5);

                } else {
                    queue.setPosition(0);
                }
            }
            printTelemetry();
        }



    static void drive(boolean precise, boolean reverse) {
        double right_scaled = scaleMotor(right,precise);
        double left_scaled = scaleMotor(left,precise);

        if(reverse) {
            double temp = right_scaled;
            right_scaled = -left_scaled;
            left_scaled = -temp;
        }

        r$front.setPower(right_scaled);
        r$rear.setPower(right_scaled);

        l$front.setPower(left_scaled);
        l$rear.setPower(left_scaled);
    }



}
