package com.brndbot.promo;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.parser.ModelParser;


/** A ModelLoader reads the model files and gives its caller
 *  a ModelCollection. 
 */
public class ModelLoader {

	final static Logger logger = LoggerFactory.getLogger(ModelLoader.class);

	File modelDir;
	
	/** Constructor. Establishes the directory that holds
	 *  the model files. */
	public ModelLoader(String path) {
		modelDir = new File (path);
		if (!modelDir.exists()) {
			logger.error ("Model directory {} does not exist", path);
			modelDir = null;
			return;
		}
		if (!modelDir.isDirectory()) {
			logger.error ("Path {} is not a directory", path);
			modelDir = null;
			return;
		}
	}
	
	public ModelCollection readModelFiles () {
		ModelCollection mc = new ModelCollection ();
		File[] files = modelDir.listFiles();
		for (File f : files) {
			ModelParser parser = new ModelParser (f);
			try {
				Model m = parser.parse();
				mc.addModel(m);
			} catch (Exception e) {
				logger.error ("Error parsing {}", f.getPath());
			}
		}
		return mc;
	}

}
