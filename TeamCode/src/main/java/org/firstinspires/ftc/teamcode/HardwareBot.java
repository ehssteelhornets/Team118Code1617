package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.internal.GyroSensorComponents;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

/**
 * Created by Steel Hornets on 10/18/2016.
 */

public class HardwareBot  {

    public DcMotor r$rear;
    public DcMotor l$rear;
    public DcMotor r$front;
    public DcMotor l$front;

    public DcMotor shooter;
    public DcMotor intake;
    public DcMotor elevator;
    public Servo queue;
    public DcMotor LEDs;

    public Servo release;
    public static final double releaseUp = 1.0;
    public static final double releaseDown = .75;

    static double right;
    static double left;

    public Servo lPusher;
    public Servo rPusher;

    public ColorSensor leftSensor;
    public ColorSensor rightSensor;
    public DeviceInterfaceModule cdim;

    public boolean precisionMode = false;


    public boolean lPusherDown = false;
    public boolean rPusherDown = false;

    static final double lServoDown = .90;//
    static final double rServoDown= .0;
    static final double lServoUp = .40;
    static final double rServoUp = .50;

    static final double DRIVE_POWER = 0.5;

    public Color teamColor;
    public double initialHeading;
    public enum Orientation{TANGENTAL, PERPENDICULAR}
    public Orientation phoneOrientation = Orientation.PERPENDICULAR;

    public GyroSensorComponents gyroSensorComponents = null;

    public Telemetry telem;

    public static int scooperIndex = 2; // Start With Scoop Up Ready to Deploy


    public static MultiplexColorSensor muxColor;
    public static int[] ports = {0,1};

    public enum Color {RED,BLUE};

    HardwareMap hardwareMap= null;

    public HardwareBot()  {

    }

    /**
     *  This init method has fail-safes and should be used in all programs to ensure that the hardware configuration is the same
     */
    public void init(HardwareMap hardwareMap) {
        try {
            release = hardwareMap.servo.get("release");
            release.setPosition(releaseUp);
        }
        catch (Exception e) {
            release = null;
        }

        try {
            LEDs = hardwareMap.dcMotor.get("leds");
        }
        catch (Exception e) {
            LEDs = null;
        }
        try {
            LEDs.setPower(1);
        }
        catch (Exception e) {
            LEDs = null;
        }


        try {
            r$rear = hardwareMap.dcMotor.get("rrear");
            r$rear.setDirection(DcMotor.Direction.REVERSE);

        }
        catch (Exception e) {
            r$rear = null;

        }
        try {
            r$front = hardwareMap.dcMotor.get("rfore");
            r$front.setDirection(DcMotor.Direction.REVERSE);
        }
        catch (Exception e) {
            r$front = null;
        }

        try {
            l$front = hardwareMap.dcMotor.get("lfore");

        }
        catch (Exception e) {
            l$front = null;

        }

        try {

            l$rear = hardwareMap.dcMotor.get("lrear");
        }
        catch (Exception e) {

            l$rear = null;
        }

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
            cdim = hardwareMap.deviceInterfaceModule.get("DIM");
        }
        catch (Exception e) {
            cdim = null;
        }

        try {
            leftSensor = hardwareMap.colorSensor.get("leftRGB");
        }
        catch(Exception e)  {
            leftSensor = null;
        }

        try {
            rightSensor = hardwareMap.colorSensor.get("rightRGB");
        }
        catch(Exception e) {
            rightSensor = null;
        }

