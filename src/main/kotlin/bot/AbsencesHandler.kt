package bot

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AbsencesHandler() {

    fun Get(content: String): Absences{
        val regex = "(entsch.)".toRegex()

        val lines = content.split("\n")
        val absences = Absences()

        for (i in 1 until lines.size) {
            val line = lines[i];
            val items = line.split(" ")

            val start = this.parseDate(items[5])
            val end = this.parseDate(items[7])
            val accepted = regex.containsMatchIn(line)

            absences.add(Absence(start,end,accepted))
        }

        return absences
    }

    private fun parseDate(time: String): LocalTime {
        return LocalTime.parse(time, DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }
}