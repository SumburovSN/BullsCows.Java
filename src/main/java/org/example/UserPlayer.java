package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class UserPlayer extends Player {

    public UserPlayer(int language){
        super(language);
        inputSecretCode();
    }

    public void inputSecretCode(){
        System.out.println(getPhrase().get("autoSecretCode") + getDecisionMaker().getSecretCode());
        System.out.println(getPhrase().get("promptToSecretCode"));
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String guess = null;
        try {
            guess = r.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (isCorrect(guess)) getDecisionMaker().setSecretCode(guess);

        System.out.println(getPhrase().get("promptToSecretCode") + getDecisionMaker().getSecretCode());
    }

    private boolean isCorrect(String guess){
        return getDecisionMaker().getFull().contains(guess);
    }

    @Override
    public String nextGuess() {
        String guess;
        System.out.println(Colors.WHITE + getPhrase().get("calculateHint"));
        String hint = super.nextGuess();
        System.out.println(getPhrase().get("hint") + hint);
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println(getPhrase().get("promptForGuess"));
                guess = r.readLine();
                if (isCorrect(guess) && !isNumberInGameLog(guess)) break;
        //      Игрок хочет выйти
                if (Objects.equals(guess, "x") || Objects.equals(guess, "X")) break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return guess;
    }

    private boolean isNumberInGameLog(String guess) {
        for (String[] entry: getGameLog()){
            if (Objects.equals(guess, entry[0])) return true;
        }
        return false;
    }

}
