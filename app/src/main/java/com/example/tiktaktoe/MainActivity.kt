 package com.example.tiktaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER=true
    var TURN_COUNT=0
    var boardStatus=Array(3){IntArray(3)}
    lateinit var board: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )
for(i:Array<Button>in board){
    for(button:Button in i){
        button.setOnClickListener(this)
    }
}
        initializeBoardStatus()
resetbtn.setOnClickListener(){

    PLAYER=true
    TURN_COUNT=0
    initializeBoardStatus()

}
    }

    private fun initializeBoardStatus() {
        for(i:Int in 0..2){
            for(j:Int in 0..2){
                boardStatus[i][j]=-1
            }
        }
        for(i : Array<Button> in board){
            for(button: Button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }


    override fun onClick(v: View) {
when(v.id){
    R.id.btn1->{
updatevalue(row=0,col=0,player=PLAYER)
    } 
    R.id.btn2->{
        updatevalue(row=0,col=1,player=PLAYER)
    }
    R.id.btn3->{
        updatevalue(row=0,col=2,player=PLAYER)
    }
    R.id.btn4->{
        updatevalue(row=1,col=0,player=PLAYER)
    }
    R.id.btn5->{
        updatevalue(row=1,col=1,player=PLAYER)
    }
    R.id.btn6->{
        updatevalue(row=1,col=2,player=PLAYER)
    }
    R.id.btn7->{
        updatevalue(row=2,col=0,player=PLAYER)
    }
    R.id.btn8->{
        updatevalue(row=2,col=1,player=PLAYER)
    }
    R.id.btn9->{
            updatevalue(row=2,col=2,player=PLAYER)
    }

}
        TURN_COUNT++
        PLAYER=!PLAYER
        if(PLAYER){
            updatedisplay("player X turn")
        }
        else{
            updatedisplay("player 0 turn")
        }
        if(TURN_COUNT==9){
            updatedisplay("Game Draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1]&&boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updatedisplay("player X Winner")
                    break
                }else if(boardStatus[i][0]==0){
                    updatedisplay("player 0 is Winner")
                    break
                }
            }
        }
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i]&&boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updatedisplay("player X Winner")
                    break
                }else if(boardStatus[0][i]==0){
                    updatedisplay("player 0 is Winner")
                    break
                }
            }
        }
        if(boardStatus[0][0]==boardStatus[1][1]&&boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updatedisplay("Player X is Winner")
            }else if(boardStatus[0][0]==0){
                updatedisplay("player 0 is Winner ")
            }
        }
        if(boardStatus[0][2]==boardStatus[1][1]&&boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updatedisplay("Player X is Winner")
            }else if(boardStatus[0][2]==0){
                updatedisplay("player 0 is Winner ")
            }
        }
    }

    private fun updatedisplay(text: String) {
displayTv.text=text
        if(text.contains("Winner")){
            disablebutton()
        }
    }
private fun disablebutton(){
    for (i: Array<Button> in board) {
        for (button: Button in i) {
            button.isEnabled = false
        }
    }
}
    private fun updatevalue(row: Int, col: Int, player: Boolean) {
val text:String=if(player) "X" else "O"
        val value:Any=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]= value as Int
    }
}