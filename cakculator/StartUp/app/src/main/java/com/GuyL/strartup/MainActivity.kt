package com.GuyL.strartup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun btnNumber(view:View){
        if(isNewOp){
            editResult.setText("")

        }
        isNewOp=false
        val selectedBtn=  view as Button
        var num:String=editResult.text.toString()
        when(selectedBtn.id){
            btn0.id->{
                num+="0"
            }
            btn1.id->{
                num+="1"
            }
            btn2.id->{
                num+="2"
            }
            btn3.id->{
                num+="3"
            }
            btn4.id->{
                num+="4"
            }
            btn5.id->{
                num+="5"
            }
            btn6.id->{
                num+="6"
            }
            btn7.id->{
                num+="7"
            }
            btn8.id->{
                num+="8"
            }
            btn9.id->{
                num+="9"
            }
            btnDot.id->{
                num+="."
            }

            btnPlusMinus.id->{
                num+="1-"
                num+="-"+ num
            }
        }
        editResult.setText(num)

    }


    var oldNum=""
    var operation="+"
    var isNewOp=true

    fun btnOperation(view:View){

        val selectedBtn=  view as Button
        when(selectedBtn.id){
            btnPlus.id->{
                operation="+"
            }

            btnSub.id->{
                operation="-"
            }

            btnMul.id->{
                operation="*"
            }



            btnDiv.id->{
                operation="/"
            }
        }
        oldNum = editResult.text.toString()
        isNewOp=true
    }

    fun btnEqual(view:View){
        var newNum = editResult.text.toString()
        var finalNumber:Double?
        when(operation){
            "+"->{
                finalNumber=oldNum.toDouble()+ newNum.toDouble()
            }
            "-"->{
                finalNumber=oldNum.toDouble()- newNum.toDouble()
            }
            "*"->{
                finalNumber=oldNum.toDouble()* newNum.toDouble()
            }
            "/"->{
                finalNumber=oldNum.toDouble()/newNum.toDouble()
            }

            else->{
                finalNumber= 43.0
            }

        }
        editResult.setText(finalNumber.toString())
        isNewOp=true
    }

    fun btnPrecent(view:View){
        val num:Double = editResult.text.toString().toDouble()/100
        editResult.setText(num.toString()+"%")
        isNewOp=true
    }

    fun btnClean(view:View){
        editResult.setText("0")
        isNewOp=true
    }

}
