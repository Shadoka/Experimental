/*
 * Datei: CoordinateSystem.java
 *
 * Rev.    Datum       Rel.    Anwender                        Aenderung und Grund
 * -------------------------------------------------------------------------------
 * ${RevHist}
 */
package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * TODO: JavaDoc-Kommentar einfügen!
 */
public class CoordinateSystem {

    private Point origin;
    private int width, height;
    private boolean d3;
    private BufferedImage targetImage;
    private Graphics2D graphics;
    private Vector<Point> points;
    private Vector<EuclideanCluster> cluster;
    private static double zoomFactor = 1.0;
    private static int changeX = 0, changeY = 0;
    
    /**
     * Konstruktor der Klasse.
     */
    public CoordinateSystem(Point origin, int width, int height, boolean d3) {
        this.width = width;
        this.height = height;
        this.d3 = false;
        this.origin = origin;
        this.points = new Vector<>();
        this.cluster = new Vector<EuclideanCluster>();
        this.targetImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.graphics = this.targetImage.createGraphics();
        this.graphics.setBackground(Color.WHITE);
        this.graphics.clearRect(0, 0, this.width, this.height);
        this.graphics.setColor(Color.BLACK);
        this.drawSystem(null, null);
    }
    
    private void drawSystem(EuclideanCluster toHighlightC, Point toHighlightP) {
        this.getGraphics().drawLine(5, this.getHeight()/2 + changeY, this.getWidth()-5, this.getHeight()/2 + changeY);
        this.getGraphics().drawLine(this.getWidth()/2 + changeX, 5, this.getWidth()/2 + changeX, this.getWidth()-5);
        this.drawArrowXAxis();
        this.drawArrowYAxis();
        this.drawDistanceLinesXAxis();
        this.drawDistanceLinesYAxis();
        this.drawPoints(toHighlightP);
        this.drawClusterCircle(toHighlightC);
        if (this.isD3()) {
            
        }
    }
    
    public void redraw(int width, int height, EuclideanCluster toHighlightC, Point toHighlightP) {
        this.setWidth(width);
        this.setHeight(height);
        this.setOrigin(new Point(width/2, height/2));
        this.targetImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.graphics = this.targetImage.createGraphics();
        this.graphics.setBackground(Color.WHITE);
        this.graphics.clearRect(0, 0, this.width, this.height);
        this.graphics.setColor(Color.BLACK);
        this.drawSystem(toHighlightC, toHighlightP);
    }
    
    /**
     * We draw a small "x" for each point.
     */
    public void drawPoints(Point toHighlightP) {
    	for (Point p : this.getPoints()) {
    		this.drawPoint(p, Color.BLACK, toHighlightP);
    	}
    }
    
    public void drawPoint(Point p, Color c, Point toHighlightP) {
    	if (p.equals(toHighlightP)) {
    		this.getGraphics().setColor(Color.ORANGE);
    	} else {
    		this.getGraphics().setColor(c);
    	}
    	//bot-left to top-right
    	int startX = (int)(this.getOrigin().getX() + (p.getX()*5 - 1)*zoomFactor + changeX);
    	int startY = (int)(this.getOrigin().getY() - (p.getY()*5 + 1)*zoomFactor + changeY);
    	int endX = (int)(this.getOrigin().getX() + (p.getX()*5 + 1)*zoomFactor + changeX);
    	int endY = (int)(this.getOrigin().getY() - (p.getY()*5 - 1)*zoomFactor + changeY);
		this.getGraphics().drawLine(startX, startY, endX, endY);
		//top-left to bot-right
		startY = (int)(this.getOrigin().getY() - (p.getY()*5 - 1)*zoomFactor + changeY);
		endY = (int)(this.getOrigin().getY() - (p.getY()*5+ 1)*zoomFactor + changeY);
		this.getGraphics().drawLine(startX, startY, endX, endY);
		this.getGraphics().setColor(Color.BLACK);
    }
    
    public void drawClusterCircle(EuclideanCluster toHighlight) {
    	this.getGraphics().setColor(Color.RED);
    	for (EuclideanCluster cluster : this.getCluster()) {
    		if (cluster.equals(toHighlight)) this.getGraphics().setColor(Color.BLUE);
    		int radius = (int) Math.round(cluster.getRealRadius()*5*zoomFactor);
    		int x = (int) Math.round(this.getOrigin().getX() + changeX + cluster.getCentroid().getX()*5*zoomFactor - radius);
    		int y = (int) Math.round(this.getOrigin().getY() + changeY - cluster.getCentroid().getY()*5*zoomFactor - radius); 
    		this.getGraphics().drawOval(x, y, radius*2, radius*2);
    		this.drawPoint(cluster.getCentroid(), Color.GREEN, null);
    		this.getGraphics().setColor(Color.RED);
    	}
    	this.getGraphics().setColor(Color.BLACK);
    }

