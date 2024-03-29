package bot

import java.time.Duration
import java.time.LocalTime
import java.util.*

class Absence(val start: LocalTime, val end: LocalTime, val accepted: Boolean, val date: Date){
    public val duration = this.getDurationInMinutes(this.start, this.end)

    private fun getDurationInMinutes(start: LocalTime, end: LocalTime): Int{
        return ((Duration.between(start, end).getSeconds() / 60) / 50).toInt();
    }
}