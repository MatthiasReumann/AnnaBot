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
                    val text = update.message?.text ?: ""
                    val absences = ah.Get(text)

                    val message = "\uD83D\uDE80 FEHLSTUNDEN \uD83D\uDE80\n\n" +
                            "Entschuldigte Fehlstunden: ${absences.getAcceptedSum()}\n" +
                            "Nicht entschuldigte Fehlstunden: ${absences.getNotAcceptedSum()}\n\n" +
                            "Offene nicht entschuldigte (von 30): ${30 - absences.getNotAcceptedSum()}"


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