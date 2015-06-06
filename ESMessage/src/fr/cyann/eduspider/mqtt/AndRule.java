/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cyann.eduspider.mqtt;

/**
 <p>
 @author cyann
 */
public class AndRule implements Rule {

	private final Rule leftRule;
	private final Rule rightRule;

	public AndRule(Rule leftRule, Rule rightRule) {
		this.leftRule = leftRule;
		this.rightRule = rightRule;
	}

	@Override
	public boolean predicate(Context context) {
		return leftRule.predicate(context) && rightRule.predicate(context);
	}

}
