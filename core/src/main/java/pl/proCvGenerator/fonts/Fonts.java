package pl.proCvGenerator.fonts;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

public class Fonts {

    public static final Font UBUNTU_NORMAL = FontFactory.getFont("fonts/Ubuntu-Light.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL);
    public static final Font UBUNTU_BOLD = FontFactory.getFont("fonts/Ubuntu-Light.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.BOLD);

}
