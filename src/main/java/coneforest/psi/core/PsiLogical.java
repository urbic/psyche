package coneforest.psi.core;

/**
*	A representation of Ψ-{@code logical}, a type of object that is an operand
*	of logical operation. This interface declares methods for logical negation,
*	disjunction, conjunction and exclusive disjunction.
*
*	@param <T> a type of the second operand at binary operation.
*/
@coneforest.psi.Type("logical")
public interface PsiLogical<T extends PsiLogical>
	extends PsiObject
{

	/**
	*	Returns a result of logical negation of this object.
	*
	*	@return a result.
	*/
	public T psiNot();

	/**
	*	Returns a result of logical disjunction of this object and given
	*	object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psiOr(final T oLogical);

	/**
	*	Returns a result of logical conjunction of this object and given
	*	object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psiAnd(final T oLogical);

	/**
	*	Returns a result of logical exclusive disjunction of this object and
	*	given object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psiXor(final T oLogical);
}
