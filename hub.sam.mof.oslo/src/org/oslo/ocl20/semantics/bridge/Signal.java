/**
 *
 *  Class Signal.java
 *
 *  Generated by KMFStudio at 09 May 2003 17:49:04
 *  Visit http://www.cs.ukc.ac.uk/kmf
 *
 */

package org.oslo.ocl20.semantics.bridge;

import java.util.List;

import org.oslo.ocl20.semantics.SemanticsElement;


public interface Signal
extends
    SemanticsElement,
    ModelElement
{
	/** Get the 'parameterTypes' from 'Signal' */
	public List getParameterTypes();
	/** Set the 'parameterTypes' from 'Signal' */
	public void setParameterTypes(List parameterTypes);

	/** Get the 'parameterNames' from 'Signal' */
	public List getParameterNames();
	/** Set the 'parameterNames' from 'Signal' */
	public void setParameterNames(List parameterNames);

	/** Override the toString */
	public String toString();

	/** Clone the object */
	public Object clone();
}
