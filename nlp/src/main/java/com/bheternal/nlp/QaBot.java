package com.bheternal.nlp;

import java.util.Scanner;
import java.util.function.Consumer;

/**
 * QaBot
 *
 * @author Zain
 * @date 2020/10/19
 */
public class QaBot {

    private final Tk tk;

    public QaBot() {
        this.tk = new Tk();
    }

    public static void main(String[] args) {
        QaBot qaBot = new QaBot();
        qaBot.listen(speech -> {
            String response = String.join(",", speech);
            qaBot.say(response);
        });
    }

    private void listen(Consumer<String[]> callback) {
        say("请输入: ");
        Scanner scanner = new Scanner(System.in);
        String speech = scanner.next();
        callback.accept(tk.handler(speech));
    }

    public void say(String text) {
        System.out.println(text);
    }

}
