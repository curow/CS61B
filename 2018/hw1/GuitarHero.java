/*
 * A client that uses the synthesizer package to
 * replicate plucked guitar string sound with
 * 37 keyboard keys.
 */

import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final String keyboard =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public GuitarString[] guitarStrings;

    public GuitarHero() {
        guitarStrings = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }
    }

    public static void main(String[] args) {
        GuitarHero guitar = new GuitarHero();
        while (true) {
            GuitarString keyString;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyNumber = keyboard.indexOf(key);
                if (keyNumber >= 0) {
                    keyString = guitar.guitarStrings[keyNumber];
                    keyString.pluck();
                }
            }

            double sample = 0;
            for (int i = 0; i < keyboard.length(); i++) {
                keyString = guitar.guitarStrings[i];
                sample += keyString.sample();
                keyString.tic();
            }

            StdAudio.play(sample);
        }
    }

}
