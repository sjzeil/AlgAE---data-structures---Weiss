package edu.odu.cs.cs361.animations;//!

import edu.odu.cs.zeil.AlgAE.ActivationRecord;
import edu.odu.cs.zeil.AlgAE.Animation;

public class VectorUtility {//!

//!	void copy (const vector<int>& a)
	public static void copy(miniVector a)//! 
	{
		ActivationRecord arec = Animation.activate(VectorUtility.class);//!
		arec.refParam("a", a).breakHere("invoke copy constructor");//!
		miniVector c = new miniVector(a);//!		vector<int> c = a;
		arec.var("c", c).breakHere("copied");//!
	}

}
