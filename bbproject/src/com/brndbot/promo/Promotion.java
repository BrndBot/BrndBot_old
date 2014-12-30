package com.brndbot.promo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.ModelField;
import com.brndbot.client.PromotionPrototype;
import com.brndbot.client.StyleSet;
import com.brndbot.system.SessionUtils;

/** This class defines a Promotion, which includes a model, style,
 *  and content.
 */
public class Promotion extends PromotionPrototype {

	private List<ModelField> content;
	
	/** Factory method to create a Promotion from a specified
	 *  model name. *** DO we ever want to do this, rather than 
	 *  creating it from a PromotionPrototype?
	 */
	public static Promotion createPromotion (String protoName, HttpServletRequest request) {
		// We somehow need access to the ModelCollection. That should
		// be a session attribute, so the session has to be an
		// argument.
		Client client = (Client) SessionUtils.getSessionData
					(request, SessionUtils.CLIENT);
		PromotionPrototype proto = client.getPromotionPrototype(protoName);
		return new Promotion (proto);
	}
	
	/** Create a promotion from a PromotionPrototype. */
	public Promotion (PromotionPrototype proto) {
		super(proto.getName(), proto.getModel(), proto.getStyleSet());
		// copy all fields from model
		List<ModelField> modelFields = proto.getModel().getFields();
		content = new ArrayList<ModelField>(modelFields.size());
		for (ModelField field : modelFields) {
			content.add (field.replicate ());
		}
	}
	
	public Promotion(String name, Model m, StyleSet ss) {
		super(name, m, ss);
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
