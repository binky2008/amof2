package hub.sam.mof.plugin.modelview;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import org.eclipse.jface.resource.ImageDescriptor;

public class ImageImageDescriptor extends ImageDescriptor {

	private Image fImage;

	/**
	 * Constructor for ImagImageDescriptor.
	 */
	public ImageImageDescriptor(Image image) {
		super();
		fImage= image;
	}

	/* (non-Javadoc)
	 * @see ImageDescriptor#getImageData()
	 */
	@Override
	public ImageData getImageData() {
		return fImage.getImageData();
	}

	/* (non-Javadoc)
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj != null) && getClass().equals(obj.getClass()) && fImage.equals(((ImageImageDescriptor)obj).fImage);
	}

	/* (non-Javadoc)
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return fImage.hashCode();
	}

}