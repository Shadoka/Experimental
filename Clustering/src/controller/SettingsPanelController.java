package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import observer.ClusterChangedEvent;
import observer.NewPointsEvent;

import common.StopCondition;
import exception.AbstractGuiException;
import exception.EmptyKFieldException;
import exception.MinGreaterMaxException;
import exception.NoAlgoSelectedException;
import exception.NoNegativeNumbersAllowedException;
import exception.NoPointsGeneratedException;
import exception.NoStopConditionSelectedException;
import exception.OnlyDigitsAllowedException;
import exception.WrongFileFormatException;

import metric.EuclideanDistance;

import util.AmountLimitCondition;
import util.DiameterLimitCondition;
import util.EuclideanCluster;
import util.Point;
import util.RadiusLimitCondition;
import visitor.GuiExceptionVisitor;

import algorithm.AbstractAlgorithm;
import algorithm.Algorithm;
import algorithm.HierarchicalAlgorithm;
import algorithm.KMeansAlgorithm;
import gui.SettingsPanel;

public class SettingsPanelController {

	private final SettingsPanel panel;
	private AbstractAlgorithm algo;
	private Vector<Point> generatedPoints;
	private final static int MAX_X = 1000;
	private final static int MAX_Y = 1000;
	
	public SettingsPanelController(SettingsPanel panel) {
		this.panel = panel;
		this.generatedPoints = new Vector<>();
	}
	
	public void selectHierButton() {
		this.enableAllConditionButtons(true);
		this.getPanel().getkPanel().getkField().setEnabled(false);
		this.getPanel().getkPanel().getHierPreButton().setEnabled(false);
		this.getPanel().getkPanel().getRandomButton().setEnabled(false);
	}
	
	public void selectMeansButton() {
		this.enableAllConditionButtons(false);
		this.disableAllConditionTextFields();
		this.getPanel().getkPanel().getkField().setEnabled(true);
		this.getPanel().getkPanel().getHierPreButton().setEnabled(true);
		this.getPanel().getkPanel().getRandomButton().setEnabled(true);
		this.getPanel().getkPanel().getkField().grabFocus();
	}
	
	private void disableAllConditionTextFields() {
		this.getPanel().getConditionPanel().getAmount().setEnabled(false);
		this.getPanel().getConditionPanel().getRadius().setEnabled(false);
		this.getPanel().getConditionPanel().getDiameter().setEnabled(false);
	}
	
	private void enableAllConditionButtons(boolean enable) {
		this.getPanel().getConditionPanel().getAmountLimit().setEnabled(enable);
		this.getPanel().getConditionPanel().getRadiusLimit().setEnabled(enable);
		this.getPanel().getConditionPanel().getDiameterLimit().setEnabled(enable);
	}
	
	public void selectAmountLimit() {
		this.getPanel().getConditionPanel().getAmount().setEnabled(true);
		this.getPanel().getConditionPanel().getRadius().setEnabled(false);
		this.getPanel().getConditionPanel().getDiameter().setEnabled(false);
		this.getPanel().getConditionPanel().getAmount().grabFocus();
	}
	
	public void selectRadiusLimit() {
		this.getPanel().getConditionPanel().getAmount().setEnabled(false);
		this.getPanel().getConditionPanel().getRadius().setEnabled(true);
		this.getPanel().getConditionPanel().getDiameter().setEnabled(false);
		this.getPanel().getConditionPanel().getRadius().grabFocus();
	}
	
	public void selectDiameterLimit() {
		this.getPanel().getConditionPanel().getAmount().setEnabled(false);
		this.getPanel().getConditionPanel().getRadius().setEnabled(false);
		this.getPanel().getConditionPanel().getDiameter().setEnabled(true);
		this.getPanel().getConditionPanel().getDiameter().grabFocus();
	}
	
