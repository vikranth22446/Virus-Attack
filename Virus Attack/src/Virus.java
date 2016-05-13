package v2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Virus implements Locatable, Attacker {

	private int x, y; // current loc

	private int prex, prey;

	private int speed, attack, health; // stats

	private int vx, vy;

	private int xL, yL; // location it is headed to

	private int width, height;

	private int attackRadius = 80;

	private int num;
	ArrayList<Line> lines;

	// construct a virus
	public Virus(int x, int y) {

		this.x = x;
		prex = x;
		this.y = y;
		prey = y;
		speed = 5;
		attack = 2;
		health = 6;

		width = 30;
		height = 30;
		lines = new ArrayList<Line>();

	}

	// set a new coordinate for the virus to head to
	public void setCoord(int nx, int ny) {

		xL = nx;
		yL = ny;
		double hyp = Math.sqrt((xL - x) * (xL - x) + (yL - y) * (yL - y));
		double scale = speed / hyp;
		vx = (int) ((xL - x) * scale);
		vy = (int) ((yL - y) * scale);
	}

	
	
	// update the status of the virus
	// Also checks if anything is in attack radius
	public void update(Canvas canvas) {
		x += vx;
		y += vy;

		Graphics g;
		boolean attacking = false;
		for (int i = 0; i < AntiVirusManager.anti.size(); i++) {
			AntiVirus av = AntiVirusManager.anti.get(i);
			if (getDistance(av) <= attackRadius) {
				av.reduceHealth(attack);
				g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2, y + height / 2,
						av.getX() + av.getWidth() / 2,
						av.getY() + av.getHeight() / 2);
				if (av.isDead()) {
					AntiVirusManager.anti.remove(av);
				}
				attacking = true;
				break;
			}

		}

		if (attacking)
			return;

		for (int i = 0; i < CellManager.redValues.size(); i++) {
			Cell c = CellManager.redValues.get(i);
			if (getDistance(c) <= attackRadius && !(c instanceof SickCell)) {
				c.decrementHealth(attack);
				g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2, y + height / 2,
						c.getX() + c.getRadius() / 2, c.getY() + c.getRadius()
								/ 2);
				if (c.getHealth() <= 0) {
					CellManager.convertSick(c);
				}
				attacking = true;

				break;
			}

		}

		if (attacking)
			return;

		for (int i = 0; i < CellManager.whiteValues.size(); i++) {
			Cell c = CellManager.whiteValues.get(i);
			if (getDistance(c) <= attackRadius) {
				c.decrementHealth(attack);
				g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2, y + height / 2,
						c.getX() + c.getRadius() / 2, c.getY() + c.getRadius()
								/ 2);
				if (c.getHealth() <= 0) {
					CellManager.removeCell(i);
				}
				attacking = true;
				break;
			}

		}

	}

	public void draw(Canvas canvas) {
		Graphics g = canvas.getGraphics();
		g.setColor(new Color(122, 122, 0));
		g.fillRect(x, y, width, height);
		g.setColor(World.BCOLOR);
		g.drawRect(x + width / 4, y + height / 4, width / 2, height / 2);
	}

	public boolean isDead() {
		return health == 0;
	}

	public void reduceHealth(int n) {
		health -= n;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getDistance(Locatable other) {
		double distance = (x - other.getX()) * (x - other.getX())
				+ (y - other.getY()) * (y - other.getY());
		distance = Math.sqrt(distance);
		return distance;
	}

	public int getAttackRadius() {
		return attackRadius;
	}
}