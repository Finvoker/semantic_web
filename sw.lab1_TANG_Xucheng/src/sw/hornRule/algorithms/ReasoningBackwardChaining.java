/**
 * 
 */
package sw.hornRule.algorithms;

import sw.hornRule.models.*;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author  TANG Xucheng
 *
 */
public class ReasoningBackwardChaining extends AlogrithmChaining {


	public boolean match(Variable l, HashSet<Variable> basefact){
		return basefact.contains(l);
	}

	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
		return backwardChaining(ruleBase,factBase,query);
	}

	private boolean backwardChaining(Formalism ruleBase, Formalism factBase,Formalism query) {
		// TODO  To complete
		HornRuleBase rb = (HornRuleBase) ruleBase;
		FactBase initfb = (FactBase) factBase;
		Variable q = (Variable) query;

		FactBase fb = new FactBase(initfb.getFact());

		if (match(q,fb.getFact())){
			return true;
		}else{
			Iterator<HornRule> ihr = rb.getRules().iterator();
			while (ihr.hasNext()){
				HornRule hr = ihr.next();
				if (match(q,hr.getConclusions())){
					boolean bool=true;
					Iterator<Variable> ic = hr.getConditions().iterator();
					while (bool&&ic.hasNext()){
						Variable li=ic.next();
						bool=backwardChaining(rb,fb,li);
					}
					if (bool)
						return bool;
				}
			}
			return false;
		}
	}

	@Override
	public int countNbMatches() {
		// TODO To complete
		return 0;
	}

}
