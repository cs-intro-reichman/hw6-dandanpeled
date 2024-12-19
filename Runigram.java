import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] horImage;

		// Tests the horizontal flipping of an image:
		horImage = flippedHorizontally(tinypic);
		System.out.println();
		print(horImage);
		
		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] verImage;

		// Tests the vertical flipping of an image:
		verImage = flippedVertically(tinypic);
		System.out.println();
		print(verImage);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] greyImage;

		// Tests the vertical flipping of an image:
		greyImage = grayScaled(tinypic);
		System.out.println();
		print(greyImage);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] scaleImage;

		// Tests the vertical flipping of an image:
		scaleImage = scaled(tinypic,3,5);
		System.out.println();
		print(scaleImage);

		Color c1 = new Color(100,40,100);
		Color c2 = new Color(200,20,40);

		Color blend1 = blend(c1, c2, 0.25);

		System.out.println(blend1.getRed());
		System.out.println(blend1.getGreen());
		System.out.println(blend1.getBlue());


		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		

		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				int r = in.readInt();
				int g = in.readInt();
				int b = in.readInt();
				Color newColor = new Color(r, g, b);
				image[i][j] = newColor;

			}
		}
	
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.

		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				Color c = image[i][j];
				System.out.print("(");
				System.out.printf("%3s,", c.getRed());   // Prints the red component
				System.out.printf("%3s,", c.getGreen()); // Prints the green component
				System.out.printf("%3s",  c.getBlue());  // Prints the blue component
				System.out.print(")");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] flippedImage = new Color[image.length][image[0].length];

		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				flippedImage[i][j] = image[i][image[0].length - 1 - j];
			}
		}
		return flippedImage;
		}
	
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		
		Color[][] verticalFlippedImage = new Color[image.length][image[0].length];

		for (int i = 0; i < image[0].length; i++){
			for (int j = 0; j < image.length; j++){
				verticalFlippedImage[j][i] = image[image.length - 1 - j][i];

			}
		}
		return verticalFlippedImage;
		}

	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.

	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();

		int lum = (int)(0.299 * r + 0.587 * g + 0.114 * b);
		Color greyColor = new Color(lum, lum, lum);

		return greyColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		Color[][] greyScaledImage = new Color[image.length][image[0].length];

		for (int i = 0; i < image.length; i++){
			for (int j = 0; j < image[0].length; j++){
				Color pixel = image[i][j];
				pixel = luminance(pixel);

				greyScaledImage[i][j] = pixel;

			}
		}
		return greyScaledImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		/// 
		Color[][] scaledImage = new Color[height][width];
		double h0 = image.length;
		double w0 = image[0].length;
		double Heightdivide = h0/height;
		double widthdivide = w0/width;

		for (int i = 0; i < height ; i++){
			for (int j = 0; j < width ; j++){
				int hColorIndex = (int)(i * Heightdivide);
				int wColorIndex = (int)(j * widthdivide);

				Color oldImage = image[hColorIndex][wColorIndex];

				scaledImage[i][j] = oldImage;
			}
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
				
		int newR = (int) ((alpha * c1.getRed()) + ((1-alpha)*c2.getRed()));
		int newG = (int) (alpha * c1.getGreen() + (1-alpha)*c2.getGreen());
		int newB = (int) (alpha * c1.getBlue() + (1-alpha)*c2.getBlue());

		Color newColor = new Color(newR, newG, newB);

		return newColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		/// 
		Color[][] blendImage = new Color[image1.length][image1[0].length];

		for (int i = 0; i < blendImage.length; i++){
			for (int j = 0; j < blendImage[0].length; j++){
				blendImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}

		return blendImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {

		if (source.length != target.length || source[0].length != target[0].length){
			target = scaled(target, source[0].length, source.length);
		}



		for (int i = 0; i <= n; i++){
			double alpha = (double)(n-i)/n;
			source = blend(source, target, alpha);

			setCanvas(source);
			display(source);
			StdDraw.pause(50); 

			

		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

