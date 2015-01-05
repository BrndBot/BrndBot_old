package com.brndbot.jsphelper;

import java.io.IOException;
import java.io.StringWriter;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.brndbot.client.BlockField;
import com.brndbot.client.ButtonField;
import com.brndbot.client.ImageField;
import com.brndbot.client.LogoField;
//import com.brndbot.client.Model;
import com.brndbot.client.ModelField;
import com.brndbot.client.Promotion;
import com.brndbot.client.SVGField;
import com.brndbot.client.TextField;
//import com.brndbot.client.ModelField.StyleType;

/**
 * A BlockRenderer takes the information in a Promotion and generates a
 * div which will display it. Each field type has its own renderer.
 */
public class BlockRenderer extends Renderer {

	String promoName;

	public BlockRenderer(Promotion promo) {
		super ();
		promoName = promo.getName ();
		try {
			for (ModelField field : promo.getContent()) {
				Element div = new Element("div");
				div.setAttribute ("id", promoName + "-" + field.getName ());
				switch (field.getStyleType ()) {
				case TEXT:
					renderText((TextField)field, div);
					break;
				case IMAGE:
					renderImage((ImageField)field, div);
					break;
				case LOGO:
					renderLogo((LogoField)field, div);
					break;
				case SVG:
					renderSVG((SVGField)field, div);
					break;
				case BLOCK:
					renderBlock((BlockField) field, div);
					break;
				case BUTTON:
					renderButton((ButtonField) field, div);
					break;
				}
			}
		} catch (Exception e) {}
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
	private void renderText (TextField field, Element div) throws IOException {
		CSSBuilder cssBuilder = new CSSBuilder ();
		// Set some fixed values for the moment FIXME
		cssBuilder.setFont ("serif");
		cssBuilder.setPointSize(12);
		div.setAttribute ("style", cssBuilder.toString ());
		div.setText (field.getText());
		outputter.output (div, writer);
	}
	
	private void renderImage (ImageField field, Element div) throws IOException {
		Element img = new Element("img");
		img.setAttribute ("src", field.getImagePath());
		div.addContent(img);
		outputter.output (div, writer);
	}
	
	private void renderLogo (LogoField field, Element div) throws IOException {
		// TODO stub
		outputter.output (div, writer);
	}
	
	private void renderSVG (SVGField field, Element div) throws IOException {
		outputter.output (div, writer);
	}
	
	private void renderBlock (BlockField field, Element div) throws IOException {
		// TODO stub
		outputter.output (div, writer);
	}

	private void renderButton (ButtonField field, Element div) throws IOException {
		// TODO stub
		outputter.output (div, writer);
	}
		
}
