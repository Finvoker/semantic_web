/**
 * 
 */
package sw.hornRule.algorithms;

import sw.hornRule.models.*;

import java.util.HashSet;

/**
 * @author  TANG Xucheng
 *
 */
public class ReasoningForwardChaining extends AlogrithmChaining {
 
	/**
	 * param a knowledge base kb (in a given formalism)
	 * param facts (in a given formalism)
	 * return forwardChaining(ruleBase,factBase), also called the saturation of ruleBase w.r.t. factBase,
	 * mathematically it computes the minimal fix point of KB from facts)
	 */
	//It's your turn to implement the algorithm, including the methods match() and eval()

	//function match()
	public boolean match(Variable l, HashSet<Variable> basefact){
		return basefact.contains(l);
	}

	//function eval()
	public boolean eval(HornRule hr, HashSet<Variable> bf){
		boolean res = true;

		for (Variable li: hr.getConditions()){
			res = res && match(li,bf);
		}
		return res;
	}

	//function fowardChaining(RB, FB)
	public FactBase forwardChaining(Formalism ruleBase, Formalism factBase){

		FactBase subfb = (FactBase) factBase;
		HashSet<Variable> sfb = subfb.getFact();
		int nbsub =0;

		for (Variable s:subfb.getFact()){
			nbsub++;
		}// init s(fb) and prepare the counter nbsub for the comparison

		HashSet<Variable> nfb = new HashSet<Variable>();
		for (Variable s:subfb.getFact()){
			nfb.add(s);
		}
		int nbfb = 0;
		//init the FB used in the loop and the counter nbfb

		do {
			nbfb=0;
			for (Variable n:nfb){
				nbfb++;
			}//update the counter after the sub fb changed

			for (HornRule hr:((HornRuleBase) ruleBase).getRules()){
				if(eval(hr,nfb)){
					for(Variable v: hr.getConclusions()){
						sfb.add(v);
						nfb.add(v);//update FBs right after but not the counters
					}
				}
			}// loop
			nbsub=0;
			for (Variable s:subfb.getFact()){
				nbsub++;
			}

		}while(nbfb!=nbsub);

		subfb.setFact(sfb);//set the final FactBase for return

		return subfb;
	}
	

	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
		FactBase allInferredFacts = forwardChaining(ruleBase, factBase);
		if(allInferredFacts.getFact().contains(query))
			return true;
		else
			return false;
	}

	@Override
	//It's your turn to implement this method
	public int countNbMatches() {
		// TODO Auto-generated method stub
		return 0;
	}

}
