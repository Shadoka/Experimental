package test;

import java.util.Random;
import java.util.Vector;

import exception.AbstractClusteringException;
import gui.ClusteringFrame;

import metric.EuclideanDistance;

import algorithm.HierarchicalAlgorithm;
import algorithm.KMeansAlgorithm;

import util.AmountLimitCondition;
import util.Point;

public class Test {

	public static void main(String[] args) throws AbstractClusteringException {
		ClusteringFrame frame = new ClusteringFrame();
		frame.setVisible(true);
//		withoutGUI();
	}
	
	private static void withoutGUI() {
		Vector<Point> pointsHier = new Vector<>();
		pointsHier.add(new Point(4,10));
		pointsHier.add(new Point(7,10));
		pointsHier.add(new Point(4,8));
		pointsHier.add(new Point(6,8));
		pointsHier.add(new Point(12,6));
		pointsHier.add(new Point(10,5));
		pointsHier.add(new Point(11,4));
		pointsHier.add(new Point(9,3));
		pointsHier.add(new Point(12,3));
		pointsHier.add(new Point(3,4));
		pointsHier.add(new Point(5,2));
		pointsHier.add(new Point(2,2));
		
		Vector<Point> pointsMeans = new Vector<>();
		pointsMeans.add(new Point(4,10));
		pointsMeans.add(new Point(7,10));
		pointsMeans.add(new Point(4,8));
		pointsMeans.add(new Point(6,8));
		pointsMeans.add(new Point(12,6));
		pointsMeans.add(new Point(10,5));
		pointsMeans.add(new Point(11,4));
		pointsMeans.add(new Point(9,3));
		pointsMeans.add(new Point(12,3));
		pointsMeans.add(new Point(3,4));
		pointsMeans.add(new Point(5,2));
		pointsMeans.add(new Point(2,2));
		
		Vector<Point> rndPoints1 = generateRandomPoints(1000);
		
//		HierarchicalAlgorithm algo = new HierarchicalAlgorithm(rndPoints1, EuclideanDistance.getInstance(), new AmountLimitCondition(10));
//		algo.cluster();
//		System.out.println(algo);
		
		KMeansAlgorithm kmean = new KMeansAlgorithm(pointsMeans, EuclideanDistance.getInstance(), false, 3);
		kmean.cluster();
		System.out.println(kmean);
		System.out.println(Math.sqrt(2.3));
	}
	
	private static Vector<Point> generateRandomPoints(int n) {
		Vector<Point> result = new Vector<>();
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			result.add(new Point(rnd.nextInt(1000), rnd.nextInt(1000)));
		}
		return result;
	}
}
