package org.example.domain.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GhostModel extends ActorModel {
    public GhostModel(int size) {
        super(size);
        setSpeed(300);
        try {
            URL imageUrl = getClass().getClassLoader().getResource("ghost.png");
            if (imageUrl != null) {
                BufferedImage image = ImageIO.read(imageUrl);
                Image newImage = image.getScaledInstance(size, size, Image.SCALE_DEFAULT);
//                JLabel imageLabel = new JLabel(new ImageIcon(newImage));
//                imageLabel.setSize(new Dimension(size, size));
//                imageLabel.setOpaque(false);
                setImage(newImage);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
