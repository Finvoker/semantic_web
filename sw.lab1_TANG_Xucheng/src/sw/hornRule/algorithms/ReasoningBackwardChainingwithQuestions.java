/**
 * 
 */
package sw.hornRule.algorithms;

import sw.hornRule.models.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author  TANG Xucheng
 *
 */
public class ReasoningBackwardChainingwithQuestions extends AlogrithmChaining {

	public boolean match(Variable l, HashSet<Variable> basefact){
		return basefact.contains(l);
	}

	@Override
	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism Query) {
		// TODO To complete
		// When a literal (i.e. a variable or its negation) cannot be replied by deductive reasoning, 
		// it will be asked to users to give an answer (if the liter holds according to the user)
		return backwardChainingwithQuestions(ruleBase, factBase,Query);

	}

	public boolean demandable(Variable fact, HornRuleBase ruleBase){
		boolean bool=false;
		HornRuleBase rb = ruleBase;
		Iterator<HornRule> ihr = rb.getRules().iterator();
		while (ihr.hasNext()){
			HornRule hr = ihr.next();
			bool = bool||match(fact,hr.getConclusions());
		}
		return bool;
	}
	public boolean question(Variable fact,HornRuleBase ruleBase){
		boolean answer = false;
		if(this.demandable(fact,ruleBase)){
			System.out.println("This query is demandable, please answer with (true/false) : ");
			System.out.println();
			Scanner sc = new Scanner(System.in);
			try{
				answer = sc.nextBoolean();
			}catch (Exception e){
				System.out.println("The input that you wrote don't correspond to the good type :true/false");
				e.printStackTrace();
			}
			sc.close();
		}
		return answer;
	}

	public boolean backwardChainingwithQuestions(Formalism ruleBase,Formalism factBase,Formalism query){
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
						bool=backwardChainingwithQuestions(rb,fb,li);
					}
					if (bool)
						return bool;
				}
			}
			if(demandable(q,rb))
				return question(q,rb);
			else
				return false;
		}
	}
 

	@Override
	public int countNbMatches() {
		// TODO To complete
		return 0;
	}

}
