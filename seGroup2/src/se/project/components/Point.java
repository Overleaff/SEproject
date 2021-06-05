package se.project.components;

import java.lang.Math;

public class Point {

	private double x;

	private double y;

	public int clusterNo;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getClusterNo() {
		return clusterNo;
	}

	public double calculateDistance(double x, double y) {
		return Math.sqrt(Math.abs(x*x-y*y));
	}

}
