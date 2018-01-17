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
public class ReasoningForwardChainingOptimised extends AlogrithmChaining {
	
	/**
	 * param a knowledge base ruleBase (in a given formalism)
	 * param a base of facts : factBase (in a given formalism)
	 * return the saturation of KB w.r.t. facts (the minimal fix point of KB from facts)
	 */


    public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
        FactBase allInferredFacts = forwardChainingOptimise(ruleBase, factBase);
        if(allInferredFacts.getFact().contains(query))
            return true;
        else
            return false;
    }

	public FactBase forwardChainingOptimise(Formalism ruleBase, Formalism factBase){
		//It's your turn to implement the algorithm

		FactBase fb = new FactBase();
		fb.getFact().addAll(((FactBase)factBase).getFact());

		HornRuleBase rb = new HornRuleBase();
        for (HornRule hr:((HornRuleBase)ruleBase).getRules()){
            HornRule ihr = new HornRule(hr.getConditions(),hr.getConclusions());
            rb.getRules().add(ihr);
        }

		for (Variable fact:((FactBase) factBase).getFact()){
			fb.getFact().addAll((propagate(fact,rb)).getFact());
		}
		return fb;
	}

	//function propagate to simplify the rulebase on each iteration
	public FactBase propagate(Variable fact, Formalism ruleBase){
		FactBase deltaF = new FactBase();

        HornRuleBase initrb = new HornRuleBase();
        for (HornRule hr:((HornRuleBase)ruleBase).getRules()){
            HornRule ihr = new HornRule(hr.getConditions(),hr.getConclusions());
            initrb.getRules().add(ihr);
        }

        Iterator<HornRule> itehr = initrb.getRules().iterator();

	    while (itehr.hasNext()){
	        HornRule hr = itehr.next();
	        if (hr.getConditions().contains(fact)){
	            Iterator<Variable> vr = hr.getConditions().iterator();
	            while (vr.hasNext()){
	                Variable condi= vr.next();
	                if (condi.equals(fact))
	                    vr.remove();
                }
            }
        }
        for (HornRule hr:initrb.getRules()){
            if (hr.getConditions().isEmpty()){
                deltaF.getFact().addAll(hr.getConclusions());
                hr.getConclusions().clear();
            }
        }

		FactBase initfb =new FactBase();
        initfb.getFact().addAll(deltaF.getFact());
		for (Variable f:deltaF.getFact()){
			initfb.getFact().addAll((propagate(f,initrb)).getFact());
		}
		return initfb;
	}


	@Override
	public int countNbMatches() {
		// TODO Auto-generated method stub
		return 0;
	}

}
