package org.example;

import java.util.HashMap;

public class Phrase {
    private final int language;
    private static final HashMap<String, String[]> list = new HashMap<>() {
        {
            put("promptToGameMode", new String[]{
                    "Choose game mode (1 - Comp vs User, other symbols - Comp vs Comp): ",
                    "Выберите режим игры (1 — Компьютер против Пользователя, другие символы — Компьютер против Компьютера): ",
                    "Sélectionner le mode de jeu(1 - Ordinateur contre Utilisateur, autres symboles - Ordinateur contre Ordinateur): ",
                    "Spielmodus auswählen(1 – Computer vs. Benutzer, andere Symbole – Computer vs.Computer): "});
            put("move", new String[]{
                    "MOVE ",
                    "ХОД ",
                    "COUP ",
                    "ZUG "
            });
            put("bulls", new String[]{
                    "Bulls: ",
                    "Быков: ",
                    "Taureaux: ",
                    "Bullen: "
            });
            put("cows", new String[]{
                    "Cows: ",
                    "Коров: ",
                    "Vaches: ",
                    "Kühe: "
            });
            put("player1guess", new String[]{
                    "Player 1 guess: ",
                    "Догадка Игрока 1: ",
                    "Le joueur 1 devine: ",
                    "Vermutung von Spieler 1: "
            });
            put("player2guess", new String[]{
                    "Player 2 guess: ",
                    "Догадка Игрока 2: ",
                    "Le joueur 2 devine: ",
                    "Vermutung von Spieler 2: "
            });
            put("player1response", new String[]{
                    "Player 1 response: ",
                    "Ответ Игрока 1: ",
                    "Réponse du joueur 1: ",
                    "Antwort von Spieler 1: "
            });
            put("player2response", new String[]{
                    "Player 2 response: ",
                    "Ответ Игрока 2: ",
                    "Réponse du joueur 2: ",
                    "Antwort von Spieler 2: "
            });
            put("player1won", new String[]{
                    "Player 1 won",
                    "Выиграл Игрок 1",
                    "Le joueur 1 a gagné",
                    "Spieler 1 hat gewonnen"
            });
            put("player2won", new String[]{
                    "Player 2 won",
                    "Выиграл Игрок 2",
                    "Le joueur 2 a gagné",
                    "Spieler 2 hat gewonnen"
            });
            put("draw", new String[]{
                    "Draw",
                    "Ничья",
                    "Match nul",
                    "Unentschieden"
            });
            put("autoSecretCode", new String[]{
                    "Your automatically generated guess number is ",
                    "Ваше загаданное число, сгенерированное автоматически, равно ",
                    "Votre numéro de supposition généré automatiquement est ",
                    "Ihre automatisch generierte Ratezahl lautet "
            });
            put("promptToSecretCode", new String[]{
                    "If you want to make a different wish, enter it here: ",
                    "Если Вы хотите загадать другое, введите здесь: ",
                    "Si vous souhaitez faire un souhait différent, indiquez-le ici :",
                    "Wenn Du einen anderen Wunsch äußern möchtest, gib ihn hier ein: ",
            });
            put("secretCode", new String[]{
                    "Your guessed number is ",
                    "Ваше загаданное число равно ",
                    "Votre numéro deviné est ",
                    "Ihre erratene Zahl ist "
            });
            put("calculateHint", new String[]{
                    "Calculating a hint...",
                    "Вычисляю подсказку...",
                    "Calculer un indice...",
                    "Einen Hinweis berechnen..."
            });
            put("hint", new String[]{
                    "Clue: ",
                    "Подсказка: ",
                    "Indice: ",
                    "Hinweis: "
            });
            put("promptForGuess", new String[]{
                    "Enter your guess (x or X for exit): ",
                    "Введите Вашу догадку (x или X для выхода): ",
                    "Entrez votre estimation (x ou X pour sortir): ",
                    "Geben Sie Ihren Tipp ein (x oder X für Beenden): "
            });
            put("analyzing", new String[]{
                    "Analyzing...",
                    "Анализирую...",
                    "En train d'analyser...",
                    "Analysieren..."
            });
            put("incorrectInput", new String[]{
                    "Incorrect input, try again",
                    "Неверный ввод, попробуйте еще раз",
                    "Entrée incorrecte, réessayez",
                    "Falsche Eingabe, versuchen Sie es erneut"
            });
            put("somethingWrong", new String[]{
                    "Something wrong. Please, input your guess number for verification: ",
                    "Что-то не так. Пожалуйста, введите загаданное число для проверки: ",
                    "Quelque chose ne va pas. Veuillez entrer le numéro masqué pour vérifier: ",
                    "Irgendwas stimmt nicht. Bitte geben Sie zur Überprüfung die versteckte Nummer ein: "
            });
            put("errorInAnswer", new String[]{
                    "Error in answer: ",
                    "Ошибка в ответе: ",
                    "Erreur dans la réponse: ",
                    "Fehler in der Antwort: "
            });
            put("errorInCode", new String[]{
                    "The error in code for further correction...",
                    "Ошибка в коде для дальнейшего исправления...",
                    "L'erreur dans le code pour une correction supplémentaire...",
                    "Der Fehler im Code zur weiteren Korrektur..."
            });
            put("rest", new String[]{
                    "The search field contains: ",
                    "В поле поиска осталось: ",
                    "Le champ de recherche contient: ",
                    "Das Suchfeld enthält: "
            });
            put("end", new String[]{
                    "The game is interrupted",
                    "Игра прерывается",
                    "Le jeu est interrompu",
                    "Das Spiel wird unterbrochen"
            });
        }
    };

    public Phrase(int language){
        this.language = language;
    }

    public String get(String name){
        return list.get(name)[language];
    }

}