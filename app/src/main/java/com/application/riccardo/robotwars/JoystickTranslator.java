package com.application.riccardo.robotwars;

/**
 * Created by davide on 29/12/16.
 */

// Interfaccia per i metodi di restituzione della velocita' e rotazione
public interface JoystickTranslator {

    int getSpeed(double joyPower, double joyAngle);

    int getRotation(double joyPower, double joyAngle);

    char getCharSpeed(double joyPower, double joyAngle);

    char getCharRotation(double joyPower, double joyAngle);

}
