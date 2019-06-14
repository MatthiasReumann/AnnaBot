package bot

class Absences(){
    private var acceptedSum: Int = 0
    private var notAcceptedSum: Int = 0

    fun getAcceptedSum(): Int{
        return this.acceptedSum
    }

    fun getNotAcceptedSum(): Int{
        return this.notAcceptedSum
    }

    fun add(absence: Absence){
        if(absence.accepted) {
            this.acceptedSum += absence.duration;
        }else{
            this.notAcceptedSum += absence.duration;
        }
    }
}