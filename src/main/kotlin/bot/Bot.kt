package bot

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.text

class Bot(private val config: BotConfiguration, private val ah: AbsencesHandler){
    fun Run(){

        val bot = bot {

            token = config.Get("bot.key")

            dispatch {
                text { bot, update ->
                    val text = update.message?.text ?: ""
                    val absences = ah.Get(text)

                    val message = "Entschuldigte Fehlstunden: ${absences.getAcceptedSum()}\n" +
                            "Nicht entschuldigte Fehlstunden: ${absences.getNotAcceptedSum()}\n\n" +
                            "Offene nicht entschuldigte (max. 30): ${30 - absences.getNotAcceptedSum()}"


                    bot.sendMessage(chatId = update.message!!.chat.id, text = message)
                }

            }
        }

        bot.startPolling()
    }
}