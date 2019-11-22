package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
	    double a = 0.1f;
	    for(int i = 0; i < 1000000; i++){
	        a += (double)0.1f;
	    }
        System.out.println(a);
	}
}