        try {
            int milliSeconds = 48;
            muxColor = new MultiplexColorSensor(hardwareMap, "mux", "ada",
                    ports, milliSeconds,
                    MultiplexColorSensor.GAIN_16X);
        }
        catch(Exception e) {

        }

    }



    /** Scales the raw joystick input into granular speeds to improve driving control
     *
     * @param num - the joystick value from the controller
     * @param precise - whether or not the button for precision mode is pressed
     * @return the scaled granular motor power
     */
    public static double scaleMotor(double num, boolean precise) {
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

    public void printTelemetry(Telemetry telemetry){
        telemetry.addData("RMotor Power", r$front.getPower());
        telemetry.addData("LMotor power", l$front.getPower());
        telemetry.addData("LServo",lPusherDown);
        telemetry.addData("RServo", rPusherDown);
        telemetry.addData("Launcher power", shooter.getPower());
        telemetry.addData("Elevator power", elevator.getPower());
        telemetry.addData("Intake power", intake.getPower());
    }

    /*Auto Methods Begin Here
    *
    *
     */
    final static double enc_CPR = 1260.31;  //Encoder ticks per revolution
    final static double wheelSize = 3.75;  //Diameter of the wheels or sprockets in inches

    /**
     * SET THE DRIVE POWER FOR AUTO
     *
     * @param pwr - power level of both motors
     */
    public void set_drive_power(double pwr) {
        set_drive_power(pwr,pwr);
    }
    /**
     * @param leftPwr  - power of left motor
     * @param rightPwr - power of right motor
     */
    public void set_drive_power(double leftPwr, double rightPwr) {
        if (r$front != null && r$rear != null) {
            r$front.setPower(rightPwr);
            r$rear.setPower(rightPwr);
        }
        if (l$front != null && l$rear != null) {

            l$front.setPower(leftPwr);
            l$rear.setPower(leftPwr);
        }
    }


    public int get_ticks_degrees(int degrees) {

        double percent = degrees / 360;
        int ticks = (int) (percent * enc_CPR);
        return ticks;
    }



    /**
     * @param distance - number of inches to travel
     * @return - number of encoder ticks to travel DISTANCE inches
     */
    public static int getNumTicks(int distance) {

        double rotations = distance / (Math.PI * wheelSize); //distance divided by the circumference of the wheel
        return (int) (enc_CPR * rotations); //Encoder ticks per revolution times by the number of rotations
    }

    public void pusher_toggle(char side) {
        if (side == 'l') {
            if (lPusher != null) {
                lPusherDown ^= true;
                if (lPusherDown) {
                    lPusher.setPosition(lServoDown);
                } else {
                    lPusher.setPosition(lServoUp);
                }
            }
        }
        else if (side == 'r') {
            if (rPusher != null) {
                rPusherDown ^= true;
                if (rPusherDown) {
                    rPusher.setPosition(rServoDown);
                } else {
                    rPusher.setPosition(rServoUp);
                }
            }
        }
    }

    /**
     * RESET THE ENCODERS OF BOTH DRIVE MOTORS
     */
    public void reset_drive_encoders() {
        if(l$front != null && l$rear != null) {

            l$front.setPower(0.0);
            l$rear.setPower(0.0);
        }

        if(r$front != null && r$rear != null) {
            r$front.setPower(0.0);
            r$rear.setPower(0.0);
        }
        reset_left_encoders();
        reset_right_encoders();
    }

    public void reset_left_encoders() {
        if(l$front != null && l$rear != null) {
            l$front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            l$rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void reset_right_encoders()
    {
        if(r$front != null && r$rear != null) {
            r$front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            r$rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }


    /**
     * SET THE MOTORS TO RUN WITH ENCODERS
     */
    public void RunWithEncoders() {
        if(r$front != null && r$rear != null) {
            r$front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            r$rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if(l$front != null && l$rear != null) {
            l$front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            l$rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void RunToPosition() {
        if(r$front != null && r$rear != null) {
            r$front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            r$rear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if(l$front != null && l$rear != null) {
            l$front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            l$rear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    public void SetTargetPosition(int target)
    {
        if(r$front != null && r$rear != null) {
            r$front.setTargetPosition(target);
            r$rear.setTargetPosition(target);
        }

        if(l$front != null && l$rear != null) {
            l$front.setTargetPosition(target);
            l$rear.setTargetPosition(target);
        }
    }
    /**
     * HAVE THE ENCODERS REACHED THE REQUIRED NUMBER OF TICKS
     *
     * @param ticks
     * @param ticks
     * @return true if the encoders have reached TICKS number of ticks
     */
    public boolean have_encoders_reached(int ticks) {
        return has_left_encoder_reached(ticks) && has_right_encoder_reached(ticks);
    }

    public boolean have_encoders_reached(int leftTicks, int rightTicks) {
        return has_left_encoder_reached(leftTicks) && has_right_encoder_reached(rightTicks);
    }

    public boolean has_left_encoder_reached(int ticks) {
        return Math.abs(l$front.getCurrentPosition()) >= ticks;
    }

    public boolean has_right_encoder_reached(int ticks) {
        return Math.abs(r$front.getCurrentPosition()) >= ticks;
    }


    /**
     * HAVE THE ENCODERS RESET
     *
     * @return true only if the encoders are finished resetting
     */
    public boolean have_encoders_reset() {
        return has_left_front_encoder_reset() && has_right_front_encoder_reset() && has_left_rear_encoder_reset() && has_right_rear_encoder_reset();
    }

    public boolean has_left_front_encoder_reset() {
        return l$front.getCurrentPosition() == 0;
    }

    public boolean has_right_front_encoder_reset() {

        return r$front.getCurrentPosition() == 0;
    }

    public boolean has_left_rear_encoder_reset() {
        return l$rear.getCurrentPosition() == 0;
    }

    public boolean has_right_rear_encoder_reset() {

        return r$rear.getCurrentPosition() == 0;
    }

    /**
     * Gyroscope Methods
     */

    /**
     *
     * @param inches
     */
    void drive(int inches)   {
        int targetTicks = getNumTicks(inches);
        this.reset_drive_encoders();
        this.RunWithEncoders();
        if (inches >0) {
            while (!this.have_encoders_reached(targetTicks)) {
                this.set_drive_power(-DRIVE_POWER);
            }
        }
        else{
            while (!this.have_encoders_reached(targetTicks)) {
                this.set_drive_power(DRIVE_POWER);
            }
        }

        this.set_drive_power(0.0);
    }

    /**
     *
     * @param degrees
     */
    //Degrees to turn. Direction indicated by sign. Postive is Clockwise
    //initial Heading in degrees from 0-360
    void turn(double degrees)    {

         initialHeading = getCurrentHeading();

        if(initialHeading < 180)   {
            initialHeading += 360;
        }
        else    {
            initialHeading -= 360;
        }

        double currHeading = initialHeading;

        if(degrees < 0)    {   //Turn CCW
            while(-Math.abs(currHeading - initialHeading) > degrees)    {
                set_drive_power(DRIVE_POWER, -DRIVE_POWER);
                currHeading = getCurrentHeading();
                if(currHeading < 180)   {
                    currHeading += 360;
                }
                else    {
                    currHeading -= 360;
                }
                telem.addData("Heading",currHeading);
                telem.update();
            }
        }
        else    { //Turn CW
            while(Math.abs(currHeading - initialHeading) < degrees)    {
                set_drive_power(-DRIVE_POWER, DRIVE_POWER);
                currHeading = getCurrentHeading();
                if(currHeading < 180)   {
                    currHeading += 360;
                }
                else    {
                    currHeading -= 360;
                }
                telem.addData("Heading",currHeading);
                telem.update();
            }
        }
        set_drive_power(0.0);
    }

    /**
     *
     * @return
     */
    double getCurrentHeading()   {
        double currentHeading;
        if(phoneOrientation == Orientation.PERPENDICULAR) {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[0]);
        }
        else {
            currentHeading = geoVectorToDegrees(gyroSensorComponents.rotationData[0]);//?
        }
        return currentHeading;
    }

    /**
     *
     * @param angle
     * @return
     */
    static double geoVectorToDegrees(double angle)    {
        double theta =  Math.toDegrees(angle);
        if (theta < 0.0) {
            theta += 360;
        }
        return theta;
    }

    /**
     * Color Sensor Logic
     */
    boolean leftRed() throws InterruptedException
    {
        muxColor.startPolling();
        int[] r1 = new int[2];
        boolean r1b; //auto init to false
        int[] b1 = new int[2];
        boolean b1b;
        int[] r2 = new int[2];
        boolean r2b ;
        for (int i=0; i<ports.length; i++) {
            int[] crgb = muxColor.getCRGB(ports[i]);
            r1[i] = crgb[1];
            b1[i] = crgb[3];
        }

        sleep(500);
        //Sleep HOW LONG NOONE KNOWS


        for (int i=0; i<ports.length; i++) {
            int[] crgb = muxColor.getCRGB(HardwareBot.ports[i]);
            r2[i] = crgb[1];
        }



        if(r1[0] > r1[1]) {
            r1b = true;
        }
        else {
            r1b = false;
        }

        if(b1[0] > b1[1]) {
            b1b = false;
        }
        else {
            b1b = true;
        }

        if(r2[0] > r2[1]) {
            r2b = true;
        }
        else {
            r2b = false;
        }

        if(r1b && r2b && b1b || r1b && r2b || r1b && b1b || b1b && r2b)
        {
            return true;
        }
        else{

            return false;
        }

    }

    /**
     * @return true if left red false if right red
     * @throws InterruptedException
     */
    boolean doColorSensor() throws InterruptedException {
        boolean leftRed = leftRed();

        switch (teamColor)  {
            case RED:
                if(leftRed)  {
                    lPusher.setPosition(lServoDown);

                }
                else {
                    rPusher.setPosition(rServoDown);
                }
                break;
            case BLUE:
                if(leftRed)  {
                   rPusher.setPosition(rServoDown);
                }
                else {
                   lPusher.setPosition(lServoDown);

                }
                break;
        }
        return leftRed;
    }
}
