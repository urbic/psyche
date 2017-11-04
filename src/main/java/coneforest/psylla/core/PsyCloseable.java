package coneforest.psylla.core;

/**
*	A representation of Ψ-{@code closeable}, a type of objects that can be
*	closed in some sense.
*/
@coneforest.psylla.Type("closeable")
public interface PsyCloseable
	extends PsyObject
{

	/**
	*	Close this object.
	*
	*	@throws PsyException when error occured during closing.
	*/
	public void psyClose()
		throws PsyException;
}
