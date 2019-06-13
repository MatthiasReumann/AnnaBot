package bot

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec

object BotSpec : ConfigSpec() {
    val key by required<String>()
}

class BotConfiguration{
    private var config: Config;

    init {
        config = Config { addSpec(BotSpec) }
                .from.env()
    }

    fun Get(key: String): String{
        return config<String>(key);
    }
}