package pl.proCvGenerator.fonts;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;

public class Fonts {

    public static final Font UBUNTU_NORMAL = FontFactory.getFont("fonts/ubuntu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL);
    public static final Font UBUNTU_BOLD = FontFactory.getFont("fonts/ubuntu.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.BOLD);
    public static final Font TAHOMA_NORMAL = FontFactory.getFont("fonts/tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL);
    public static final Font TAHOMA_BOLD = FontFactory.getFont("fonts/tahoma.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.BOLD);
    public static final Font CALIBRI_NORMAL = FontFactory.getFont("fonts/calibri.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL);
    public static final Font CALIBRI_BOLD = FontFactory.getFont("fonts/calibri.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.BOLD);
    public static final Font VERDANA_NORMAL = FontFactory.getFont("fonts/verdana.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.NORMAL);
    public static final Font VERDANA_BOLD = FontFactory.getFont("fonts/verdana.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10, Font.BOLD);


}
