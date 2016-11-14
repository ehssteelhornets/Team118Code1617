package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Steel Hornets on 10/18/2016.
 */

public abstract class HardwareMethods118_1617 extends OpMode {

    static DcMotor r$rear;
    static DcMotor l$rear;
    static DcMotor r$front;
    static DcMotor l$front;

    static Servo lPusher;
    static Servo rPusher;
    static Servo scooper;

    static ColorSensor rgbSensor;
    static DeviceInterfaceModule cdim;

    static double right;
    static double left;
    static boolean precisionMode = false;
    static double right_scaled;
    static double left_scaled;

    static boolean lPusherDown = false;
    static boolean rPusherDown = false;

    static final double lServoDown = .80;
    static final double rServoDown= .15;
    static final double lServoUp = .15;
    static final double rServoUp = .80;
    static int scooperIndex = 2; // Start With Scoop Up Ready to Deploy
    static final double[] scooperPos = {1,0.5,0};

    @Override
    public void loop() {
        //Meant to be overridden
    }


    public static double scooperPos(int scooperIndex)
    {
        scooperIndex = Range.clip(scooperIndex,0,2);
        return scooperPos[scooperIndex];
    }
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
        r$rear = hardwareMap.dcMotor.get("rrear");
        r$front = hardwareMap.dcMotor.get("rfore");

        l$front = hardwareMap.dcMotor.get("lfore");
        l$rear = hardwareMap.dcMotor.get("lrear");

        lPusher = hardwareMap.servo.get("lPusher");
        rPusher = hardwareMap.servo.get("rPusher");
        scooper = hardwareMap.servo.get("scooper");

        scooper.setPosition(scooperPos(scooperIndex));

        lPusher.setPosition(lServoUp);
        rPusher.setPosition(rServoUp);

        r$rear.setDirection(DcMotor.Direction.REVERSE);
        r$front.setDirection(DcMotor.Direction.REVERSE);
    }

      void printTelemetry(){
         telemetry.addData("RMotor Power", right_scaled);
         telemetry.addData("LMotor power", left_scaled);
         telemetry.addData("LServo",lPusherDown);
         telemetry.addData("RServo", rPusherDown);
         telemetry.addData("Scoop",scooperPos(scooperIndex));
     }
     static double scaleMotor(double num, boolean precise) {
        if (num == 0.0)
            return 0.0;
        double[] scaleArray = {0.5, 0.75, 1.0};
        double[] preciseArray = {0.1, 0.2, 0.3};
        // get the corresponding index for the scaleInput array.
        int index = (int) (num * (scaleArray.length - 1));
        index = Math.abs(index);

        double scaled;
        if (precise)
            scaled = preciseArray[index];
        else
            scaled = scaleArray[index];
        if (num < 0.0)
            scaled = -scaled;

        return scaled;
    }


}