	private StopCondition getStopCondition() throws AbstractGuiException {
		if (this.getPanel().getConditionPanel().getAmountLimit().isSelected()) {
			this.validateNumericInputField(this.getPanel().getConditionPanel().getAmount(), 1);
			return new AmountLimitCondition(Integer.parseInt(this.getPanel().getConditionPanel().getAmount().getText()));
		} else if (this.getPanel().getConditionPanel().getRadiusLimit().isSelected()) {
			this.validateNumericInputField(this.getPanel().getConditionPanel().getRadius(), 1);
			return new RadiusLimitCondition(Integer.parseInt(this.getPanel().getConditionPanel().getRadius().getText()));
		} else if (this.getPanel().getConditionPanel().getDiameterLimit().isSelected()) {
			this.validateNumericInputField(this.getPanel().getConditionPanel().getDiameter(), 1);
			return new DiameterLimitCondition(Integer.parseInt(this.getPanel().getConditionPanel().getDiameter().getText()));
		} else {
			throw new NoStopConditionSelectedException();
		}
	}
	
	private void setErrorLabel(String message) {
		this.getPanel().getFrame().getErrorLabel().setForeground(Color.RED);
		this.getPanel().getFrame().getErrorLabel().setText(message);
		this.getPanel().getFrame().getErrorLabel().setVisible(true);
	}
	
