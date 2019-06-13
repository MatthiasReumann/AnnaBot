package bot

fun main(args: Array<String>) {
    val botConfig = BotConfiguration()
    val config = botConfig.Get("bot.key");
    println(config);
}