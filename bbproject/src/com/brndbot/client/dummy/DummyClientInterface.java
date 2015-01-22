package com.brndbot.client.dummy;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.brndbot.client.BlockField;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.ImageField;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.style.Style.StyleType;
import com.brndbot.client.Promotion;
import com.brndbot.client.TextField;

/**
 * A super-simple client interface for testing and illustrative purposes.
 * 
 * NOTE: This implementation piles a lot of data into the session attributes.
 * This should be AVOIDED in real modules, since it can slow down performance,
 * as well as running into problems where shared references to an object turn
 * into distinct objects. It's done this way here just to be quick and dirty.
 */
public class DummyClientInterface implements ClientInterface, Serializable {

	private static final String CLIENT_INTERFACE_NAME = "DummyClient";
	
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(DummyClientInterface.class);
	
	/** TODO For a robust module, the ModelCollection should not be part of the
	 *  serializable data. 
	 */
	private ModelCollection mCollection;
	
	/** Map of model names to maps of promotion names to promotion prototypes */
	private Map<String, Map<String, Promotion>> promotionPrototypes;
	
	public DummyClientInterface() {
		promotionPrototypes = new HashMap<>();
		initModelCollection ();
	}

	
//	public ModelCollection getModels() {
//		return mCollection;
//	}
	
	@Override
	public String getName() {
		return CLIENT_INTERFACE_NAME;
	}

	@Override
	public Map<String,Promotion> getPromotionPrototypes(String modelName) {
		if (promotionPrototypes.get(modelName) != null)
				return promotionPrototypes.get(modelName);
		
		logger.debug ("getPromotionPrototypes, modelName = {}", modelName);
		Model m = mCollection.getModelByName (modelName);
		if (m == null) {
			logger.error ("Model {} not found", modelName);
			return null;
		}
		Map<String,Promotion> pmap = new HashMap<>();
		try {
			if ("Coaches".equals (modelName)) {
				logger.debug ("Adding coaches");
				String coachName = "KnuteRockne";
				Promotion coach1 = 
						new Promotion (coachName, m, null);
				TextField nameField = (TextField) coach1.getNamedField ("Name");
				nameField.setText ("Knute Rockne");
				TextField descField = (TextField) coach1.getNamedField ("Description");
				descField.setText ("Knute Kenneth Rockne (March 4, 1888 – March 31, 1931) was an American football player and coach, both at the University of Notre Dame.");
				ImageField imgField = (ImageField) coach1.getNamedField ("Picture");
				imgField.setImagePath("images/Knute_Rockne.jpg");
				//BlockField blkField = (BlockField) coach1.getNamedField ("Block");
				pmap.put (coachName, coach1);
				coachName = "VinceLombardi";
				Promotion coach2 =
						new Promotion (coachName, m, null);
				nameField = (TextField) coach2.getNamedField ("Name");
				nameField.setText ("Vince Lombardi");
				descField = (TextField) coach2.getNamedField ("Description");
				descField.setText ("Vincent Thomas \"Vince\" Lombardi (June 11, 1913– September 3, 1970) was an American football player, coach, and executive. He is best known as the head coach of the Green Bay Packers during the 1960s.");
				imgField = (ImageField) coach2.getNamedField ("Picture");
				imgField.setImagePath("images/Vince_Lombardi.png");
				pmap.put (coachName, coach2);
			}
			if ("Athlete Spotlight".equals (modelName)) {
				String athName = "TedWilliams";
				Promotion athlete1 =
						new Promotion (athName, m, null);
				TextField nameField = (TextField) athlete1.getNamedField ("Name");
				nameField.setText ("Ted Williams");
				pmap.put (athName, athlete1);
			}
			if ("WOD".equals (modelName)) {
				String wName = "Extreme";
				Promotion wod1 = new Promotion (wName, m, null);
				TextField titleField = (TextField) wod1.getNamedField ("Title");
				titleField.setText ("Extreme workout");
				pmap.put (wName, wod1);
			}
			if ("Testimonials".equals (modelName)) {
				String tName = "HallOfFame";
				Promotion tst1 = new Promotion (tName, m, null);
				TextField nameField = (TextField) tst1.getNamedField ("Name");
				nameField.setText("Hall of Fame");
				pmap.put (tName, tst1);
			}
		} catch (Exception e) {
			logger.error ("Error creating prototypes: {}", e.getClass().getName());
			e.printStackTrace ();
		}
		promotionPrototypes.put (modelName, pmap);
		return pmap;
	}

	
	/* Hard-code some models */
	private void initModelCollection () {
		mCollection = new ModelCollection ();
		mCollection.addCategory ("Activity");
		mCollection.addCategory ("People");
		
		Model model1 = new Model("WOD", "Workout of the Day");
		model1.setCategory ("Activity");
		model1.setButtonImage("home/schedule.png");
		model1.addField ("Title", StyleType.TEXT);
		model1.addField ("Date", StyleType.TEXT);
		model1.addField ("Description", StyleType.TEXT);
		mCollection.addModel(model1);
		
		Model model2 = new Model("Coaches", "Coaches");
		model2.setCategory ("People");
		model2.setButtonImage("home/teacher.png");
		model2.addField ("Name", StyleType.TEXT);
		model2.addField ("Description", StyleType.TEXT);
		model2.addField ("Picture", StyleType.IMAGE);
		model2.addField ("Block", StyleType.BLOCK);
		mCollection.addModel(model2);

		Model model3 = new Model("Athlete Spotlight", "Athlete Spotlight");
		model3.setCategory ("People");
		model3.setButtonImage("home/workshops.png");
		model3.addField ("Name", StyleType.TEXT);
		model3.addField ("Description", StyleType.TEXT);
		mCollection.addModel(model3);

		Model model4 = new Model("Testimonials", "Testimonials");
		model4.setCategory ("People");
		model4.setButtonImage("home/classes.png");
		model4.addField ("Name", StyleType.TEXT);
		model4.addField ("Description", StyleType.TEXT);
		mCollection.addModel(model4);

	}
}
