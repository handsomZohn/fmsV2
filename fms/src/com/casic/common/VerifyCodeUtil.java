package com.casic.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于生成验证码的工具类，需要初始化，必须先调用paintImg方法，才可以生成img
 *
 */
public class VerifyCodeUtil {
	private static Logger logger = LoggerFactory.getLogger(VerifyCodeUtil.class);
	private BufferedImage buffImg = null;
	private static int width = 80;
	private static int height = 30;
	private static int codeCount = 4;
	private static int x = 0;
	private static int fontHeight;
	private static int codeY;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	static {
		x = width / (codeCount + 2);
		fontHeight = height - 6;
		codeY = height - 6;
	}

	public VerifyCodeUtil() {

	}

	public void initBuffImg() {
		buffImg = new BufferedImage(width, height, 1);
	}

	/**
	 * 用于生成BufferedImage来实例化成员变量buffImg，且返回验证码的存根
	 * @return  verifycode string
	 */
	public String paintImg() {
		initBuffImg();
		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		Font font = new Font("Arial, Helvetica, sans-serif", 0, fontHeight);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawRect(0, 0, width - 1, height - 1);

		StringBuffer randomCode = new StringBuffer();
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int i = 0; i < codeCount; ++i) {
			String strRand = String.valueOf(this.codeSequence[random.nextInt(60)]);

			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);

			randomCode.append(strRand);
		}
		String verifycodeStr = randomCode.toString();
		logger.info("create verifycode string is: " + verifycodeStr);
		return verifycodeStr;
	}

	/**
	 * 获取img成员的实例，需要在调用paintImg方法后有效，否则返回null
	 * @return buffImg (if before perform the paintImg method, making null and void)
	 */
	public BufferedImage getBuffImg() {
		return buffImg;
	}

}
