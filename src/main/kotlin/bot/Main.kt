package bot

fun main(args: Array<String>) {
    val botConfig = BotConfiguration()
    val fh = AbsencesHandler()
    val bot = Bot(botConfig, fh)
    bot.Run();
}