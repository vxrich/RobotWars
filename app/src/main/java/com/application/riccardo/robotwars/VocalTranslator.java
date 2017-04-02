package com.application.riccardo.robotwars;

/**
 * Created by Riccardo on 14/01/17.
 */

// Classe per la ricerca delle parole chiave nella stringa prodotta dal riconoscimento vocale
public class VocalTranslator {

    private BluetoothCommunication blue;

    public VocalTranslator (BluetoothCommunication blue)
    {
        this.blue = blue;
    }

    // Stringhe delle parole che vengono cercate
    private String comandi[] = {"attacca", "attack", "fermo", "stop", "fermati" };

    public void translate (String text)
    {
        for(int i=0; i < comandi.length; i++)
        {
            if(text.toLowerCase().contains(comandi[i]))
            {
                switch(comandi[i])
                {

                    case "attaca":
                    case "attack":
                    {
                        blue.attack();
                        break;
                    }

                    case "fermo":
                    case "stop":
                    case "fermati":
                    {
                        blue.move('c','c');
                        break;
                    }

                    // Se non vengono trovate parole viene inviato un comando per far suonare da errore il robot
                    //default: blue.makeSound("errore");  // Inserire il nome del suono di errore
                }
            }

        }
    }

}
