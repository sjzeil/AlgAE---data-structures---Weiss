package edu.odu.cs.cs361.animations;//!

import edu.odu.cs.AlgAE.Server.MemoryModel.ActivationRecord;
import static edu.odu.cs.AlgAE.Server.Animations.LocalJavaAnimation.activate;//!


public class VectorUtilityNew {//!

//!	void copy (const vector<int>& a)
	public static void copy(VectorNew a)//! 
	{
		ActivationRecord arec = activate(VectorUtilityNew.class);//!
		arec.refParam("a", a).breakHere("invoke copy constructor");//!
		VectorNew c = new VectorNew(a);//!		vector<int> c = a;
		arec.var("c", c).breakHere("copied");//!
	}

}
