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
        //Get Inputs from a controller
        right = gamepad1.right_stick_y;
        left = gamepad1.left_stick_y;

        drive(gamepad1.right_bumper);

        //Left Button Pusher
        if (gamepad1.a){
            lPusherDown ^= true;
            if(lPusherDown){
                lPusher.setPosition(lServoDown);
            }
            else{
                lPusher.setPosition(lServoUp);
            }
            busySleep(500);
        }

        //Right Button Pusher
        if(gamepad1.b){
            rPusherDown ^= true;
            if(rPusherDown){
                rPusher.setPosition(rServoDown);
            }
            else{
                rPusher.setPosition(rServoUp);
            }
            busySleep(500);
        }

        if(gamepad1.right_trigger > 0.1) {
            shooter.setPower(1);
        }
        else {
            shooter.setPower(0);
        }

        if(gamepad1.left_trigger > 0.1) {
            elevator.setPower(1);
        }
        else {
            elevator.setPower(0);
        }

        if(gamepad1.left_bumper) {
            queue.setPosition(.4);
        }
        else {
            queue.setPosition(1);
        }

        if(gamepad1.y) {
            intake.setPower(1);
        }
        else {
            intake.setPower(0);
        }
        /*
        if(gamepad1.y){
            scooperIndex ++;
            scooper.setPosition(scooperPos(scooperIndex));
            busySleep(500);
        }

        //
        if(gamepad1.x){
            scooperIndex --;
            scooper.setPosition(scooperPos(scooperIndex));
            busySleep(500);
        }
        */
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
