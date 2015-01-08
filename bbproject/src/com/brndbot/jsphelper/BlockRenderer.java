package com.brndbot.jsphelper;

import java.io.IOException;
import java.util.Iterator;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.BlockField;
import com.brndbot.client.BlockStyle;
import com.brndbot.client.ButtonField;
import com.brndbot.client.ImageField;
import com.brndbot.client.LogoField;
//import com.brndbot.client.Model;
import com.brndbot.client.ModelField;
import com.brndbot.client.Promotion;
import com.brndbot.client.SVGField;
import com.brndbot.client.StyleSet;
import com.brndbot.client.TextField;
import com.brndbot.client.Style;
import com.brndbot.client.TextStyle;
//import com.brndbot.client.ModelField.StyleType;

/**
 * A BlockRenderer takes the information in a Promotion and generates a
 * div which will display it. Each field type has its own renderer.
 */
public class BlockRenderer extends Renderer {

	final static Logger logger = LoggerFactory.getLogger(BlockRenderer.class);
	
	private String promoName;
	private Promotion promotion;
	private StyleSet styleSet;

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
				switch (field.getStyleType ()) {
				case TEXT:
					renderText((TextField)field, div, styl);
					break;
				case IMAGE:
					renderImage((ImageField)field, div, styl);
					break;
				case LOGO:
					renderLogo((LogoField)field, div, styl);
					break;
				case SVG:
					renderSVG((SVGField)field, div, styl);
					break;
				case BLOCK:
					renderBlock((BlockField) field, div, styl);
					break;
				case BUTTON:
					renderButton((ButtonField) field, div, styl);
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
	private void renderText (TextField field, Element div, Style styl) throws IOException {
		logger.debug ("renderText");
		TextStyle tStyle = (TextStyle) styl;
		if (tStyle == null)
			logger.error ("Null style!");
		CSSBuilder cssBuilder = new CSSBuilder ();
		// Set some fixed values for the moment FIXME
		cssBuilder.setFont ("serif");
		cssBuilder.setPointSize(tStyle.getPointSize());
		if (tStyle.isBold())
			cssBuilder.setBold();
		if (tStyle.isItalic())
			cssBuilder.setItalic();
		logger.debug ("Constructed CSS: {}", cssBuilder.toString());
		div.setAttribute ("style", cssBuilder.toString ());
		div.setText (field.getText());
		logger.debug ("Completed div for text");
		outputter.output (div, writer);
	}
	
	private void renderImage (ImageField field, Element div, Style styl) throws IOException {
		Element img = new Element("img");
		img.setAttribute ("src", field.getImagePath());
		div.addContent(img);
		outputter.output (div, writer);
	}
	
	private void renderLogo (LogoField field, Element div, Style styl) throws IOException {
		// TODO stub
		outputter.output (div, writer);
	}
	
	private void renderSVG (SVGField field, Element div, Style styl) throws IOException {
		outputter.output (div, writer);
	}
	
	private void renderBlock (BlockField field, Element div, Style styl) throws IOException {
		// TODO stub
		// A block is effectively a div with specified dimensions, a background color,
		// and a Z-index which places it behind other elements.
		BlockStyle bStyle = (BlockStyle) styl;
		Element blockDiv = new Element ("div");
		CSSBuilder cssBuilder = new CSSBuilder ();
		cssBuilder.setColor (field.getColor());
		cssBuilder.setZIndex(-1);		// put it behind non-blocks
		outputter.output (div, writer);
	}

	private void renderButton (ButtonField field, Element div, Style styl) throws IOException {
		// TODO stub
		outputter.output (div, writer);
	}
	
	/** Create the CSS needed to position the element. */
	private void calcPositionCSS (ModelField field, CSSBuilder cssBuilder) {
		ModelField.AnchorType anchorType = field.getAnchorType ();
		if (anchorType == null) {
			logger.warn ("No anchor type found");
		}
		int x = field.getXOffset();
		int y = field.getYOffset();
		switch (anchorType) {
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
		
}
