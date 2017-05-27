package com.application.riccardo.robotwars;

/**
 * Created by davide on 29/12/16.
 */

// Classe che ritorna velocita' e rotazione dell'elemento JoyStick
public class JoystickTrigonometricTranslator implements JoystickTranslator {

    private int turbo_enable = 0;

    private final static double RAD = 57.2957795;

    public int getTurbo()
    {
        return turbo_enable;
    }

    public void setTurbo(int value)
    {
        turbo_enable = value;
    }

    public int getSpeed(double joyPower, double joyAngle)
    {
        if (turbo_enable == 0) {
            return (int) ((Math.sin(joyAngle / RAD) * joyPower)*1.8);
        }
        else{
            return (int) ((Math.sin(joyAngle / RAD) * joyPower)*2.55);
        }
    }

    public int getRotation(double joyPower, double joyAngle)
    {

        return (int) ((Math.cos(joyAngle / RAD) * joyPower));
    }

    public char getCharSpeed(double joyPower, double joyAngle)
    {
        if (turbo_enable == 0)
        {
            return (char)((Math.sin(joyAngle/RAD)*joyPower)/1.5);
        }
        else
        {
            return (char)(Math.sin(joyAngle/RAD)*joyPower);
        }

    }

    public char getCharRotation(double joyPower, double joyAngle)
    {
            return (char)((Math.cos(joyAngle/RAD)*joyPower));

    }


}
