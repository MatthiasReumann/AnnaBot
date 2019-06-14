package bot

fun main(args: Array<String>) {
    val botConfig = BotConfiguration()
    val bot = Bot(botConfig)

    bot.Run();
}