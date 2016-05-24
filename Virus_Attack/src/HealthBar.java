

import java.awt.*;


public class HealthBar {
    Cell cell;

    public HealthBar() {
    }

    public HealthBar(Cell cell) {
        this.healthValue = cell.getHealth();
        this.cell = cell;
    }

    private double healthValue;

    public void updateHealth() {
        healthValue = cell.getHealth();
    }

    public void draw(Graphics graph, int xOffset, int yOffset) {
        int fullPercent = 100;
        if (cell instanceof SickCell) {
            // Graphics graph = canvas.getGraphics();
            graph.setColor(new Color(255, 26, 41));
            graph.fillRect(cell.getX() - 25 - xOffset, cell.getY() - 20 - yOffset, (int) (Math.abs(cell.getHealth() / cell.max()) * 100), 10);
            System.out.println(Math.abs(cell.getHealth() / cell.max()));
            drawOtherHalf(graph, xOffset, yOffset, fullPercent-(int) (Math.abs(cell.getHealth() / cell.max() * 100)),new Color(0, 255, 59));

        } else {
            // Graphics graph = canvas.getGraphics();

            graph.setColor(new Color(0, 255, 59));
            graph.fillRect(cell.getX() - 25 - xOffset, cell.getY() - 20 - yOffset, (int) (Math.abs(cell.getHealth()) / cell.max() * 100), 10);

             drawOtherHalf(graph, xOffset, yOffset, fullPercent-(int) (Math.abs(cell.getHealth()) / cell.max() * 100),new Color(255, 26, 41));
        }
    }

    public void drawOtherHalf(Graphics g,int xOffset, int yOffset, int cellHealth,Color color) {
        // Graphics graph = canvas.getGraphics();

         g.setColor(color);
        g.fillRect((cell.getX() - 25) + (int) (Math.abs(cell.getHealth() / cell.max() * 100))- xOffset, cell.getY() - 20 - yOffset, cellHealth, 10);
    }


}