package edu.odu.cs.cs361.animations;//!

import java.util.Stack;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;//!
import static edu.odu.cs.AlgAE.Server.LocalServer.activate;//!

//
//	rpn calculator routine
//
//	Described in Chapter 10 of
//	Data Structures in C++ using the STL
//	Published by Addison-Wesley, 1997
//	Written by Tim Budd, budd@cs.orst.edu
//	Oregon State University
//

//!#include <list>
//!#include <stack>

//!using namespace std;


//
//	class calculatorEngine
//		simulate the behavior of a simple integer calculator
//
public//!
class calculatorEngine {
//!public:
	static enum  binaryOperator {plus, minus, multiply, divide};//!	enum  binaryOperator {plus, minus, multiply, divide};

	int   currentMemory   () { 
		return data.peek ();//!		return data.top (); 
		}
	void  pushOperand     (int value) { data.push (value); }
//!	void  doOperator      (binaryOperator theOp);

//!protected:
	protected Stack<Integer> data;//!	stack < list<int> > data;
//!};
	
	calculatorEngine() {//!
		data = new Stack<Integer>();//!
	}//!


void doOperator(binaryOperator theOp)//!void calculatorEngine::doOperator(binaryOperator theOp)
	// perform a binary operation on stack values
{
	ActivationRecord arec = activate(getClass());//!
	arec.breakHere("Process a binary operator");//!
	int right = data.peek();//!	int right = data.top();
	data.pop();
	int left = data.peek();//!	int left = data.top();
	data.pop();
	arec.param("theOp", theOp).var("left",left).var("right", right);//!
	arec.breakHere("pulled right and left values from stack");//!
	int result = -99;//!	int result;
	arec.var("result",result);//!
	switch(theOp) {	// do the operation
		case plus: 
			arec.breakHere("Add left and right operands");//!
			result = left + right;
			break;
		case minus:
			arec.breakHere("Subtract right from left operands");//!
			result = left - right;
			break;
		case multiply:
			arec.breakHere("Multiply left and right operands");//!
			result = left * right;
			break;
		case divide:
			arec.breakHere("Divide left by right operands");//!
			result = left / right;
			break;
		}

	// push the result back on the stack
	arec.var("result",result);//!
	arec.breakHere("Then push the result");//!
	data.push(result);
	arec.breakHere("Result has replaced the operands on the stack");//!
}

static void calculator(calculatorEngine calc)//!void calculator()
{	
	ActivationRecord arec = activate(calculatorEngine.class);//!
	arec.breakHere("entered calculator()");//!
	int intval = 0;//!    int intval;
	//!	calculatorEngine calc;
	String c;//!char c;

	String input = "";//!
	arec.var("intval",intval);//!
	arec.breakHere("start processing characters");//!
	while (true) {//!	while (cin >> c) {
		while (input.length() == 0) { //!
			input = arec.promptForInput("Input for calculator [integers, + - * / p q]: ", ".*");//!
		}//!
		c = input.substring(0, 1);//!
		input = input.substring(1);//!
		arec.var("input", input).var("c",c);//!
		arec.breakHere("switch on this character");//!
	    switch(c.charAt(0)) {//!	    switch(c) {
		case '0': case '1': case '2': case '3': case '4':
		case '5': case '6': case '7': case '8': case '9':
		    input = "" + c + input;//!		    cin.putback(c);
		    arec.breakHere("Character was put back");//!
		    {int k = 0; while (k < input.length() && Character.isDigit(input.charAt(k))) ++k;//!
		    intval = Integer.parseInt(input.substring(0, k));//!
			arec.var("intval",intval);//!
		    input = input.substring (k);//!
		    //!		    cin >> intval;
		    }//!
		    arec.breakHere("now push this integer");//!
		    calc.pushOperand(intval);
		    arec.breakHere("integer is on the stack");//!
		    break;

		case '+':
		    arec.breakHere("we have read a + operator");//!
		    calc.doOperator(binaryOperator.plus);//!		    calc.doOperator(calculatorEngine::plus);
		    break;

		case '-':
		    arec.breakHere("we have read a - operator");//!
		    calc.doOperator(binaryOperator.minus);//!		    calc.doOperator(calculatorEngine::minus);
		    break;

		case '*':
		    arec.breakHere("we have read a * operator");//!
		    calc.doOperator(binaryOperator.multiply);//!		    calc.doOperator(calculatorEngine::multiply);
		    break;

		case '/':
		    arec.breakHere("we have read a / operator");//!
		    calc.doOperator(binaryOperator.divide);//!		    calc.doOperator(calculatorEngine::divide);
		    break;

		case 'p':
		    arec.breakHere("print the current answer");//!
		    arec.out().println (calc.currentMemory());//!		    cout << calc.currentMemory() << endl;
		    break;

		case 'q':
		    arec.breakHere("done");//!
		    return; // quit calculator
	    }
	}
}

			
}//!
