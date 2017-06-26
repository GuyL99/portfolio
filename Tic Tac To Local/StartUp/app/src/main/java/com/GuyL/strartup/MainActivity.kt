package com.GuyL.strartup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.graphics.Color
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    protected fun btnClick(view:View) {
        val btnSelect = view as Button
        var cellID=0
        when(btnSelect.id){
            R.id.btn1->cellID=1
            R.id.btn2->cellID=2
            R.id.btn3->cellID=3
            R.id.btn4->cellID=4
            R.id.btn5->cellID=5
            R.id.btn6->cellID=6
            R.id.btn7->cellID=7
            R.id.btn8->cellID=8
            R.id.btn9->cellID=9

        }



        Toast.makeText(this,"ID:${cellID}",Toast.LENGTH_LONG).show()


    }

    var player1= ArrayList<Int>()
    var player2= ArrayList<Int>()
    var activePlayer= 1

    fun playGame(cellID:Int,btnSelect:Button){
        if(activePlayer==1){
            btnSelect.text="X"
            btnSelect.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            activePlayer= 2
            autoPlay()
        }
        if(activePlayer==1){
            btnSelect.text="O"
            btnSelect.setBackgroundResource(R.color.darkGreen)
            player1.add(cellID)
            activePlayer= 1
        }

        btnSelect.isEnabled=false
        checkWinner()



    }


    fun checkWinner(){
        var winner = -1

        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3)){

            winner = 1
        }
        if(player2.contains(1)&&player2.contains(2)&&player2.contains(3)){

            winner = 2
        }
        if(player1.contains(4)&&player1.contains(5)&&player1.contains(6)){

            winner = 1
        }
        if(player2.contains(4)&&player2.contains(5)&&player2.contains(6)){

            winner = 2
        }
        if(player1.contains(7)&&player1.contains(8)&&player1.contains(9)){

            winner = 1
        }
        if(player2.contains(7)&&player2.contains(8)&&player2.contains(9)){

            winner = 2
        }
        if(player1.contains(1)&&player1.contains(4)&&player1.contains(7)){

            winner = 1
        }
        if(player2.contains(1)&&player2.contains(4)&&player2.contains(7)){

            winner = 2
        }
        if(player1.contains(5)&&player1.contains(2)&&player1.contains(8)){

            winner = 1
        }
        if(player2.contains(2)&&player2.contains(5)&&player2.contains(8)){

            winner = 2
        }
        if(player1.contains(6)&&player1.contains(9)&&player1.contains(3)){

            winner = 1
        }
        if(player2.contains(6)&&player2.contains(9)&&player2.contains(3)){

            winner = 2
        }
        if(player1.contains(1)&&player1.contains(5)&&player1.contains(9)){

            winner = 1
        }
        if(player2.contains(1)&&player2.contains(5)&&player2.contains(9)){

            winner = 2
        }
        if(player1.contains(7)&&player1.contains(5)&&player1.contains(3)){

            winner = 1
        }
        if(player2.contains(7)&&player2.contains(5)&&player2.contains(3)){

            winner = 2
        }
        if(winner!=-1){
            if(winner ==1 ){
                Toast.makeText(this,"Player1 won the game",Toast.LENGTH_LONG).show()
                cleanGame()
            }else{
                Toast.makeText(this,"Player2 won the game",Toast.LENGTH_LONG).show()
                cleanGame()
            }
        }
    }



    fun cleanGame(){
       btn1.setBackgroundResource(R.color.white)
        btn1.text=""
        btn2.setBackgroundResource(R.color.white)
        btn2.text=""
        btn3.setBackgroundResource(R.color.white)
        btn3.text=""
        btn4.setBackgroundResource(R.color.white)
        btn4.text=""
        btn5.setBackgroundResource(R.color.white)
        btn5.text=""
        btn6.setBackgroundResource(R.color.white)
        btn6.text=""
        btn7.setBackgroundResource(R.color.white)
        btn7.text=""
        btn8.setBackgroundResource(R.color.white)
        btn8.text=""
        btn9.setBackgroundResource(R.color.white)
        btn9.text=""
        Toast.makeText(this,"Game cleaned, you are free to start over",Toast.LENGTH_LONG).show()
        player1.clear()
        player2.clear()
        activePlayer=1

    }


    fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for(cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }
        var r = Random()
        val randomIndex = r.nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randomIndex]
        var myBtn:Button?
        when(cellID){
            1-> myBtn = btn1
            2-> myBtn = btn2
            4-> myBtn = btn4
            3-> myBtn = btn3
            5-> myBtn = btn5
            6-> myBtn = btn6
            8-> myBtn = btn8
            7-> myBtn = btn7
            9-> myBtn = btn9
            else->{
                myBtn  =btn5
            }
        }
        playGame(cellID,myBtn)
    }
}


