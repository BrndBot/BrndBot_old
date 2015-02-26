package com.brndbot.jsphelper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.BlockField;
import com.brndbot.client.style.BlockStyle;
import com.brndbot.client.ButtonField;
import com.brndbot.client.ImageField;
import com.brndbot.client.style.ImageStyle;
import com.brndbot.client.LogoField;
import com.brndbot.client.style.LogoStyle;
//import com.brndbot.client.Model;
import com.brndbot.client.ModelField;
import com.brndbot.client.Promotion;
import com.brndbot.client.SVGField;
import com.brndbot.client.style.SVGStyle;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.TextField;
import com.brndbot.client.style.Style;
import com.brndbot.client.style.TextStyle;
import com.brndbot.client.style.TextStyle.Alignment;
//import com.brndbot.client.ModelField.StyleType;
import com.brndbot.db.Palette;

/**
 * A BlockRenderer takes the information in a Promotion and generates a
 * div which will display it. Each field type has its own renderer.
 * 
 * We use explicit CSS rather than class attributes, since we want the
 * promotion to be exportable to another environment.
 */
public class BlockRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(BlockRenderer.class);
	
	private String promoName;
	private Promotion promotion;
	private StyleSet styleSet;
	private List<Palette> palettes;

	public BlockRenderer(Promotion promo) {
		super ();
		promoName = promo.getName ();
		promotion = promo;
		styleSet = promo.getStyleSet();
		if (styleSet == null)
			logger.error ("styleSet is null");
		else if (styleSet.getStyles() == null)
			logger.error ("styleSet has no style");
	}
	
	public void setPaletteArray (List<Palette> pa) {
		palettes = pa;
	}
	
	public String render () {
		try {
			logger.debug ("Starting render");
			// The number of styles needs to equal the number of
			// model fields. 
			Iterator<Style> stylIter = styleSet.getStyles().iterator();
			for (ModelField field : promotion.getContent()) {
				logger.debug ("Processing field {}", field.getClass().getName());
				Element div = new Element("div");
				div.setAttribute ("id", promoName + "-" + field.getName ());
				Style styl = stylIter.next();
				CSSBuilder cssBuilder = new CSSBuilder ();
				calcPositionCSS(field, styl, cssBuilder);
				cssBuilder.setWidth (styl.getWidth());
				cssBuilder.setHeight (styl.getHeight());
				switch (field.getStyleType ()) {
				case TEXT:
					renderText((TextField)field, div, styl, cssBuilder);
					break;
				case IMAGE:
					renderImage((ImageField)field, div, styl, cssBuilder);
					break;
				case LOGO:
					renderLogo((LogoField)field, div, styl, cssBuilder);
					break;
				case SVG:
					renderSVG((SVGField)field, div, styl, cssBuilder);
					break;
				case BLOCK:
					renderBlock((BlockField) field, div, styl, cssBuilder);
					break;
				case BUTTON:
					renderButton((ButtonField) field, div, styl, cssBuilder);
					break;
				}
			}
			return writer.toString ();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error ("Exception in render: {}", e.getClass().getName());
		}
		return null;
	}

	/** Do I really want to do all the messy rendering here, where I have to
	 *  deal with HTML as raw text? Or is there a way to feed the block to
	 *  the JavaScript, where it can use all the power of JQuery?
	 *  
	 *  Or another idea ... use JDOM to build the division and then render it
	 *  into XHTML fragments! But the CSS style is still a mess. Write a CSS
	 *  builder as well.
	 *  
	 *  FOR NOW ... don't worry about where it goes or how it looks. Just render it.
	 */
	private void renderText (TextField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		logger.debug ("renderText");
		TextStyle tStyle = (TextStyle) styl;
		if (tStyle == null)
			logger.error ("Null text style");
		div.setAttribute("class", "prmf_text");
		// Set some fixed values for the moment FIXME
		cssBuilder.setFont ("serif");
		cssBuilder.setPointSize(tStyle.getPointSize());
		if (tStyle.isBold())
			cssBuilder.setBold();
		if (tStyle.isItalic())
			cssBuilder.setItalic();
		String color = tStyle.getColor();
		if (color != null)
			cssBuilder.setColor (color);
		Alignment align = tStyle.getAlignment();
		if (align != null)
			cssBuilder.setTextAlign(align);
		logger.debug ("Constructed CSS: {}", cssBuilder.toString());
		div.setText (field.getText());
		logger.debug ("Completed div for text");
		div.setAttribute ("style", cssBuilder.toString ());
		outputter.output (div, writer);
	}
	
	private void renderImage (ImageField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		div.setAttribute("class", "prmf_image");
		Element img = new Element("img");
		CSSBuilder imgCSSBuilder = new CSSBuilder();
		imgCSSBuilder.setOpacity (styl.getOpacity());
		imgCSSBuilder.limitScaling();
		img.setAttribute ("style", imgCSSBuilder.toString());
//		ImageStyle imgStyle = (ImageStyle) styl;
		img.setAttribute ("src", field.getImagePath());
		div.addContent(img);
		div.setAttribute ("style", cssBuilder.toString ());
		outputter.output (div, writer);
	}
	
	private void renderLogo (LogoField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		div.setAttribute("class", "prmf_logo");
		LogoStyle logoStyle = (LogoStyle) styl;
		if (logoStyle == null)
			logger.error ("Null logo style");
		// TODO more stuff
		div.setAttribute ("style", cssBuilder.toString ());
		outputter.output (div, writer);
	}
	
	private void renderSVG (SVGField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		div.setAttribute("class", "prmf_svg");
		SVGStyle svgStyle = (SVGStyle) styl;
		if (svgStyle == null)
			logger.error ("Null SVG style");
		div.addContent (field.getSVG());
		div.setAttribute ("style", cssBuilder.toString ());
		outputter.output (div, writer);
	}
	
	private void renderBlock (BlockField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		// A block is effectively a div with specified dimensions and a background color.
		div.setAttribute("class", "prmf_block");
		BlockStyle bStyle = (BlockStyle) styl;
		int paletteSel = bStyle.getPaletteSelection ();
		String color = null;
		if (paletteSel < 0) {
			color = bStyle.getColor();
		}
		else {
			color = paletteColor (paletteSel);
		}
		if (color != null)
			cssBuilder.setBackgroundColor (color);
		div.setAttribute ("style", cssBuilder.toString ());
		outputter.output (div, writer);
	}

	private void renderButton (ButtonField field, Element div, Style styl, CSSBuilder cssBuilder) 
			throws IOException {
		// TODO stub
		div.setAttribute("class", "prmf_button");
		div.setAttribute ("style", cssBuilder.toString ());
		Element btn = new Element ("button");
		btn.setAttribute ("type", "submit");
		div.addContent (btn);
		outputter.output (div, writer);
	}
	
	/** Create the CSS needed to position the element. */
	private void calcPositionCSS (ModelField field, Style styl, CSSBuilder cssBuilder) {
		Style.Anchor anchor = styl.getAnchor ();
		if (anchor == null) {
			logger.warn ("No anchor type found");
			return;
		}
		int x = styl.getOffsetX();
		int y = styl.getOffsetY();
		switch (anchor) {
		case TOP_LEFT:
			cssBuilder.setTopLeftAnchor (x, y);
			break;
		case TOP_RIGHT:
			cssBuilder.setTopRightAnchor (x, y);
			break;
		case BOTTOM_LEFT:
			cssBuilder.setBottomLeftAnchor (x, y);
			break;
		case BOTTOM_RIGHT:
			cssBuilder.setBottomRightAnchor (x, y);
			break;
		}
	}
	
	/* Get a palette color from the style selection. May return null. */
	private String paletteColor (int pidx) {
		if (pidx > 0 && palettes != null && pidx <= palettes.size()) {
			// We have to convert 1-based to 0-based here.
			return palettes.get(pidx - 1).getColor();
		}
		return null;
	}
	
}
