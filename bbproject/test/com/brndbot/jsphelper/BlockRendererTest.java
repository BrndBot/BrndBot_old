package com.brndbot.jsphelper;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.Promotion;
import com.brndbot.client.style.BlockStyle;
import com.brndbot.client.style.ImageStyle;
import com.brndbot.client.style.SVGStyle;
import com.brndbot.client.style.Style.Anchor;
import com.brndbot.client.style.Style.StyleType;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.style.TextStyle;

public class BlockRendererTest {

	private ModelCollection mCollection;
	private StyleSet styleSet;
	private Model theModel;
	
	@Before
	public void setUp() throws Exception {
		initModelCollection ();
		initStyleSet ();
	}
	
	
	@Test
	public void testRender() {
		Promotion promo = initPromotion ();
		BlockRenderer renderer = new BlockRenderer (promo);
		String result = renderer.render();
		assertTrue (result != null);
	}

	/* Hard-code some models */
	private void initModelCollection () {
		mCollection = new ModelCollection ();
		mCollection.addCategory ("People");
		
		theModel = new Model("Coaches", "Coaches");
		theModel.setCategory ("People");
		theModel.setButtonImage("home/teacher.png");
		theModel.addField ("Name", StyleType.TEXT);
		theModel.addField ("SVG", StyleType.SVG);
		theModel.addField ("Picture", StyleType.IMAGE);
		theModel.addField ("Block", StyleType.BLOCK);
		mCollection.addModel(theModel);


	}
	
	/* Hard-code a style set */
	private void initStyleSet () {
		// The styles must correspond 1-to-1 to the model
		styleSet = new StyleSet("ss1");
		styleSet.setWidth(400);
		styleSet.setHeight(360);
		styleSet.setOrganization ("org");
		styleSet.setBrand ("brand");
		styleSet.setModel (theModel.getName());
		
		TextStyle nameStyle = new TextStyle ();
		nameStyle.setWidth (100);
		nameStyle.setHeight (50);
		nameStyle.setOffsetX(10);
		nameStyle.setOffsetY(20);
		nameStyle.setAnchor(Anchor.TOP_LEFT);
		nameStyle.setPointSize (10);
		styleSet.addStyle(nameStyle);
		
		SVGStyle svgStyle = new SVGStyle ();
		Element svgelem = new Element ("svg");
		svgStyle.setWidth (180);
		svgStyle.setHeight(180);
		svgStyle.setOffsetX(0);
		svgStyle.setOffsetY(0);
		svgStyle.setAnchor(Anchor.BOTTOM_RIGHT);
		svgStyle.setSVG(svgelem);
		styleSet.addStyle(svgStyle);

		ImageStyle imgStyle = new ImageStyle ();
		imgStyle.setWidth (180);
		imgStyle.setHeight(180);
		imgStyle.setOffsetX(0);
		imgStyle.setOffsetY(0);
		imgStyle.setAnchor(Anchor.BOTTOM_RIGHT);
		imgStyle.setImagePath("foo.jpg");
		styleSet.addStyle(imgStyle);
		
		BlockStyle blkStyle = new BlockStyle ();
		blkStyle.setWidth (180);
		blkStyle.setHeight(180);
		blkStyle.setOffsetX(0);
		blkStyle.setOffsetY(0);
		blkStyle.setColor("#00FFEE");
		blkStyle.setAnchor(Anchor.BOTTOM_RIGHT);

		styleSet.addStyle(blkStyle);
	}
	
	private Promotion initPromotion () {
		Promotion promo = new Promotion("promo1", theModel, styleSet);
		return promo;
	}
}
