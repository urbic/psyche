package coneforest.psi;

/**
 *	A type of Ψ objects that can be read.
 */
public interface PsiReadable
	extends PsiObject
{
	/**
	 *	Read a character (Ψ-<code class="type">integer</code>) from this object
	 *	and return it.
	 *
	 *	@return a character,
	 *	@throws PsiException when error occurs.
	 */
	public PsiInteger psiRead()
		throws PsiException;

	/**
	 *	Read a string (Ψ-<code class="type">string</code>) from this object and
	 *	return it.
	 *
	 *	@return a string,
	 *	@throws PsiException when error occurs.
	 */
	public PsiString psiReadString(PsiInteger count)
		throws PsiException;

	/**
	 *	Read a line (Ψ-<code class="type">string</code>) from this object and
	 *	return it.
	 *
	 *	@return a line,
	 *	@throws PsiException when error occurs.
	 */
	public PsiString psiReadLine(PsiStringlike stringlike)
		throws PsiException;

	public PsiBoolean psiReady()
		throws PsiException;
}
