package raflack.views.cards;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class RoundedPane extends JPanel {

    private int shadowSize = 2;

    public RoundedPane() {
        // This is very important, as part of the panel is going to be transparent
        setOpaque(false);
    }

    public RoundedPane(LayoutManager mgr){
        super(mgr);
        setOpaque(false);
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 10, 10);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth() - 1;
        int height = getHeight() - 1;

        Graphics2D g2d = (Graphics2D) g.create();
        TestDropShadowBorder.applyQualityProperties(g2d);
        Insets insets = getInsets();
        Rectangle bounds = getBounds();
        bounds.x = insets.left;
        bounds.y = insets.top;
        bounds.width = width - (insets.left + insets.right);
        bounds.height = height - (insets.top + insets.bottom);

        RoundRectangle2D shape = new RoundRectangle2D.Float(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);

        /**
         * * THIS SHOULD BE CACHED AND ONLY UPDATED WHEN THE SIZE OF THE
         * COMPONENT CHANGES **
         */
        BufferedImage img = TestDropShadowBorder.createCompatibleImage(bounds.width, bounds.height);
        Graphics2D tg2d = img.createGraphics();
        TestDropShadowBorder.applyQualityProperties(g2d);
        tg2d.setColor(Color.BLACK);
        tg2d.translate(-bounds.x, -bounds.y);
        tg2d.fill(shape);
        tg2d.dispose();
        BufferedImage shadow = TestDropShadowBorder.generateShadow(img, shadowSize, Color.BLACK, 0.5f);

        g2d.drawImage(shadow, shadowSize, shadowSize, this);

        g2d.setColor(getBackground());
        g2d.fill(shape);

        /**
         * THIS ONE OF THE ONLY OCCASIONS THAT I WOULDN'T CALL
         * super.paintComponent *
         */
        getUI().paint(g2d, this);

        g2d.setColor(Color.GRAY);
        g2d.draw(shape);
        g2d.dispose();
    }
}
