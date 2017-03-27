package com.application.riccardo.robotwars;

/**
 * Created by davide on 29/12/16.
 */

// Classe che ritorna velocita' e rotazione dell'elemento JoyStick
public class JoystickTrigonometricTranslator implements JoystickTranslator {

    private final static double RAD = 57.2957795;

    public int getSpeed(double joyPower, double joyAngle)
    {
        return (int)(Math.sin(joyAngle/RAD)*joyPower);
    }

    public int getRotation(double joyPower, double joyAngle)
    {
        return (int)(Math.cos(joyAngle/RAD)*joyPower);
    }

    public char getCharSpeed(double joyPower, double joyAngle)
    {
        return (char)(Math.sin(joyAngle/RAD)*joyPower);
    }

    public char getCharRotation(double joyPower, double joyAngle)
    {
        return (char)(Math.cos(joyAngle/RAD)*joyPower);
    }
}
