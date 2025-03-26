package org.example;

import java.util.ArrayList;

public class Player {
    private final ArrayList<String[]> gameLog = new ArrayList<>();
    private final DecisionMaker decisionMaker;
    private final Phrase phrase;

    public Player(int language){
        decisionMaker = new DecisionMaker(language);
        phrase = new Phrase(language);
    }

    public String nextGuess(){
        String guess = "";
        if (gameLog.isEmpty()) guess = decisionMaker.pick();
        else if (decisionMaker.getDecisionsField().isEmpty()) {
            System.out.println(phrase.get("errorInCode"));
            System.out.println(gameLog);
        }
        else if (decisionMaker.getDecisionsField().size() == 1)
            guess = decisionMaker.getDecisionsField().get(0);
        else
            guess = decisionMaker.getNextGuess(gameLog.get(gameLog.size()-1)[0],
                    Integer.parseInt(gameLog.get(gameLog.size()-1)[1]));
        return guess;
    }

    public int getResponse(String guess){
        return DecisionMaker.getBullsCows(decisionMaker.getSecretCode(), guess);
    }

    public void addGameLogEntry(String guess, int response) {
        gameLog.add(new String[]{guess, Integer.toString(response)});
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public DecisionMaker getDecisionMaker() {
        return decisionMaker;
    }

    public ArrayList<String[]> getGameLog() {
        return gameLog;
    }
}
