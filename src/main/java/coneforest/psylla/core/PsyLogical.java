package coneforest.psylla.core;
import coneforest.psylla.*;

/**
*	A representation of Ψ-{@code logical}, a type of object that is an operand
*	of logical operation. This interface declares methods for logical negation,
*	disjunction, conjunction and exclusive disjunction.
*
*	@param <T> a type of the second operand at binary operation.
*/
@coneforest.psylla.Type("logical")
public interface PsyLogical<T extends PsyLogical>
	extends PsyObject
{

	/**
	*	Returns a result of logical negation of this object.
	*
	*	@return a result.
	*/
	public T psyNot();

	/**
	*	Returns a result of logical disjunction of this object and given
	*	object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psyOr(final T oLogical);

	/**
	*	Returns a result of logical conjunction of this object and given
	*	object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psyAnd(final T oLogical);

	/**
	*	Returns a result of logical exclusive disjunction of this object and
	*	given object.
	*
	*	@param oLogical given object.
	*	@return a result.
	*/
	public T psyXor(final T oLogical);

	public static final PsyOperator[] OPERATORS=
		{
			new PsyOperator.Arity21<PsyLogical, PsyLogical>
				("and", PsyLogical::psyAnd),
			new PsyOperator.Arity11<PsyLogical>
				("not", PsyLogical::psyNot),
			new PsyOperator.Arity21<PsyLogical, PsyLogical>
				("or", PsyLogical::psyOr),
			new PsyOperator.Arity21<PsyLogical, PsyLogical>
				("xor", PsyLogical::psyXor),
		};

}
