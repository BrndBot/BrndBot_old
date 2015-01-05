package com.brndbot.dummyclient;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.ModelField.StyleType;
import com.brndbot.client.PromotionPrototype;
import com.brndbot.client.TextField;

/**
 * A super-simple client interface for testing and illustrative purposes.
 */
public class DummyClientInterface implements ClientInterface {

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
	public List<PromotionPrototype> getPromotionPrototypes(String modelName) {
		logger.debug ("getPromotionPrototypes, modelName = {}", modelName);
		Model m = mCollection.getModelByName (modelName);
		List<PromotionPrototype> plist = new ArrayList<>();
		try {
			if ("Coaches".equals (modelName)) {
				logger.debug ("Adding coaches");
				PromotionPrototype coach1 = 
						new PromotionPrototype ("Knute Rockne", m, null);
				logger.debug ("Created coach1");
				TextField nameField = (TextField) coach1.getNamedField ("Name");
				if (nameField == null)
					logger.error ("Name field is null!");
				nameField.setText ("Knute Rockne");
				plist.add (coach1);
				PromotionPrototype coach2 =
						new PromotionPrototype ("Vince Lombardi", m, null);
				nameField = (TextField) coach2.getNamedField ("Name");
				nameField.setText ("Vince Lombardi");
				plist.add (coach2);
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
		model1.addField ("title", StyleType.TEXT);
		model1.addField ("Date", StyleType.TEXT);
		mCollection.addModel(model1);
		
		Model model2 = new Model("Coaches", "Coaches");
		model2.setCategory ("People");
		model2.setButtonImage("home/teacher.png");
		model2.addField ("Name", StyleType.TEXT);
		model2.addField ("Bio", StyleType.TEXT);
		mCollection.addModel(model2);

		Model model3 = new Model("Athlete Spotlight", "Athlete Spotlight");
		model3.setCategory ("People");
		model3.setButtonImage("home/workshops.png");
		model3.addField ("Name", StyleType.TEXT);
		model3.addField ("Bio", StyleType.TEXT);
		mCollection.addModel(model3);

		Model model4 = new Model("Testimonials", "Testimonials");
		model4.setCategory ("People");
		model4.setButtonImage("home/classes.png");
		model4.addField ("Name", StyleType.TEXT);
		model4.addField ("Bio", StyleType.TEXT);
		mCollection.addModel(model4);

	}
}
