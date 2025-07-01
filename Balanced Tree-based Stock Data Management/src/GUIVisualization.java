import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUIVisualization class which represents a GUIVisualization.
 *
 * @file GUIVisualization.java
 * @author Akif Safa Angi
 * @brief Represents a GUIVisualization that draw a graph.
 * @version 1.0
 * @date 2024-05-23
 */
public class GUIVisualization extends JFrame {
    private List<Integer> dataPointsX; // List to store x-axis data points
    private List<Long> addTimes; // List to store add times
    private List<Long> searchTimes; // List to store search times
    private List<Long> updateTimes; // List to store update times
    private List<Long> removeTimes; // List to store remove times
    private String plotType; // Type of plot ("line" or "scatter")

    public GUIVisualization(String plotType) {
        this.plotType = plotType; // Set the plot type
        this.dataPointsX = new ArrayList<>(); // Initialize x-axis data points list
        this.addTimes = new ArrayList<>(); // Initialize add times list
        this.searchTimes = new ArrayList<>(); // Initialize search times list
        this.updateTimes = new ArrayList<>(); // Initialize update times list
        this.removeTimes = new ArrayList<>(); // Initialize remove times list
        setTitle("Performance Graph Visualization"); // Set the title of the window
        setSize(1600, 1200); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        setLocationRelativeTo(null); // Center the window on the screen
    }

    public void addDataPoint(int x, long addTime, long searchTime, long updateTime, long removeTime) {
        dataPointsX.add(x);
        addTimes.add(addTime);
        searchTimes.add(searchTime);
        updateTimes.add(updateTime);
        removeTimes.add(removeTime);
        repaint(); // Repaint the GUI to show new data point
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass's paint method
        int width = getWidth();
        int height = getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        drawGraph(g, "ADD", dataPointsX, addTimes, Color.BLUE, plotType, 0, 0, halfWidth, halfHeight);
        drawGraph(g, "SEARCH", dataPointsX, searchTimes, Color.RED, plotType, halfWidth, 0, halfWidth, halfHeight);
        drawGraph(g, "UPDATE", dataPointsX, updateTimes, Color.GREEN, plotType, 0, halfHeight, halfWidth, halfHeight);
        drawGraph(g, "REMOVE", dataPointsX, removeTimes, Color.ORANGE, plotType, halfWidth, halfHeight, halfWidth, halfHeight);
    }

    private void drawGraph(Graphics g, String label, List<Integer> dataPointsX, List<Long> times, Color color, String plotType, int startX, int startY, int graphWidth, int graphHeight) {
        int padding = 50;
        int labelPadding = 20;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(startX + padding + labelPadding, startY + padding, graphWidth - 2 * padding - labelPadding, graphHeight - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // Create hatch marks and grid lines for y axis.
        int numberYDivisions = 10;
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = startX + padding + labelPadding;
            int x1 = startX + graphWidth - padding;
            int y0 = startY + graphHeight - ((i * (graphHeight - padding * 2 - labelPadding)) / numberYDivisions + padding);
            int y1 = y0;
            if (times.size() > 0) {
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawLine(x0 + 1 + labelPadding, y0, x1, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMaxYValue(times) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
        }

        // Create hatch marks and grid lines for x axis.
        for (int i = 0; i < dataPointsX.size(); i++) {
            if (dataPointsX.size() > 1) {
                int x0 = startX + i * (graphWidth - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = startY + graphHeight - padding - labelPadding;
                int y1 = y0 - 4;
                if ((i % ((int) ((dataPointsX.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(Color.LIGHT_GRAY);
                    g2.drawLine(x0, y0 - 1 - labelPadding, x1, startY + padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = dataPointsX.get(i) + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // Draw axis lines.
        g2.drawLine(startX + padding + labelPadding, startY + graphHeight - padding - labelPadding, startX + padding + labelPadding, startY + padding);
        g2.drawLine(startX + padding + labelPadding, startY + graphHeight - padding - labelPadding, startX + graphWidth - padding, startY + graphHeight - padding - labelPadding);

        g2.setColor(color);
        if (plotType.equals("line")) {
            for (int i = 0; i < dataPointsX.size() - 1; i++) {
                int x1 = startX + i * (graphWidth - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y1 = startY + graphHeight - padding - labelPadding - (int) ((times.get(i) * 1.0) / getMaxYValue(times) * (graphHeight - padding * 2 - labelPadding));
                int x2 = startX + (i + 1) * (graphWidth - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y2 = startY + graphHeight - padding - labelPadding - (int) ((times.get(i + 1) * 1.0) / getMaxYValue(times) * (graphHeight - padding * 2 - labelPadding));
                g2.drawLine(x1, y1, x2, y2);
            }
        } else if (plotType.equals("scatter")) {
            for (int i = 0; i < dataPointsX.size(); i++) {
                int x = startX + i * (graphWidth - padding * 2 - labelPadding) / (dataPointsX.size() - 1) + padding + labelPadding;
                int y = startY + graphHeight - padding - labelPadding - (int) ((times.get(i) * 1.0) / getMaxYValue(times) * (graphHeight - padding * 2 - labelPadding));
                g2.fillOval(x - 3, y - 3, 6, 6);
            }
        }

        // Draw label
        g2.drawString(label, startX + padding, startY + padding - 10);
    }

    private long getMaxYValue(List<Long> times) {
        long max = Long.MIN_VALUE;
        for (Long y : times) {
            max = Math.max(max, y);
        }
        return max;
    }
}
