package coneforest.psylla.core;
import coneforest.psylla.*;

/**
*	A representation of Ψ-{@code namespace}, a named dictionary.
*/
@Type("namespace")
public class PsyNamespace
	extends PsyDict
{

	protected PsyNamespace(final String prefix)
	{
		this.prefix=prefix;
	}

	protected void registerOperators(final PsyOperator... operators)
	{
		for(final PsyOperator oOperator: operators)
			put(oOperator.getName(), oOperator);
	}

	/**
	*	Returns the namespace prefix.
	*
	*	@return the prefix.
	*/
	public String prefix()
	{
		return prefix;
	}

	// TODO known()
	@Override
	public boolean known(final String key)
	{
		//System.out.println("namespace:known "+prefix+" "+key);
		if(super.known(key))
		//if(dict.containsKey(key))
			return true;
		for(var oNamespace: imports)
		{
			//System.out.println("LOOKING: "+oNamespace.prefix());
			if(oNamespace.known(key))
				return true;
		}
		return false;
	}

	@Override
	public PsyObject get(final String key)
		throws PsyException
	{
		//System.out.println("namespace:get "+key);
		final var agenda=new Stack<PsyNamespace>();
		agenda.push(this);

		while(agenda.size()>0)
		{
			final var oNamespace=agenda.pop();
			final var o=oNamespace.dict.get(key);
			if(o!=null)
				return o;

			for(int i=oNamespace.imports.size()-1; i>=0; i--)
				agenda.push(oNamespace.imports.get(i));
		}
		throw new PsyUndefinedException();
	}

	public void psyImport(final PsyNamespace oNamespace)
	{
		imports.add(0, oNamespace);
	}

	@Override
	public String toSyntaxString()
	{
		return "%namespace="+prefix+"%";
	}

	public static PsyNamespace namespace(final String prefix)
	{
		if(pool.containsKey(prefix))
			return pool.get(prefix);
		final var oNamespace=new PsyNamespace(prefix);
		pool.put(prefix, oNamespace);
		return oNamespace;
	}

	/*public static PsyNamespace namespace(final Class<? extends PsyObject> clazz)
	{
		final var oNamespace=namespace(clazz.getAnnotation(Type.class).value());
		for(final var method: clazz.getDeclaredMethods())
		{
			if(method.isAnnotationPresent(Operator.class))
			{
				final var operatorName=method.getDeclaredAnnotation(Operator.class).value();
				oNamespace.put(operatorName, PsyOperator.valueOf(method));
			}
		}
		for(final var constructor: clazz.getDeclaredConstructors())
		{
			if(constructor.isAnnotationPresent(Operator.class))
			{
				final var operatorName=constructor.getDeclaredAnnotation(Operator.class).value();
				oNamespace.put(operatorName, PsyOperator.valueOf(constructor));
			}
		}
		return oNamespace;
	}*/

	public static PsyNamespace psyNamespace(final PsyStringy oPrefix)
	{
		return namespace(oPrefix.stringValue());
	}

	private static java.util.HashMap<String, PsyNamespace> pool
		=new java.util.HashMap<String, PsyNamespace>();

	private final String prefix;

	private java.util.ArrayList<PsyNamespace> imports
		=new java.util.ArrayList<PsyNamespace>();

}
