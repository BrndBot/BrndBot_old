package com.brndbot.promo;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.parser.ModelParser;
import com.brndbot.system.BrndbotException;


/** A ModelLoader reads the model files and gives its caller
 *  a ModelCollection. 
 */
public class ModelLoader {

	final static Logger logger = LoggerFactory.getLogger(ModelLoader.class);

	File categoriesDir;
	
	/** Constructor. Establishes the directory that holds
	 *  the category directories, which in turn hold the
	 *  model files. */
	public ModelLoader(String path) throws BrndbotException {
		categoriesDir = new File (path);
		if (!categoriesDir.exists()) {
			logger.error ("Model directory {} does not exist", path);
			categoriesDir = null;
			throw new BrndbotException ("Configuration error: no model directory");
		}
		if (!categoriesDir.isDirectory()) {
			logger.error ("Path {} is not a directory", path);
			categoriesDir = null;
			throw new BrndbotException ("Configuration error: bad model directory");
		}
	}
	
	public ModelCollection readModelFiles () {
		ModelCollection mc = new ModelCollection ();
		File[] categoryDirs = categoriesDir.listFiles();
		for (File dir : categoryDirs) {
			String catName = dir.getName();
			logger.debug ("Category directory {}", catName);
			if (!dir.isDirectory()) {
				logger.warn ("Non-directory file at category level in models directory");
				continue;
			}
			File[] modelFiles = dir.listFiles ();
			for (File f : modelFiles) {
				logger.debug ("Model file {}", f.getName());
				ModelParser parser = new ModelParser (f);
				try {
					Model m = parser.parse();
					// If the model category doesn't match the directory,
					// is logging the inconsistency sufficient notice?
					// Should we change the category to match the directory?
					// Right now the category in the file governs.
					if (!catName.equals (m.getCategory())) {
						logger.warn ("Model file category {} does not match category directory {}",
								m.getCategory (),
								catName);
					}
					mc.addModel(m);
				} catch (Exception e) {
					logger.error ("Error parsing {}", f.getPath());
					if (e.getMessage() != null)
						logger.error (e.getMessage());
					e.printStackTrace ();
				}
			}
		}
		return mc;
	}

}
