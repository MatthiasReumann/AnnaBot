package bot

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec

object BotSpec : ConfigSpec() {
    val key by required<String>()
}

class BotConfiguration{
    fun Get(): Config{
        val config = Config { addSpec(BotSpec) }
                .from.env()
        return config
    }
}