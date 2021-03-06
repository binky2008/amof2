package core.abstractions.expressions;


public class OpaqueExpressionDlg extends hub.sam.mof.reflection.ObjectDlg implements OpaqueExpression
{
    protected OpaqueExpression self = null;
    @Override
    protected void setSelf(cmof.reflection.Object self) {
        super.setSelf(self);
        this.self = (OpaqueExpression)self;
    }

    public java.lang.String getBody() {
        return self.getBody();
    }

    public void setBody(java.lang.String value) {
        self.setBody(value);
    }

    public java.lang.String getLanguage() {
        return self.getLanguage();
    }

    public void setLanguage(java.lang.String value) {
        self.setLanguage(value);
    }

    public boolean isComputable()  {
        return self.isComputable();
    }

    public int integerValue()  {
        return self.integerValue();
    }

    public boolean booleanValue()  {
        return self.booleanValue();
    }

    public java.lang.String stringValue()  {
        return self.stringValue();
    }

    public long unlimitedValue()  {
        return self.unlimitedValue();
    }

    public boolean isNull()  {
        return self.isNull();
    }

    public cmof.common.ReflectiveCollection<? extends core.abstractions.ownerships.Element> getOwnedElement() {
        return (cmof.common.ReflectiveCollection<? extends core.abstractions.ownerships.Element>)(java.lang.Object)self.getOwnedElement();
    }

    public core.abstractions.ownerships.Element getOwner() {
        return (core.abstractions.ownerships.Element)(java.lang.Object)self.getOwner();
    }

    public void setOwner(core.abstractions.ownerships.Element value) {
        self.setOwner(value);
    }

    public cmof.common.ReflectiveCollection<? extends core.abstractions.ownerships.Element> allOwnedElements()  {
        return (cmof.common.ReflectiveCollection<? extends core.abstractions.ownerships.Element>)(java.lang.Object)self.allOwnedElements();
    }

    public boolean mustBeOwned()  {
        return self.mustBeOwned();
    }

}

