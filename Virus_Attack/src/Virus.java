
import java.awt.*;
import java.util.ArrayList;

/**
 * The Virus Class is the main unit that the player controls and uses it to
 * conquer the enemy cells and anti viruses
 * 
 * The virus class holds all of its own functions such as updating its location,
 * the coordinate it is headed to, drawing itself and getter methods. extends
 * locatable a interface
 * 
 * @author alexm
 *
 */
public class Virus implements Locatable {

	/**
	 * the current x,y of the virus
	 */
	private int x, y;

	/**
	 * virus stats
	 */
	private int speed, attack, health;

	/**
	 * x and y speed
	 */
	private int vx, vy;

	/**
	 * x, y location of the location it is headed to
	 */
	private int xL, yL;

	/**
	 * width and height of the object
	 */
	private int width, height;

	/**
	 * the radius range in which the virus will attack objects
	 */
	private int attackRadius = 80;

	/**
	 * the group number
	 */
	private int num;

	/**
	 * true if the object is moving, false if not
	 */
	private boolean move = true;

	/**
	 * range of idle movment
	 */
	private int range = 50;

	/**
	 * constructor, takes in the intial x and y coordinate, and instantiates
	 * variables
	 * 
	 * @param x
	 *            x coord
	 * @param y
	 *            y coord
	 */
	public Virus(int x, int y) {

		this.x = x;
		this.y = y;
		speed = 5;
		attack = 1;
		health = 110;
		width = 30;
		height = 30;

	}

	/**
	 * sets new coordiate for the virus to head to
	 * 
	 * @param nx
	 *            the new x
	 * @param ny
	 *            the new y
	 */
	public void setCoord(int nx, int ny) {

		xL = nx;
		yL = ny;
		double hyp = Math.sqrt((xL - x) * (xL - x) + (yL - y) * (yL - y));
		double scale = speed / hyp;
		vx = (int) ((xL - x) * scale);
		vy = (int) ((yL - y) * scale);
		move = true;
	}

	/**
	 * when the virus reaches its assigned location this method makes the virus
	 * move to prevent them from overlapping
	 */
	public void idleMovement() {
		if (getDistance(xL, yL) >= range) {
			vx = -vx;
			vy = -vy;
		}
		x += vx;
		y += vy;
	}

	/**
	 * moves the virus, checks if it has reached the designated location, and if
	 * there is anything to attack in its attack range
	 * 
	 * @param g
	 *            draws lines to show attack using graphics
	 * @param xOffset
	 *            the offset to render the object on the screen according to how
	 *            the screen had shifted
	 * @param yOffset
	 *            same as above
	 */
	public void update(Graphics g, int xOffset, int yOffset) {
		if ((vx < 0 && x < xL) || (vy < 0 && y < yL) || (vy > 0 && y > yL)
				|| (vx > 0 && x > xL)) {
			move = false;
		}
		if (!move) {
			idleMovement();
		} else {
			x += vx;
			y += vy;
		}
		boolean attacking = false;
		for (int i = 0; i < AntiVirusManager.anti.size(); i++) {
			AntiVirus av = AntiVirusManager.anti.get(i);
			if (getDistance(av) <= attackRadius) {
				av.reduceHealth(attack);
				// g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2 - xOffset, y + height / 2 - yOffset,
						av.getX() + av.getWidth() / 2 - xOffset,
						av.getY() + av.getHeight() / 2 - yOffset);
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
				// g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2 - xOffset, y + height / 2 - yOffset,
						c.getX() + c.getRadius() / 2 - xOffset,
						c.getY() + c.getRadius() / 2 - yOffset);
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
				// g = canvas.getGraphics();
				g.setColor(Color.black);
				g.drawLine(x + width / 2 - xOffset, y + height / 2 - yOffset,
						c.getX() + c.getRadius() / 2 - xOffset,
						c.getY() + c.getRadius() / 2 - yOffset);
				WhiteCell wc = (WhiteCell) c;
				wc.setAttacked(true);
				if (c.getHealth() <= 0) {
					CellManager.removeCell(i);
				}
				attacking = true;
				wc.setAttacked(false);
				break;
			}

		}
	}

	/**
	 * draws the virus on the screen
	 * 
	 * @param g
	 *            draws in using this graphics
	 * @param xOffset
	 *            the offset to render the object on the screen according to how
	 *            the screen had shifted
	 * @param yOffset
	 *            same as above
	 */
	public void draw(Graphics g, int xOffset, int yOffset) {
		g.setColor(new Color(122, 122, 0));
		g.fillRect(x - xOffset, y - yOffset, width, height);
		g.setColor(World.BCOLOR);
		g.drawRect((x + width / 4) - xOffset, (y + height / 4) - yOffset,
				width / 2, height / 2);
	}

	/**
	 * checks if the virus is dead or 0 hp
	 * 
	 * @return true if health is 0 false otherwise
	 */
	public boolean isDead() {
		return health == 0;
	}

	/**
	 * reduces the current health by n
	 * 
	 * @param n
	 *            health to reduce by
	 */
	public void reduceHealth(int n) {
		health -= n;
	}

	/**
	 * getter method for width
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * getter method for height
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @see Locatable#getX()
	 */
	public int getX() {
		return x;
	}

	/**
	 * @see Locatable#getY()
	 */
	public int getY() {
		return y;
	}

	/**
	 * @see Locatable#getDistance(Locatable)
	 */
	public double getDistance(Locatable other) {
		double distance = (x - other.getX()) * (x - other.getX())
				+ (y - other.getY()) * (y - other.getY());
		distance = Math.sqrt(distance);
		return distance;
	}

	/**
	 * get distance method for when not using Locatable objects
	 * 
	 * @param xL
	 *            x coord of other point
	 * @param yL
	 *            y coord of other point
	 * @return returns the distance between the two objects as a double
	 */
	public double getDistance(int xL, int yL) {
		double distance = (x - xL) * (x - xL) + (y - yL) * (y - yL);
		distance = Math.sqrt(distance);
		return distance;
	}

}