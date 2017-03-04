package coneforest.psi.core;

/**
*	A representation of Ψ-{@code lock} object.
*/
@coneforest.psi.Type("lock")
public class PsiLock
	implements PsiObject
{

	/**
	*	Acquires the lock.
	*/
	public void lock()
	{
		lock.lock();
	}

	/**
	*	Releases the lock.
	*/
	public void unlock()
	{
		lock.unlock();
	}

	/**
	*	Queries if this lock is held by the current thread.
	*
	*	@return {@code true} if current context holds this lock and {@code
	*	false} otherwise.
	*/
	public boolean isHeldByCurrentThread()
	{
		return lock.isHeldByCurrentThread();
	}

	/**
	*	Returns a Ψ-{@code condition} for use with this lock.
	*
	*	@return a Ψ-{@code condition} object.
	*/
	public PsiCondition psiCondition()
	{
		return new PsiCondition(lock.newCondition());
	}

	private final java.util.concurrent.locks.ReentrantLock lock
		=new java.util.concurrent.locks.ReentrantLock();
}
