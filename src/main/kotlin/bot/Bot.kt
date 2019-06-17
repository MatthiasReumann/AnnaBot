package bot

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.*

class Bot(private val config: BotConfiguration){
    private val ah = AbsencesHandler()

    fun Run(){

        val bot = bot {

            token = config.Get("bot.key")

            dispatch {
                command("new") { bot, update, args ->
                    println("new request")
                    val text = update.message?.text ?: ""
                    val absences = ah.Get(text)

                    val message = "\uD83D\uDE80 FEHLSTUNDEN \uD83D\uDE80\n\n" +
                            "Entschuldigte Fehlstunden: ${absences.getAcceptedSum()}\n" +
                            "Nicht entschuldigte Fehlstunden: ${absences.getNotAcceptedSum()}\n\n" +
                            "Offene nicht entschuldigte (von 30): ${30 - absences.getNotAcceptedSum()}"


                    bot.sendMessage(chatId = update.message!!.chat.id, text = message)
                }
                command("help") { bot, update ->
                    val message = "🙋\n\n" +
                        "/new: Sende den Inhalt der .csv Datei um Informationen zu deinen Fehlstunden zu erhalten!\n" +
                        "/repo: URL zum GitHub Repository\n"

                    bot.sendMessage(chatId = update.message!!.chat.id, text = message)
                }
                command("repo") {bot, update ->
                    val message = """https://github.com/MatthiasReumann/AnnaBot"""

                    bot.sendMessage(chatId = update.message!!.chat.id, text = message)
                }
                telegramError { _, telegramError ->
                    println(telegramError.getErrorMessage())
                }

            }
        }

        bot.startPolling()
    }
}