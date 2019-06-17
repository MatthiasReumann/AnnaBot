package bot

import java.util.*

class Absences(){
    var acceptedSum: Int = 0
            private set
    var notAcceptedSum: Int = 0
            private set
    var currentWeekSum: Int = 0
            private set

    private var week =  Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)

    fun add(absence: Absence){
        val targetCalender = Calendar.getInstance()
        targetCalender.setTime(absence.date)
        val targetWeek = targetCalender.get(Calendar.WEEK_OF_YEAR)

        if(absence.accepted){
           this.acceptedSum += absence.duration
        }else{
            this.notAcceptedSum += absence.duration
        }

        if(targetWeek == week){
            this.currentWeekSum += absence.duration
        }
    }
}