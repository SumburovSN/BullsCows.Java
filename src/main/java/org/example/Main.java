package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Choose the language for the game (1-Русский, 2-Français, 3-Deutsch, " +
                "Other symbols-English): ");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String choice = r.readLine();
        int language = checkLanguageChoice(choice);
        Phrase phrase = new Phrase(language);
        System.out.println(phrase.get("promptToGameMode"));
        String gameMode = r.readLine();
        BullsCows game;
        if (Objects.equals(gameMode, "1")) game = new BullsCows(language, 1);
        else game = new BullsCows(language, 0);
        game.start();

    }

    private static int checkLanguageChoice(String language) {
        if (Objects.equals(language, "1")) return 1;
        else if (Objects.equals(language, "2")) return 2;
        else if (Objects.equals(language, "3")) return 3;
        else return 0;
    }

}