package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.WrongFileFormatException;

import algorithm.AbstractAlgorithm;

public class ClusteringFrame extends JFrame {

	private static final long serialVersionUID = -2655355037183597217L;
	private final JLabel errorLabel;
	private final DrawingPanel drawingPanel;
	private final ClusterOverviewPanel clusterPanel;
	private final SettingsPanel settingsPanel;
	private final TextPrintingPanel printPanel;
	
	public ClusteringFrame() {
		this.drawingPanel = new DrawingPanel(this, 400, 400);
		this.errorLabel = new JLabel("");
		this.printPanel = new TextPrintingPanel();
		this.clusterPanel = new ClusterOverviewPanel(this);
		this.settingsPanel = new SettingsPanel(this);
		this.initialize();
	}
	
	private void initialize() {
		this.setTitle("Super imba Clusteringstuff! HOT!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.drawingPanel, BorderLayout.CENTER);
		
		this.add(this.clusterPanel, BorderLayout.EAST);
		
		this.errorLabel.setVisible(false);
		this.add(this.errorLabel, BorderLayout.NORTH);
		this.errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.printPanel.setVisible(false);
		this.printPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.printPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.PAGE_AXIS));
		botPanel.add(this.settingsPanel);
		botPanel.add(this.printPanel);
		this.add(botPanel, BorderLayout.SOUTH);
		
		this.settingsPanel.revalidate();
		this.settingsPanel.repaint();
		
		this.drawingPanel.addMouseListener(new MouseListener() {
			private int clickedAtX;
			private int clickedAtY;
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ClusteringFrame.this.getDrawingPanel().getController().viewMoved(arg0.getX() - this.clickedAtX, arg0.getY() - this.clickedAtY);
			}
			public void mousePressed(MouseEvent arg0) {
				this.clickedAtX = arg0.getX();
				this.clickedAtY = arg0.getY();
			}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		this.drawingPanel.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				if (arg0.getWheelRotation() == 1) {
					ClusteringFrame.this.getDrawingPanel().getController().zoomChange(false);
				} else {
					ClusteringFrame.this.getDrawingPanel().getController().zoomChange(true);
				}
			}
		});
		
		this.drawingPanel.setDropTarget(new DropTarget(this.drawingPanel, new DropTargetListener() {
			@Override
			public void dropActionChanged(DropTargetDragEvent arg0) {}
			@SuppressWarnings("unchecked")
			@Override
			public void drop(DropTargetDropEvent arg0) {
				Transferable t = arg0.getTransferable();
				if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					arg0.acceptDrop(arg0.getDropAction());
					try {
						List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
						for (File f : files) {
							if (f.getName().endsWith(".json")) {
								ClusteringFrame.this.getSettingsPanel().getController().handleJsonImport(f);
							} else {
								ClusteringFrame.this.getSettingsPanel().getController().handleException(new WrongFileFormatException(f.getName()));
							}
						}
					} catch (UnsupportedFlavorException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			@Override
			public void dragOver(DropTargetDragEvent arg0) {}
			@Override
			public void dragExit(DropTargetEvent arg0) {}
			@Override
			public void dragEnter(DropTargetDragEvent arg0) {}
		}));
		
		this.setVisible(true);
	}

	public void registerController(AbstractAlgorithm algo) {
		algo.register(this.getClusterPanel().getController());
		algo.register(this.getDrawingPanel().getController());
	}

	public ClusterOverviewPanel getClusterPanel() {
		return clusterPanel;
	}

	public SettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public DrawingPanel getDrawingPanel() {
		return drawingPanel;
	}

	public TextPrintingPanel getPrintPanel() {
		return printPanel;
	}
}
