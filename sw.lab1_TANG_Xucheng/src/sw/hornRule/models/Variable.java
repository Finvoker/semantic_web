package sw.hornRule.models;
import java.util.Objects;
/**
 * @author 
 *
 */
public class Variable extends Formalism{
	protected String variableName; 
	
	// Deux variables specifiques, Vrai et False
	public final static Variable False = new Variable("False");
	public final static Variable True = new Variable("True");
	
	public Variable() {
		this.variableName = "UnknownVariable";
	}
	
	public Variable(String variableName) {
		super();
		this.variableName = variableName;
	}

	public String getNomVariable() {
		return variableName;
	}

	public void setNomVariable(String variableName) {
		this.variableName = variableName;
	}

	//Redefine func equal() for match() and contains()

	@Override
	public String toString() {
		return  variableName;
	}

	public boolean equals(Object obj){
		if(!(obj instanceof Variable)) return false;
		return this.variableName.equals(((Variable) obj).variableName);
	}

	public int hashCode() {
		return variableName.hashCode();
	}

}
