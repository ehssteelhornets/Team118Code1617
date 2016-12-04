package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOpEXP", group="TeleOp118")

public class TeleOpEXP extends HardwareMethods118_1617 {
/*
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

        //
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

        if(gamepad1.y){
            scooperIndex ++;
            scooper.setPosition(scooperPos(scooperIndex));
            busySleep(500);
        }
        if(gamepad1.x){
            scooperIndex --;
            scooper.setPosition(scooperPos(scooperIndex));
            busySleep(500);
        }

//        r$front.setPower(right_scaled);
//        r$rear.setPower(right_scaled);
//
//        l$front.setPower(left_scaled);
//        l$rear.setPower(left_scaled);

        printTelemetry();
    }

    static void drive(boolean precise) {
        right_scaled = scaleMotor(right,precise);
        left_scaled = scaleMotor(left,precise);

        r$front.setPower(right_scaled);
        r$rear.setPower(right_scaled);

        l$front.setPower(left_scaled);
        l$rear.setPower(left_scaled);
    }

*/

}
