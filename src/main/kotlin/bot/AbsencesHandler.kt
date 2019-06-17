package bot

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AbsencesHandler() {

    fun Get(content: String): Absences{
        val regex = "(entsch.)".toRegex()

        val lines = content.split("\n")
        val absences = Absences()

        for (i in 1 until lines.size) {
            val line = lines[i];
            val items = line.split(" ")

            val date = this.parseDate(items[4])
            println(date)
            val start = this.parseTime(items[5])
            val end = this.parseTime(items[7])
            val accepted = regex.containsMatchIn(line)

            absences.add(Absence(start,end,accepted,date))
        }

        return absences
    }

    private fun parseDate(date: String): Date {
        return SimpleDateFormat("dd.MM.yyyy").parse(date);
    }

    private fun parseTime(time: String): LocalTime {
        return LocalTime.parse(time, DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }
}