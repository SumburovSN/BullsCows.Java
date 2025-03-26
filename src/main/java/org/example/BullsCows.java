package org.example;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.round;

public class BullsCows {
    private final Player player1;
    private final Player player2;
    private boolean isGame;
    private final Phrase phrase;

    public BullsCows(int language, int user){
        player1 = new Player(language);
        if (user > 0) player2 = new UserPlayer(language);
        else player2 = new Player(language);
        isGame = true;
        phrase = new Phrase(language);
    }

    public void start(){
        int move = 1;
        int response1, response2;
        while (isGame){
            System.out.println(Colors.HEADER + phrase.get("move") + move);
            response2 = player1Guessing();
            response1 = player2Guessing();
            System.out.println(Colors.HEADER);
//            if (response2 == 5) {
//                System.out.println(Colors.HEADER + phrase.get("end") + move);
//                continue;
//            }

            if (response2 == 40 || response1 == 40) {
                isGame = false;
                printWinner(response1, response2);
            }
            if (player1.getDecisionMaker().getDecisionsField().isEmpty() ||
                    player2.getDecisionMaker().getDecisionsField().isEmpty()) isGame = false;
            move++;
        }
    }

    private int player1Guessing(){
        String guess;
        int response;
        guess = player1.nextGuess();
        System.out.println(Colors.BLUE + phrase.get("rest") + player1.getDecisionMaker().getDecisionsField().size());
        System.out.println(phrase.get("player1guess") + guess);
        response = player2.getResponse(guess);
        System.out.println(Colors.GREEN + phrase.get("player2response") + formatResponse(response));
        player1.addGameLogEntry(guess, response);
        return response;
    }

    private int player2Guessing(){
        String guess;
        int response;
        guess = player2.nextGuess();
        if (Objects.equals(guess, "x") || Objects.equals(guess, "X")){
            isGame = false;
            return 5;
        }
        System.out.println(Colors.GREEN + phrase.get("rest") + player2.getDecisionMaker().getDecisionsField().size());
        System.out.println(phrase.get("player2guess") + guess);
        response = player1.getResponse(guess);
        System.out.println(Colors.BLUE + phrase.get("player1response") + formatResponse(response));
        System.out.println(Colors.HEADER);
        player2.addGameLogEntry(guess, response);
        return response;
    }

    private String formatResponse(int response) {
        int bulls = round((float) response / 10);
        int cows = response - bulls * 10;
        return phrase.get("bulls") + bulls + " " + phrase.get("cows") + cows;
    }

    private void printWinner(int response1, int response2) {
        if (response1 == 40 && response2 == 40) System.out.println(phrase.get("draw"));
        else if (response1 == 40) System.out.println(phrase.get("player2won"));
        else if (response2 == 40) System.out.println(phrase.get("player1won"));
        printGameLog(1);
        printGameLog(2);
    }

    private void printGameLog(int player){
        ArrayList<String[]> gameLog = player == 1 ? player1.getGameLog() : player2.getGameLog();
        StringBuilder history = new StringBuilder();
        for (String[] entry: gameLog) history.append(entry[1]).append(":").append(entry[0]).append(" ");
        System.out.println(history);
    }

}
