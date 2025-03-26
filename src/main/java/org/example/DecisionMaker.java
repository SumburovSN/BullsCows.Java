package org.example;

import java.util.*;

public class DecisionMaker {
    private final String sourceSet = "0123456789";
    private final int[] possibleAnswersSet = new int[]{0, 1, 2, 3, 4, 10, 11, 12, 13, 20, 21, 22, 30, 40};
    private final ArrayList<String> full = new ArrayList<>();
    private ArrayList<String> decisionsField = new ArrayList<>();
    private String secretCode;
    private final Phrase phrase;

    public DecisionMaker(int language){
        phrase = new Phrase(language);
        buildFull("", sourceSet);
        decisionsField.addAll(full);
        secretCode = pick();
    }

    public String pick(){
        StringBuilder numeric = new StringBuilder(sourceSet);
        StringBuilder puzzle = new StringBuilder();
        char symbol;
        while (puzzle.length() != 4) {
            int index = (int) (Math.random() * numeric.length());
            symbol = numeric.charAt(index);
            numeric.delete(index, index+1);
            puzzle.append(symbol);
        }
        return puzzle.toString();
    }

    public static int getBullsCows(String secretCode, String guess) {
        int bulls = 0, cows = 0;

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (guess.charAt(i) == secretCode.charAt(k)) {
                    if ( i == k) bulls += 1;
                    else cows += 1;
                }
            }
        }
        // the response of template 12 (0, 22, etc.)
        return bulls * 10 + cows;
    }

    private void buildFull(String subset, String source_set){
        if (subset.length() == 4) full.add(subset);
        else {
            for (int i = 0; i < source_set.length(); i++) {
                buildFull(subset + source_set.charAt(i), source_set.substring(0, i) +
                        source_set.substring(i + 1));
            }
        }
    }

    private void narrowDecisionsField(String guess, int response) {
         ArrayList<String> newDecisionField = new ArrayList<>();
         for (String number: decisionsField){
             if (DecisionMaker.getBullsCows(number, guess) == response) newDecisionField.add(number);
         }
        decisionsField = newDecisionField;
    }

    // distribution = {40: ["1234"], 30: ["1230", "0234", ...]}
    private HashMap<Integer, ArrayList<String>> getDistribution(String guess){
        HashMap<Integer, ArrayList<String>> distribution = new HashMap<>();
        for (int answer: possibleAnswersSet){
            distribution.put(answer, new ArrayList<>());
        }
        for (String number: decisionsField) {
            distribution.get(DecisionMaker.getBullsCows(number, guess)).add(number);
        }
        return distribution;
    }

    // baskets = [1440, 1260, 720, 480, 360, 264, 216, 180, 72, 24, 9, 8, 6, 1]
    private ArrayList<Integer> getBasketsAmount(String guess){
        ArrayList<Integer> basket = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> distribution = getDistribution(guess);
        for (int answer: distribution.keySet()){
            if (!distribution.get(answer).isEmpty()){
                basket.add(distribution.get(answer).size());
            }
            basket.sort(Collections.reverseOrder());
        }
        return basket;
    }

    // {"4970": [378, 369, 222, 126, 91, 83, 57, 54, 31, 11, 6, 6, 5, 1],
    //  "3640": [420, 312, 204, 156, 144, 60, 60, 56, 20, 8],... ]}
    private HashMap<String, ArrayList<Integer>> getAllBasketsAmount(ArrayList<String> guessField){
        HashMap<String, ArrayList<Integer>> baskets = new HashMap<>();
        int i = 1;
        for (String number: guessField){
            System.out.printf("\r %s %.2f%%", phrase.get("analyzing"), i/guessField.size() * 100.0);
            baskets.put(number, getBasketsAmount(number));
            i++;
        }
        System.out.println();
        return baskets;
    }

    private ArrayList<ArrayList<Integer>> getAmountTypes(HashMap<String, ArrayList<Integer>> baskets){
        ArrayList<ArrayList<Integer>> amounts = new ArrayList<>();
        for (Map.Entry <String, ArrayList<Integer>> basket: baskets.entrySet()){
            if (!amounts.contains(basket.getValue())) amounts.add(basket.getValue());
        }
        return amounts;
    }

    private ArrayList<Integer> getMinimalAmountType(ArrayList<ArrayList<Integer>> amounts){
        Comparator<ArrayList<Integer>> arrayListComparator = (o1, o2) -> {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++){
                if (!Objects.equals(o1.get(i), o2.get(i))) return o1.get(i) - o2.get(i);
                else if (i == o1.size() - 1) return -1;
                else if (i == o2.size() - 1) return 1;
            }
            return 0;
        };
        amounts.sort(arrayListComparator);
        return amounts.get(0);
    }

    private ArrayList<String> getOptimalList(ArrayList<String> guessField){
        HashMap<String, ArrayList<Integer>> baskets = getAllBasketsAmount(guessField);
        ArrayList<ArrayList<Integer>> amounts = getAmountTypes(baskets);
        ArrayList<Integer> minimalAmountType = getMinimalAmountType(amounts);
        ArrayList<String> optimalList = new ArrayList<>();
        for (Map.Entry <String, ArrayList<Integer>> entry: baskets.entrySet()){
            if (entry.getValue().equals(minimalAmountType)) optimalList.add(entry.getKey());
        }
        return optimalList;
    }

    private String chooseRandomGuess(ArrayList<String> guessList){
        return guessList.get((int) (Math.random() * guessList.size()));
    }

    private String getSecondGuessOptimising(String guess, int response){
        String nextGuess = "";
        HashMap<Integer, ArrayList<String>> distribution = getDistribution(guess);
        ArrayList<String> guessList, joinedDistribution;
        switch (response) {
            case 0:
                guessList = distribution.get(0);
                System.out.println(guessList);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 1:
                guessList = distribution.get(1);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 2:
                guessList = getOptimalList(distribution.get(3));
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 3:
                guessList = getOptimalList(distribution.get(2));
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 4:
                joinedDistribution = distribution.get(3);
                joinedDistribution.addAll(distribution.get(13));
                guessList = getOptimalList(joinedDistribution);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 10, 11, 12, 30:
                guessList = distribution.get(20);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 13:
                joinedDistribution = distribution.get(3);
                joinedDistribution.addAll(distribution.get(12));
                joinedDistribution.addAll(distribution.get(21));
                guessList = getOptimalList(joinedDistribution);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 20, 21:
                guessList = distribution.get(11);
                nextGuess = chooseRandomGuess(guessList);
                break;
            case 22:
                joinedDistribution = distribution.get(11);
                joinedDistribution.addAll(distribution.get(12));
                nextGuess = chooseRandomGuess(joinedDistribution);
                break;
        }
        narrowDecisionsField(guess, response);

        return nextGuess;
    }

    public String getNextGuess(String guess, int response){
        boolean optimization = decisionsField.size() == full.size();
        if (optimization) return getSecondGuessOptimising(guess, response);
        else {
            narrowDecisionsField(guess, response);
            return chooseRandomGuess(getOptimalList(full));
        }
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public ArrayList<String> getFull() {
        return full;
    }

    public ArrayList<String> getDecisionsField() {
        return decisionsField;
    }
}
