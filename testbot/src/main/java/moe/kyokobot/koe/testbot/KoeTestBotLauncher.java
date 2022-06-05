package moe.kyokobot.koe.testbot;

/**
 * Starts a Koe test bot instance with default configurations, without any extensions and etc.
 */
public class KoeTestBotLauncher {
    public static void main(String... args) {
        var bot = new TestBot("OTcyNzU2MzU0NTgwMjIxOTgy.Yndr8w.N-M7R_qXRda3FnQ1N_xoxvYLU9s");
        Runtime.getRuntime().addShutdownHook(new Thread(bot::stop));
        bot.start();
    }
}
