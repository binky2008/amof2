/***********************************************************************
 * MASE -- MOF Action Semantics Editor
 * Copyright (C) 2007 Andreas Blunk
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301  USA
 ***********************************************************************/

package hub.sam.mase.m2model;

import org.apache.log4j.Logger;
import org.eclipse.gef.requests.CreationFactory;

import hub.sam.mase.editor.MaseEditDomain;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class MaseCreationFactory implements CreationFactory {
    
    private final Logger logger = Logger.getLogger(MaseCreationFactory.class.getName());
    private final MaseEditDomain editDomain;
    private final Class type;
    private Method createMethod;
    
    /**
     * Constructs a model factory for a given model class.
     * 
     * @param editDomain references the MaseRepository where new model elements are created
     * @param type the model class
     */
    public MaseCreationFactory(MaseEditDomain editDomain, Class type) {
        this.editDomain = editDomain;
        this.type = type;
        try {
            this.createMethod = this.getClass().getMethod("create" + type.getSimpleName(), (Class[]) null);
        }
        catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private MaseRepository getRepository() {
        return editDomain.getRepository();
    }
    
    public Object getNewObject() {
        try {
            logger.debug("creating " + type.getSimpleName());
            return createMethod.invoke(this, (Object[]) null);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class getObjectType() {
        return type;
    }
    
    public Activity createActivity() {
        return getRepository().getFactory().createActivity();
    }
    
    public InitialNode createInitialNode() {
        InitialNode newObject = getRepository().getFactory().createInitialNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public FinalNode createFinalNode() {
        FinalNode newObject = getRepository().getFactory().createFinalNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public OpaqueAction createOpaqueAction() {
        OpaqueAction newObject = getRepository().getFactory().createOpaqueAction();
        
        InputPinList newInputList = getRepository().getFactory().createInputPinList();
        newObject.setInputList(newInputList);
        
        OutputPinList newOutputList = getRepository().getFactory().createOutputPinList();
        newObject.setOutputList(newOutputList);
        
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public ValueNode createValueNode() {
        ValueNode newObject = getRepository().getFactory().createValueNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public ObjectFlow createObjectFlow() {
        ObjectFlow newObject = getRepository().getFactory().createObjectFlow();
        ModelGarbageCollector.getInstance().observeProperty(newObject, "target");
        return newObject;
    }

    public ControlFlow createControlFlow() {
        ControlFlow newObject = getRepository().getFactory().createControlFlow();
        ModelGarbageCollector.getInstance().observeProperty(newObject, "target");
        return newObject;
    }

    public InputPin createInputPin() {
        InputPin newObject = getRepository().getFactory().createInputPin();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public OutputPin createOutputPin() {
        OutputPin newObject = getRepository().getFactory().createOutputPin();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public ContextPin createContextPin() {
        ContextPin newObject = getRepository().getFactory().createContextPin();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public ContextExtensionPin createContextExtensionPin() {
        ContextExtensionPin newObject = getRepository().getFactory().createContextExtensionPin();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public GuardSpecification createGuardSpecification() {
        GuardSpecification newObject = getRepository().getFactory().createGuardSpecification();
        return newObject;
    }

    public DecisionNode createDecisionNode() {
        DecisionNode newObject = getRepository().getFactory().createDecisionNode();
        
        ContextPinList newList = getRepository().getFactory().createContextPinList();
        newObject.getContextList().add(newList);

        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }
    
    public ForkNode createForkNode() {
        ForkNode newObject = getRepository().getFactory().createForkNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }
    
    public JoinNode createJoinNode() {
        JoinNode newObject = getRepository().getFactory().createJoinNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }
    
    public ExpansionRegion createExpansionRegion() {
        ExpansionRegion newObject = getRepository().getFactory().createExpansionRegion();
        
        ExpansionRegionBody newBody = getRepository().getFactory().createExpansionRegionBody();
        newObject.setBody(newBody);

        InExpansionNodeList newInputList = getRepository().getFactory().createInExpansionNodeList();
        newObject.setInList(newInputList);
        
        OutExpansionNodeList newOutputList = getRepository().getFactory().createOutExpansionNodeList();
        newObject.setOutList(newOutputList);
        
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }
    
    public InExpansionNode createInExpansionNode() {
        InExpansionNode newObject = getRepository().getFactory().createInExpansionNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

    public OutExpansionNode createOutExpansionNode() {
        OutExpansionNode newObject = getRepository().getFactory().createOutExpansionNode();
        ModelGarbageCollector.getInstance().mark(newObject);
        return newObject;
    }

}