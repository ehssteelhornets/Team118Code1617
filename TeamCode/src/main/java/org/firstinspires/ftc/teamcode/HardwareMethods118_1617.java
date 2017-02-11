package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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

    static DcMotor shooter;
    static DcMotor intake;
    static DcMotor elevator;
    static Servo queue;

    static double right;
    static double left;

    static Servo lPusher;
    static Servo rPusher;
    static Servo scooper;

    public Servo release;
    public static final double releaseUp = 1.0;
    public static final double releaseDown = .75;


    static ColorSensor leftSensor;
    static ColorSensor rightSensor;
    static DeviceInterfaceModule cdim;
    static DigitalChannel leftSensorOn;
    static DigitalChannel rightSensorOn;

    static boolean precisionMode = false;


    static boolean lPusherDown = false;
    static boolean rPusherDown = false;

    static final double lServoDown = .80;//
    static final double rServoDown= .30;
    static final double lServoUp = .30;
    static final double rServoUp = .80;
    static int scooperIndex = 2; // Start With Scoop Up Ready to Deploy


    @Override
    public void loop()  {
        //Meant to be overridden
    }

    /**
     *  This init method has fail-safes and should be used in all programs to ensure that the hardware configuration is the same
     */
    @Override
    public void init() {

        r$rear = hardwareMap.dcMotor.get("rrear");
        r$front = hardwareMap.dcMotor.get("rfore");
        l$front = hardwareMap.dcMotor.get("lfore");
        l$rear = hardwareMap.dcMotor.get("lrear");

        r$rear.setDirection(DcMotor.Direction.REVERSE);
        r$front.setDirection(DcMotor.Direction.REVERSE);

        try {
            elevator = hardwareMap.dcMotor.get("elevator");
        }
        catch (Exception e) {
            elevator = null;
        }

        try {
            shooter = hardwareMap.dcMotor.get("Shooter");
        }
        catch (Exception e) {
            shooter = null;
        }

        try {
            intake = hardwareMap.dcMotor.get("intake");
            intake.setDirection(DcMotor.Direction.REVERSE);
        }
        catch (Exception e) {
            intake = null;
        }

        try {
            queue = hardwareMap.servo.get("Queue");
            queue.setPosition(0);
        }
        catch (Exception e) {
            queue = null;
        }

        try {
            release = hardwareMap.servo.get("release");
            release.setPosition(releaseUp);
        }
        catch (Exception e) {
            release = null;
        }

        try {
            lPusher = hardwareMap.servo.get("lPusher");
            lPusher.setPosition(lServoUp);
        }
        catch (Exception e)    {
            lPusher = null;
        }

        try {
            rPusher = hardwareMap.servo.get("rPusher");
            rPusher.setPosition(rServoUp);
        }
        catch (Exception e) {
            rPusher = null;
        }

        try {
            leftSensor = hardwareMap.colorSensor.get("leftRGB");
            rightSensor = hardwareMap.colorSensor.get("rightRGB");
            cdim = hardwareMap.deviceInterfaceModule.get("DIM");
        }
        catch (Exception e) {
            leftSensor = null;
            rightSensor = null;
            cdim = null;
        }

        try{
            leftSensorOn.setMode(DigitalChannelController.Mode.OUTPUT);
            rightSensorOn.setMode(DigitalChannelController.Mode.OUTPUT);
            leftSensorOn.setState(false);
            rightSensorOn.setState(false);
        }
        catch(Exception e) {

        }

    }

    /**
     * Sleep method for debounce and responsiveness purposes
     * @param nanos - Time to sleep in nanoseconds
     */
    public static void busySleep(long milis)
    {
     //Need to inplement

    }

    /** Scales the raw joystick input into granular speeds to improve driving control
     *
     * @param num - the joystick value from the controller
     * @param precise - whether or not the button for precision mode is pressed
     * @return the scaled granular motor power
     */
    static double scaleMotor(double num, boolean precise) {
        if (num == 0.0)
            return 0.0;
        //For precision mode
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

//Debugging

    void printTelemetry(){
        telemetry.addData("RMotor Power", TeleOp1020.right_scaled);
        telemetry.addData("LMotor power", TeleOp1020.left_scaled);
        telemetry.addData("LServo",lPusherDown);
        telemetry.addData("RServo", rPusherDown);
        telemetry.addData("Launcher power", shooter.getPower());
        telemetry.addData("Elevator power", elevator.getPower());
        telemetry.addData("Intake power", intake.getPower());
    }

}
