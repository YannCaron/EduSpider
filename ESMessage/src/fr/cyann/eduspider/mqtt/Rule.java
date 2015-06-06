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
public interface Rule {
	
	boolean predicate(Context context);
	
}