	private void borderTextField(JTextField comp) {
		comp.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void handleException(AbstractGuiException e) {
		e.accept(new GuiExceptionVisitor() {
			@Override
			public void handle(OnlyDigitsAllowedException e) {
				SettingsPanelController.this.borderTextField(e.getTxtField());
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(EmptyKFieldException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(NoNegativeNumbersAllowedException e) {
				SettingsPanelController.this.borderTextField(e.getTxtField());
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(NoStopConditionSelectedException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(NoPointsGeneratedException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(NoAlgoSelectedException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
				SettingsPanelController.this.getPanel().getAlgoPanel().setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			@Override
			public void handle(WrongFileFormatException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
			}
			@Override
			public void handle(MinGreaterMaxException e) {
				SettingsPanelController.this.setErrorLabel(e.getMessage());
				SettingsPanelController.this.borderTextField(e.getTxtField());
			}
		});
	}
	
	public void handleSliderEvent(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		this.getPanel().getInputPanel().getNumber().setText(source.getValue()+"");
	}
	
	public void handleGenerateClick() throws AbstractGuiException {
		this.resetBordersAndErrorLabel();
		this.validateNumericInputField(this.getPanel().getInputPanel().getRangeXPanel().getMin(), 0);
		this.validateNumericInputField(this.getPanel().getInputPanel().getRangeXPanel().getMax(), 0);
		this.validateNumericInputField(this.getPanel().getInputPanel().getRangeYPanel().getMin(), 0);
		this.validateNumericInputField(this.getPanel().getInputPanel().getRangeYPanel().getMax(), 0);
		int maxX = Integer.parseInt(this.getPanel().getInputPanel().getRangeXPanel().getMax().getText());
		int minX = Integer.parseInt(this.getPanel().getInputPanel().getRangeXPanel().getMin().getText());
		int maxY = Integer.parseInt(this.getPanel().getInputPanel().getRangeYPanel().getMax().getText());
		int minY = Integer.parseInt(this.getPanel().getInputPanel().getRangeYPanel().getMin().getText());
		int amount = this.getPanel().getInputPanel().getSlider().getValue();
		Vector<Point> points = this.generateRandomPoints(amount, maxX, minX, maxY, minY);
		this.setGeneratedPoints(points);
		Vector<EuclideanCluster> empty = new Vector<>();
		this.getPanel().getFrame().getClusterPanel().getController().updateClusterList(empty);
		this.getPanel().getFrame().getDrawingPanel().getController().getSystem().setCluster(empty);
	}
	
	private Vector<Point> generateRandomPoints(int n, int maxX, int minX, int maxY, int minY) throws AbstractGuiException {
		if (maxX <= minX) throw new MinGreaterMaxException(this.getPanel().getInputPanel().getRangeXPanel().getMin());
		if (maxY <= minY) throw new MinGreaterMaxException(this.getPanel().getInputPanel().getRangeYPanel().getMin());
		int diffX = maxX - minX;
		int diffY = maxY - minY;
		Vector<Point> result = new Vector<>();
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			result.add(new Point(rnd.nextInt(diffX) + minX, rnd.nextInt(diffY) + minY));
		}
		return result;
	}
	
	public void resetBordersAndErrorLabel() {
		this.getPanel().getkPanel().getkField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getConditionPanel().getAmount().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getConditionPanel().getRadius().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getConditionPanel().getDiameter().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getInputPanel().getRangeXPanel().getMin().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getInputPanel().getRangeYPanel().getMin().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.getPanel().getAlgoPanel().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.getPanel().getFrame().getErrorLabel().setVisible(false);
	}
	
	public void createAlgo() throws AbstractGuiException {
		this.resetBordersAndErrorLabel();
		this.getPanel().getFrame().getDrawingPanel().getController().setHighlightedCluster(null);
		this.getPanel().getFrame().getDrawingPanel().getController().setHighlightedPoint(null);
		if (this.getGeneratedPoints().size() == 0) throw new NoPointsGeneratedException();
		if (this.getPanel().getAlgoPanel().getHier().isSelected()) {
			Vector<Point> copy = this.deepClone(this.getGeneratedPoints());
			HierarchicalAlgorithm algo = new HierarchicalAlgorithm(copy, EuclideanDistance.getInstance(), this.getStopCondition());
			this.getPanel().getFrame().registerController(algo);
			algo.cluster();
			algo.notifyObservers(new NewPointsEvent(this.getGeneratedPoints()));
			algo.notifyObservers(new ClusterChangedEvent(algo.getCluster()));
		} else if (this.getPanel().getAlgoPanel().getMeans().isSelected()) {
			this.validateNumericInputField(this.getPanel().getkPanel().getkField(), 1);
			int k = Integer.parseInt(this.getPanel().getkPanel().getkField().getText());
			Vector<Point> copy = this.deepClone(this.getGeneratedPoints());
			KMeansAlgorithm algo = new KMeansAlgorithm(copy, EuclideanDistance.getInstance(), this.getPanel().getkPanel().getHierPreButton().isSelected(), k);
			this.getPanel().getFrame().registerController(algo);
			algo.cluster();
			algo.notifyObservers(new ClusterChangedEvent(algo.getCluster()));
			algo.notifyObservers(new NewPointsEvent(this.getGeneratedPoints()));
		} else {
			throw new NoAlgoSelectedException();
		}
	}
	
	public void handleJsonImport(File file) {
		this.resetBordersAndErrorLabel();
		Gson gson = new Gson();
		try {
			FileInputStream input = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
           
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            JsonArray points = json.getAsJsonArray("points");
            Vector<Point> result = new Vector<>();
            for (int i = 0; i < points.size(); i++) {
            	JsonObject point = points.get(i).getAsJsonObject();
            	result.add(new Point(point.get("x").getAsDouble(), point.get("y").getAsDouble()));
            }
            this.setGeneratedPoints(result);
    		Vector<EuclideanCluster> empty = new Vector<>();
    		this.getPanel().getFrame().getClusterPanel().getController().updateClusterList(empty);
    		this.getPanel().getFrame().getDrawingPanel().getController().getSystem().setCluster(empty);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Vector<Point> deepClone(Vector<Point> points) {
		Vector<Point> result = new Vector<>();
		for (Point p : points) {
			result.add(new Point(p.getX(), p.getY()));
		}
		return result;
	}
	
	private void validateNumericInputField(JTextField inputField, int minValue) throws AbstractGuiException {
		if (inputField.getText() == null) {
			throw new EmptyKFieldException();
		}
		if (inputField.getText().matches("[^0-9]*")) {
			throw new OnlyDigitsAllowedException(inputField);
		}
		int number = Integer.parseInt(inputField.getText());
		if (number < minValue) throw new NoNegativeNumbersAllowedException(inputField);
	}
	
	public SettingsPanel getPanel() {
		return panel;
	}

	public Algorithm getAlgo() {
		return algo;
	}

	public void setAlgo(AbstractAlgorithm algo) {
		this.algo = algo;
	}

	public Vector<Point> getGeneratedPoints() {
		return generatedPoints;
	}

	private void setGeneratedPoints(Vector<Point> generatedPoints) {
		this.generatedPoints = generatedPoints;
		this.getPanel().getFrame().getDrawingPanel().getController().update(new NewPointsEvent(generatedPoints));
	}
}
