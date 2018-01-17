package problem.hornRules;

import sw.hornRule.algorithms.ReasoningBackwardChaining;
import sw.hornRule.algorithms.ReasoningBackwardChainingwithQuestions;
import sw.hornRule.algorithms.ReasoningForwardChaining;
import sw.hornRule.algorithms.ReasoningForwardChainingOptimised;
import sw.hornRule.models.FactBase;
import sw.hornRule.models.HornRule;
import sw.hornRule.models.HornRuleBase;
import sw.hornRule.models.Variable;

import java.util.HashSet;

public class ReasoningHorn {

	public static void main(String[] args) {
		
		ReasoningBackwardChainingwithQuestions reasoner = new ReasoningBackwardChainingwithQuestions();
		Tutorial1 pb = new Tutorial1();
		HornRuleBase kb = pb.getRuleBase();
		FactBase fb = pb.getFactBase();
		
		for(HornRule r: kb.getRules()){
			System.out.println(r);
		}
		System.out.print("\nThe fact base is: \n");
		System.out.print(fb);
//
//		//Display all facts inferred by the given knowledge base kb and fact base fb
//		HashSet<Variable> inferredAllFacts = reasoner.forwardChainingOptimise(kb,fb).getFact();
//		System.out.println("All the inferred facts are:");
//		for(Variable s: inferredAllFacts){
//			System.out.print(s+" ");
//		}

		Variable q = new Variable(/*"transoceanic_race"*/ "keelboat_regatte");
		if(reasoner.entailment(kb, fb, q))
			System.out.println("\nYes, the query is entailed by the given rules and facts");
		else
			System.out.println("\nNo, the query is not entailed based on the given rules and facts");
	}
}
