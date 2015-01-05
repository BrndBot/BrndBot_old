package com.brndbot.dummyclient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.ModelField.StyleType;
import com.brndbot.client.Promotion;
import com.brndbot.client.TextField;

/**
 * A super-simple client interface for testing and illustrative purposes.
 */
public class DummyClientInterface implements ClientInterface, Serializable {


	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(DummyClientInterface.class);
	
	ModelCollection mCollection;
	
	public DummyClientInterface() {
		initModelCollection ();
	}

	@Override
	public ModelCollection getModels() {
		return mCollection;
	}

	@Override
	public List<Promotion> getPromotionPrototypes(String modelName) {
		logger.debug ("getPromotionPrototypes, modelName = {}", modelName);
		Model m = mCollection.getModelByName (modelName);
		List<Promotion> plist = new ArrayList<>();
		try {
			if ("Coaches".equals (modelName)) {
				logger.debug ("Adding coaches");
				Promotion coach1 = 
						new Promotion ("Knute Rockne", m, null);
				TextField nameField = (TextField) coach1.getNamedField ("Name");
				nameField.setText ("Knute Rockne");
				TextField descField = (TextField) coach1.getNamedField ("Description");
				descField.setText ("Knute Kenneth Rockne (March 4, 1888 – March 31, 1931) was an American football player and coach, both at the University of Notre Dame.");
				plist.add (coach1);
				Promotion coach2 =
						new Promotion ("Vince Lombardi", m, null);
				nameField = (TextField) coach2.getNamedField ("Name");
				nameField.setText ("Vince Lombardi");
				descField = (TextField) coach2.getNamedField ("Description");
				descField.setText ("Vincent Thomas \"Vince\" Lombardi (June 11, 1913– September 3, 1970) was an American football player, coach, and executive. He is best known as the head coach of the Green Bay Packers during the 1960s.");
				plist.add (coach2);
			}
			if ("Athlete Spotlight".equals (modelName)) {
				Promotion athlete1 =
						new Promotion ("Ted Williams", m, null);
				TextField nameField = (TextField) athlete1.getNamedField ("Name");
				nameField.setText ("Ted Williams");
				plist.add (athlete1);
			}
			if ("WOD".equals (modelName)) {
				Promotion wod1 = new Promotion ("Extreme workout", m, null);
				TextField titleField = (TextField) wod1.getNamedField ("Title");
				titleField.setText ("Extreme workout");
				plist.add (wod1);
			}
			if ("Testimonials".equals (modelName)) {
				Promotion tst1 = new Promotion ("Hall of Fame", m, null);
				TextField nameField = (TextField) tst1.getNamedField ("Name");
				nameField.setText("Hall of Fame");
				plist.add (tst1);
			}
		} catch (Exception e) {
			logger.error ("Error creating prototypes: {}", e.getClass().getName());
		}
		logger.debug ("Returning list");
		return plist;
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
