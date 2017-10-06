package rpek.tictactoegame

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.logging.XMLFormatter

class MainActivity : AppCompatActivity() {

    var playOne = ArrayList<Int>()
    var playTwo = ArrayList<Int>()
    var playOneOrTwo = 1
    //allow only 9 step to move
    var step=0
    val score: Int = 10

    //winner 1 or 2
    var winner: Int? = null

    //Score
    var scorePlayOne: Int = 0
    var scorePlayTwo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun onClick(view: View) {
        var typeBtn: Button = view as Button
        var cellId = 0
        when (typeBtn.id) {
            R.id.btnOne -> cellId = 1
            R.id.btnTwo -> cellId = 2
            R.id.btnThree -> cellId = 3
            R.id.btnFour -> cellId = 4
            R.id.btnFive -> cellId = 5
            R.id.btnSix -> cellId = 6
            R.id.btnSeven -> cellId = 7
            R.id.btnEight -> cellId = 8
            R.id.btnNine -> cellId = 9

        }

        typeBtn.isEnabled = false
        playGame(cellId, typeBtn)

        // Toast.makeText(this, "Cell Id : $cellId", Toast.LENGTH_SHORT).show()
    }


    fun playGame(cellID: Int, btnSelect: Button) {
        if (playOneOrTwo == 1) {
            btnSelect.text = "X"
            btnSelect.setBackgroundColor(resources.getColor(R.color.green))
            playOne.add(cellID)
            playOneOrTwo = 2
        } else {
            btnSelect.text = "0"
            btnSelect.setBackgroundColor(resources.getColor(R.color.yellow))
            playTwo.add(cellID)
            playOneOrTwo = 1
        }
        ++step
        findWinner()
    }

    fun findWinner() {
        winner = -1

        //row 1
        if (playOne.contains(1) && playOne.contains(2) && playOne.contains(3)) {
            winner = 1
        }

        if (playTwo.contains(1) && playTwo.contains(2) && playTwo.contains(3)) {
            winner = 2
        }

        //row 2
        if (playOne.contains(4) && playOne.contains(5) && playOne.contains(6)) {
            winner = 1
        }
        if (playTwo.contains(4) && playTwo.contains(5) && playTwo.contains(5)) {
            winner = 2
        }

        //row 3
        if (playOne.contains(7) && playOne.contains(8) && playOne.contains(9)) {
            winner = 1
        }
        if (playTwo.contains(7) && playTwo.contains(8) && playTwo.contains(9)) {
            winner = 2
        }

        /*******************************************************/
        //row 1
        if (playOne.contains(1) && playOne.contains(4) && playOne.contains(7)) {
            winner = 1
        }
        if (playTwo.contains(1) && playTwo.contains(4) && playTwo.contains(7)) {
            winner = 2
        }

        //row 2
        if (playOne.contains(2) && playOne.contains(5) && playOne.contains(8)) {
            winner = 1
        }
        if (playTwo.contains(2) && playTwo.contains(5) && playTwo.contains(8)) {
            winner = 2
        }

        //row 3
        if (playOne.contains(3) && playOne.contains(6) && playOne.contains(9)) {
            winner = 1
        }
        if (playTwo.contains(3) && playTwo.contains(6) && playTwo.contains(9)) {
            winner = 2
        }

        /*********************************************************/
        //Cross Left
        if (playOne.contains(1) && playOne.contains(5) && playOne.contains(9)) {
            winner = 1
        }
        if (playTwo.contains(3) && playTwo.contains(5) && playTwo.contains(9)) {
            winner = 2
        }
        //Cross Right
        if (playOne.contains(3) && playOne.contains(5) && playOne.contains(7)) {
            winner = 1
        }
        if (playTwo.contains(3) && playTwo.contains(5) && playTwo.contains(7)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                scorePlayOne = scorePlayOne + score
                tvPlayOne.text = "" + scorePlayOne
                tvPlayTwo.text = "" + scorePlayTwo
                Toast.makeText(this, "Ratanak is winner!", Toast.LENGTH_SHORT).show()

            } else {
                scorePlayTwo = scorePlayTwo + score
                tvPlayTwo.text = "" + scorePlayTwo
                tvPlayOne.text = "" + scorePlayOne
                Toast.makeText(this, "Vorn Katort is winner!", Toast.LENGTH_SHORT).show()
            }
            winner=-1
            lnReplay.visibility = View.VISIBLE
        }else if(winner==-1){
            //check for draw match
            if(step==9) {
                Toast.makeText(this, "Draw Match!", Toast.LENGTH_SHORT).show()
                lnReplay.visibility = View.VISIBLE
            }
        }

    }

    /*Btn Click*/
    fun clickReplay(view: View) {
        replayGame()
    }

    /*after click on btn replay*/
    fun replayGame() {
        winner = -1
        playTwo.clear()
        playOne.clear()
        step=0
        playOneOrTwo=1
        resetBackground()
        resetText()
        enableButton()
        lnReplay.visibility = View.GONE

    }

    /*reset background to gray default*/
    fun resetBackground() {
        btnOne.setBackgroundColor(resources.getColor(R.color.myGray))
        btnTwo.setBackgroundColor(resources.getColor(R.color.myGray))
        btnThree.setBackgroundColor(resources.getColor(R.color.myGray))
        btnFour.setBackgroundColor(resources.getColor(R.color.myGray))
        btnFive.setBackgroundColor(resources.getColor(R.color.myGray))
        btnSix.setBackgroundColor(resources.getColor(R.color.myGray))
        btnSeven.setBackgroundColor(resources.getColor(R.color.myGray))
        btnEight.setBackgroundColor(resources.getColor(R.color.myGray))
        btnNine.setBackgroundColor(resources.getColor(R.color.myGray))
    }

    /*reset text to empty*/
    fun resetText() {
        btnOne.text = ""
        btnTwo.text = ""
        btnThree.text = ""
        btnFour.text = ""
        btnFive.text = ""
        btnSix.text = ""
        btnSeven.text = ""
        btnEight.text = ""
        btnNine.text = ""
    }

    /*Allow button to click again*/
    fun enableButton() {
        btnOne.isEnabled = true
        btnTwo.isEnabled = true
        btnThree.isEnabled = true
        btnFour.isEnabled = true
        btnFive.isEnabled = true
        btnSix.isEnabled = true
        btnSeven.isEnabled = true
        btnEight.isEnabled = true
        btnNine.isEnabled = true
    }
}
