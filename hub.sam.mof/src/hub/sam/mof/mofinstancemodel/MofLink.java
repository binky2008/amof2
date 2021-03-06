/*
A MOF 2 Java -- The MOF Repository tool for Java
Copyright (C) 2005 Markus Scheidgen

    This library is free software; you can redistribute it and/or modify it
under the terms of the GNU Lesser General Public License as published by the
Free Software Foundation; either version 2.1 of the License, or any later
version.

    This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.

    You should have received a copy of the GNU Lesser General Public License
along with this library; if not, write to the Free Software Foundation, Inc.,
59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/

package hub.sam.mof.mofinstancemodel;

import cmof.Association;
import cmof.Property;
import cmof.StructuralFeature;
import cmof.UmlClass;
import cmof.exception.IllegalArgumentException;
import hub.sam.mof.instancemodel.InstanceValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Links are not stored in the model explicitly. All links are derived from properties
 * instanciated through slots in the according element instances. Any association end
 * that is not navigable, and thus not owned by the association, but the classifier, is
 * realised by an unvisible slot in the appropriate element instance.
 * @see #findStructureSlotForEnd(Property, InstanceValue<UmlClass,Property,java.lang.Object>)
 * @see #fakeSlotsForAssociation
 */
public class MofLink {
	private final Association association;
	private final Collection<MofLinkSlot> slots = new HashSet<MofLinkSlot>();
    private final MofLinkSlot slotOne;
    private final MofLinkSlot slotTwo;

    /**
     * No event notification for this operation, no subset or association handling.
     */
    public static void createLink(Association association, InstanceValue<UmlClass,Property,java.lang.Object> one,
            InstanceValue<UmlClass,Property,java.lang.Object> two) {
        MofStructureSlot slotOne = findStructureSlotForEnd(association.getMemberEnd().get(1), one);
        MofStructureSlot slotTwo = findStructureSlotForEnd(association.getMemberEnd().get(0), two);
        if ((slotOne == null) || (slotTwo == null)) {
            throw new IllegalArgumentException("instances are not compatible with association");
        }
        slotOne.getValuesAsList(null).addPlain(two, null);
        slotTwo.getValuesAsList(null).addPlain(one, null);
    }

    public static MofLink getLink(Association association, InstanceValue<UmlClass,Property,java.lang.Object> one, InstanceValue<UmlClass,Property,java.lang.Object> two) {
        MofStructureSlot slotOne = findStructureSlotForEnd(association.getMemberEnd().get(1), one);
        MofStructureSlot slotTwo = findStructureSlotForEnd(association.getMemberEnd().get(0), two);
        if ((slotOne == null) || (slotTwo == null)) {
            throw new IllegalArgumentException("instances are not compatible with association");
        }
        if (slotOne.getValuesAsList(null).contains(two) && slotTwo.getValuesAsList(null).contains(one)) {
            return new MofLink(association,one, two);
        } else {
            return null;
        }
    }

    /**
     * No event notification for this operation, no subset or association handling.
     */
    public static void removeLink(Association association, InstanceValue<UmlClass,Property,java.lang.Object> one, InstanceValue<UmlClass,Property,java.lang.Object> two) {
        if (getLink(association, one, two) != null) {
            findStructureSlotForEnd(association.getMemberEnd().get(1), one).getValuesAsList(null).removePlain(two, null);
            findStructureSlotForEnd(association.getMemberEnd().get(0), two).getValuesAsList(null).removePlain(one, null);
        }
    }

	/**
	 * Locates the slot that represents the given association end.
	 * @param property the association end
	 * @param value the instance should contate the according slot
	 * @throws NullPointerException if there is no such slot.
	 */
    protected static MofStructureSlot findStructureSlotForEnd(Property property, hub.sam.mof.instancemodel.InstanceValue<UmlClass,Property,java.lang.Object>
            value) {
        MofStructureSlot result = null;
        if (property.getOwner() instanceof Association) {
            result = ((MofClassInstance)value.getInstance()).get(property.getOwningAssociation(), property);
        } else {
            result = ((MofClassInstance)value.getInstance()).get(property);
        }
        if (result == null) {
            throw new NullPointerException();
        }
        return result;
    }

    private MofLink(Association association, InstanceValue<UmlClass,Property,java.lang.Object> one, InstanceValue<UmlClass,Property,java.lang.Object> two) {
		this.association = association;
        slotOne = new MofLinkSlot(this, association.getMemberEnd().get(0), one);
        slotTwo = new MofLinkSlot(this, association.getMemberEnd().get(1), two);
	}

    public Association getAssociation() {
        return this.association;
    }

    public Collection<MofLinkSlot> getSlot() {
        return slots;
    }

    @Override
	public boolean equals(Object other) {
        if (other instanceof MofLink) {
            MofLink otherLink = (MofLink)other;
            boolean equals = true;
            equals = equals && otherLink.slotTwo.getValue(null).get(0).equals(this.slotTwo.getValue(null).get(0));
            equals = equals && otherLink.slotOne.getValue(null).get(0).equals(this.slotOne.getValue(null).get(0));
            equals = equals && otherLink.association.equals(this.association);
            return equals;
        } else {
            return false;
        }
    }

    public MofLinkSlot getSlot(StructuralFeature definingFeature) {
        return (slotOne.getDefiningFeature() == definingFeature) ? slotOne : slotTwo;
    }
}
