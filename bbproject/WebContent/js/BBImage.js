/**
 * BBStyle.js
 * 
 *  *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *   
 *  The class ImageData holds information about an image from the server.
 *  
 *  The class ImageSet holds a set of ImageData objects and allows their
 *  retrieval by image ID.
 */


function Image (id, w, h) {
	this.id = id;
	this.width = w;
	this.height = h;
}

function ImageSet () {
	this.imgArray = [];
	
	/** Add an Image object to the ImageSet. */
	this.addImage = function (img) {
		this.imgArray.push (img);
	}
	
	/** Retrieve the Image object with the specified ID.
	 *  The string "default" may also be used as a synonym for 0.
	 */
	this.getByID = function (id) {
		if (id == "default")  {
			if (this.imgArray.length >= 1) 
				return this.imgArray[0];
			else
				return null;
		}
		for (var i = 0; i < this.imgArray.length; i++) {
			var img = this.imgArray[i];
			if (img.id == id)
				return img;
		}
		// No image with specified ID
		return null;
	}
}