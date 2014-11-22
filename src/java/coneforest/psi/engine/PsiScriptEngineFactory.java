package coneforest.psi.engine;

/**
 * A Ψ programming language scripting engine.
 */
public class PsiScriptEngineFactory
	implements javax.script.ScriptEngineFactory
{
	/**
	 * Returns an engine name.
	 *
	 * @return a <code class="constant">"Psi"</code> string.
	 */
	@Override
	public String getEngineName()
	{
		return "Psi";
	}

	/**
	 * Returns an engine.
	 *
	 * @return aa engine.
	 */
	@Override
	public javax.script.ScriptEngine getScriptEngine()
	{
		return new PsiScriptEngine(this);
	}

	@Override
	public java.util.List<String> getNames()
	{
		return java.util.Collections.unmodifiableList(java.util.Arrays.asList("psi"));
	}

	@Override
	public String getProgram(String... statements)
	{
		//throw new UnsupportedOperationException(getClass().getName()+".getProgram not supported");
		StringBuilder sb=new StringBuilder();
		for(String statement: statements)
			sb.append(statement+'\n');
		return sb.toString();
	}

	@Override
	public String getOutputStatement(String toDisplay)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException(getClass().getName()+".getOutputStatement not supported");
	}

	@Override
	public String getMethodCallSyntax(String obj, String m, String... args)
	{
		throw new UnsupportedOperationException(getClass().getName()+".getMethodCalSyntax not supported");
	}

	@Override
	public String getParameter(String key)
	{
		if(key.equals(javax.script.ScriptEngine.ENGINE))
			return getEngineName();
		if(key.equals(javax.script.ScriptEngine.ENGINE_VERSION))
			return getEngineVersion();
		if(key.equals(javax.script.ScriptEngine.NAME))
			return getEngineName();
		if(key.equals(javax.script.ScriptEngine.LANGUAGE))
			return getLanguageName();
		if(key.equals(javax.script.ScriptEngine.LANGUAGE_VERSION))
			return getLanguageVersion();
		if(key.equals("THREADING"))
			return "MULTITHREADED";	// TODO
		return null;
	}

	@Override
	public String getLanguageVersion()
	{
		return coneforest.psi.Version.getVersion();
	}

	@Override
	public String getEngineVersion()
	{
		return coneforest.psi.Version.getVersion();
	}

	/**
	 * Returns a name of a language.
	 *
	 * @return a name of a language.
	 */
	@Override
	public String getLanguageName()
	{
		return "Psi";
	}

	@Override
	public java.util.List<String> getMimeTypes()
	{
		if(mimeTypes==null)
			mimeTypes=java.util.Collections.unmodifiableList(java.util.Arrays.asList("application/psi"));
		return mimeTypes;
	}

	@Override
	public java.util.List<String> getExtensions()
	{
		if(extensions==null)
			extensions=java.util.Collections.unmodifiableList(java.util.Arrays.asList("psi"));
		return extensions;
	}

	private java.util.List<String> mimeTypes;
	private java.util.List<String> extensions;
}

