package jdbc_study.ui.content;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImageLabel extends JLabel {
	private ImageIcon icon;
	private Image img;
	private String imgPath;
	
	public void setImg(String path) {
		this.imgPath = path;
		this.icon = new ImageIcon(path);
		this.img = icon.getImage();
	}

	public String getImgPath() {
		return imgPath;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (icon != null) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
