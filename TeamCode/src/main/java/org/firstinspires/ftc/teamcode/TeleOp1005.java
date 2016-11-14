

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp1005", group="TeleOp118")
public class TeleOp1005 extends HardwareMethods118_1617 {



    public static void busySleep(long nanos)
    {
        long elapsed;
        final long startTime = System.nanoTime();
        do {
            elapsed = System.nanoTime() - startTime;
        } while (elapsed < nanos);
    }



    @Override
    public void init() {
       super.init();
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

        //Get Inputs from a controller
        right = gamepad1.right_stick_y;
        left = gamepad1.left_stick_y;
        precisionMode = gamepad1.right_bumper;


        if(precisionMode)   {
            right_scaled = preciseMotor(right);
            left_scaled = preciseMotor(left);
        }
        else    {
            right_scaled = scaleMotor(right);
            left_scaled = scaleMotor(left);
        }

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



        r$front.setPower(right_scaled);
        r$rear.setPower(right_scaled);

        l$front.setPower(left_scaled);
        l$rear.setPower(left_scaled);

        telemetry.addData("RMotor Power", right_scaled);
        telemetry.addData("Lmotor power", left_scaled);

        telemetry.addData("LServo",lPusherDown);
        telemetry.addData("RServo", rPusherDown);
        telemetry.addData("Scoop",scooperPos(scooperIndex));

        //telemetry.addData("Scale LMotor", scaleMotor(left));
        //telemetry.addData("Precise LMotor", preciseMotor(left));

    }


    double scaleMotor(double num) {
        if(num == 0.0)
            return 0.0;

        double[] scaleArray = {0.2, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00};
        // get the corresponding index for the scaleInput array.
        int index = (int) (num * 15);
        index = Math.abs(index);

        double scaled = 0.0;
        if (num < 0)
            scaled = -scaleArray[index];
        else
            scaled = scaleArray[index];

        return scaled;
    }

    double preciseMotor(double num) {
        if(num == 0.0)
            return 0.0;
        double[] scaleArray = {0.01, 0.2, 0.04, 0.06, 0.09, 0.12, 0.15, 0.18};
        int index = (int) (num * 7);
        index = Math.abs(index);

        double scaled = 0.0;
        if (num < 0)
            scaled = -scaleArray[index];
        else
            scaled = scaleArray[index];

        return scaled;
    }
}