    private void drawArrowXAxis() {
        this.getGraphics().drawLine(this.getWidth()-20, this.getHeight()/2-10 + changeY, this.getWidth()-5, this.getHeight()/2 + changeY);
        this.getGraphics().drawLine(this.getWidth()-20, this.getHeight()/2+10 + changeY, this.getWidth()-5, this.getHeight()/2 + changeY);
    }
    
    private void drawArrowYAxis() {
        this.getGraphics().drawLine(this.getWidth()/2-10 + changeX, 20, this.getWidth()/2 + changeX, 5);
        this.getGraphics().drawLine(this.getWidth()/2+10 + changeX, 20, this.getWidth()/2 + changeX, 5);
    }
    
    private void drawDistanceLinesXAxis() {
        for (int i = (int) (this.getOrigin().getX()+changeX); i < this.getWidth(); i += 20*zoomFactor) {
            this.graphics.drawLine(i, (int) (this.getHeight()/2-5) + changeY, i, (int) (this.getHeight()/2+5) + changeY);
        }
        for (int i = (int) (this.getOrigin().getX()+changeX); i > 5; i -= 20*zoomFactor) {
            this.graphics.drawLine(i, (int) (this.getHeight()/2-5) + changeY, i, (int) (this.getHeight()/2+5) + changeY);
        }
    }
    
    private void drawDistanceLinesYAxis() {
        for (int i = (int) (this.getOrigin().getY()+changeY); i < this.getWidth(); i += 20*zoomFactor) {
            this.graphics.drawLine((int) (this.getWidth()/2-5) + changeX, i, (int) (this.getWidth()/2+5) + changeX, i);
        }
        for (int i = (int) (this.getOrigin().getY()+changeY); i > 5; i -= 20*zoomFactor) {
            this.graphics.drawLine((int) (this.getWidth()/2-5) + changeX, i, (int) (this.getWidth()/2+5) + changeX, i);
        }
    }
    
    public void testPrint() {
        try {
            ImageIO.write(this.getTargetImage(), "png", new File("C:\\Programme\\myPics\\system.png"));
        } catch (IOException e) {
            throw new RuntimeException("Auto-generated catch block", e);
        }
    }
    
    /**
     * Setzt das Attribut origin.
     * @param origin Der neue Wert für origin
     */
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    /**
     * Gibt das Attribut origin zurück.
     * @return origin
     */
    public Point getOrigin() {
        return this.origin;
    }

    /**
     * Setzt das Attribut width.
     * @param width Der neue Wert für width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gibt das Attribut width zurück.
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Setzt das Attribut height.
     * @param height Der neue Wert für height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gibt das Attribut height zurück.
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Setzt das Attribut d3.
     * @param d3 Der neue Wert für d3
     */
    public void setD3(boolean d3) {
        this.d3 = d3;
    }

    /**
     * Gibt das Attribut d3 zurück.
     * @return d3
     */
    public boolean isD3() {
        return this.d3;
    }



    /**
     * Setzt das Attribut targetImage.
     * @param targetImage Der neue Wert für targetImage
     */
    public void setTargetImage(BufferedImage targetImage) {
        this.targetImage = targetImage;
    }



    /**
     * Gibt das Attribut targetImage zurück.
     * @return targetImage
     */
    public BufferedImage getTargetImage() {
        return this.targetImage;
    }

    /**
     * Setzt das Attribut graphics.
     * @param graphics Der neue Wert für graphics
     */
    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * Gibt das Attribut graphics zurück.
     * @return graphics
     */
    public Graphics2D getGraphics() {
        return this.graphics;
    }

	public Vector<Point> getPoints() {
		return points;
	}

	public void setPoints(Vector<Point> points) {
		this.points = points;
	}

	public Vector<EuclideanCluster> getCluster() {
		return cluster;
	}

	public void setCluster(Vector<EuclideanCluster> cluster) {
		this.cluster = cluster;
	}
	
	public void setZoomFactor(double delta) {
		if (zoomFactor + delta < 6 && zoomFactor + delta > 0) {
			double temp = (zoomFactor + delta)*10;
			zoomFactor = Math.round(temp)/10.0;
		}
	}

	public int getChangeX() {
		return changeX;
	}

	public void setChangeX(int deltaX) {
		changeX += deltaX;
	}
	
	public void setChangeY(int deltaY) {
		changeY += deltaY;
	}
}
