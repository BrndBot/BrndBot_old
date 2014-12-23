package com.brndbot.promo;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.Model;
import com.brndbot.client.ModelField;
import com.brndbot.client.PromotionPrototype;
import com.brndbot.client.StyleSet;

/** This class defines a Promotion, which includes a model, style,
 *  and content.
 */
public class Promotion extends PromotionPrototype {

	private List<ModelField> content;
	
	/** Factory method to create a Promotion from a specified
	 *  model name. 
	 */
	public static Promotion createPromotion (String modelName) {
		// Here we need access somewhere to the set of available
		// Models so we can grab the model with the specified name.
		return null;		// TODO stub
	}
	
	public Promotion(Model m, StyleSet ss) {
		super(m, ss);
		// TODO Auto-generated constructor stub
	}

	public List<ModelField> getContent () {
		return content;
	}
	
	/** Convert a Promotion to JSON so that JavaScript can use it.
	 *  Return it as a JSONObject, since this might be just part of a bigger
	 *  collection 
	 */
	public JSONObject toJSON () throws JSONException {
		JSONObject val = new JSONObject();
		val.put ("modelName", model.getName());
		val.put ("styleSetName", styleSet.getName());
		List<ModelField> fields = model.getFields();
		JSONArray fieldArray = new JSONArray();
		for (ModelField field : fields) {
			fieldArray.put (field.toJSON());
		}
		return val;
	}
}
