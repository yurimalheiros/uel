package de.tudresden.inf.lat.uel.sat.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.tudresden.inf.lat.uel.type.api.AtomChangeListener;
import de.tudresden.inf.lat.uel.type.cons.KRSSKeyword;

/**
 * An object of this class is a concept name or TOP.
 * 
 * @author Julian Mendez
 */
public class ConceptName implements SatAtom {

	public static final String topKeyword = KRSSKeyword.top;

	private List<AtomChangeListener> changeListener = new ArrayList<AtomChangeListener>();
	private final String name;
	private final boolean top;
	private boolean userVariable = false;

	private boolean variable = false;

	/**
	 * Constructs a new concept name.
	 * 
	 * @param str
	 *            name
	 * @param isVar
	 *            <code>true</code> when the concept is a variable
	 */
	public ConceptName(String str, boolean isVar) {
		if (str == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		this.name = str;
		this.top = str.equalsIgnoreCase(topKeyword);
		this.variable = isVar;
	}

	@Override
	public boolean addAtomChangeListener(AtomChangeListener o) {
		return this.changeListener.add(o);
	}

	@Override
	public ConceptName asConceptName() {
		return this;
	}

	@Override
	public ExistentialRestriction asExistentialRestriction() {
		throw new ClassCastException();
	}

	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof ConceptName) {
			ConceptName other = (ConceptName) o;
			ret = this.name.equals(other.name);
		}
		return ret;
	}

	@Override
	public Collection<AtomChangeListener> getAtomChangeListeners() {
		return Collections.unmodifiableCollection(this.changeListener);
	}

	@Override
	public Integer getConceptNameId() {
		// FIXME
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return this.name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean isConceptName() {
		return true;
	}

	/**
	 * Not used in UEL. Checks if this atom is a constant.
	 * 
	 * @return <code>true</code> if and only if this atoms is a constant
	 */
	public boolean isConstant() {
		return !this.variable;
	}

	@Override
	public boolean isExistentialRestriction() {
		return false;
	}

	@Override
	public boolean isGround() {
		// FIXME
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Tells whether this concept is TOP.
	 * 
	 * @return <code>true</code> if and only if this concept is TOP
	 */
	public boolean isTop() {
		return this.top;
	}

	/**
	 * Checks if this flat atom is a system variable.
	 * 
	 * @return <code>true</code> if and only if this flat atom is a system
	 *         variable
	 */
	public boolean isUserVariable() {
		return this.userVariable;
	}

	/**
	 * Checks if a flat atom is a variable.
	 * 
	 * @return <code>true</code> if and only if a flat atom is a variable
	 */
	public boolean isVariable() {
		return this.variable;
	}
	
	@Override
	public boolean removeAtomChangeListener(AtomChangeListener o) {
		return this.changeListener.remove(o);
	}

	/**
	 * Sets a flat atom to be a system variable. Used at Goal initialization.
	 * 
	 */
	public void setUserVariable(boolean value) {
		this.userVariable = value;
	}

	/**
	 * If v is true, it defines this atom as a variable
	 * 
	 * @param isVar
	 */
	public void setVariable(boolean isVar) {
		this.variable = isVar;
	}

	@Override
	public String toString() {
		return this.getId();
	}


}
