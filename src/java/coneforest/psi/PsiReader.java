package coneforest.psi;

public class PsiReader
	extends PsiAbstractObject
	implements
		PsiCloseable,
		PsiEvaluable,
		PsiReadable,
		PsiResettable
{
	public PsiReader()
	{
	}

	public PsiReader(java.io.Reader reader)
	{
		setReader(reader);
	}

	public PsiReader(java.io.InputStream is)
	{
		this(new java.io.InputStreamReader(is));
	}

	@Override
	public void eval(Interpreter interpreter)
		throws PsiException
	{
		interpreter.interpret(this);
	}

	@Override
	public String getTypeName()
	{
		return "reader";
	}

	protected void setReader(java.io.Reader reader)
	{
		this.reader=reader;
	}

	protected java.io.Reader getReader()
	{
		return reader;
	}

	@Override
	public PsiInteger psiRead()
		throws PsiException
	{
		try
		{
			return PsiInteger.valueOf(reader.read());
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public PsiString psiReadString(PsiInteger count)
		throws PsiException
	{
		final long countValue=count.longValue();
		if(countValue<=0)
			throw new PsiException("rangecheck");
		if(countValue>Integer.MAX_VALUE)
			throw new PsiException("limitcheck");
		try
		{
			java.nio.CharBuffer buffer=java.nio.CharBuffer.allocate((int)countValue);
			reader.read(buffer);
			buffer.flip();
			return new PsiString(buffer.toString());
		}
		catch(OutOfMemoryError e)
		{
			throw new PsiException("limitcheck");
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public PsiString psiReadLine(PsiStringlike eol)
		throws PsiException
	{
		String eolString=eol.getString();
		StringBuilder sb=new StringBuilder();

		try
		{
			do
			{
				int c=reader.read();
				if(c==-1)
					return new PsiString(sb);
				sb.append((char)c);
				if(sb.substring(sb.length()-eolString.length()).equals(eolString))
					return new PsiString(sb);
			}
			while(true);
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public void psiSkip(PsiInteger count)
		throws PsiException
	{
		long countValue=count.longValue();
		try
		{
			reader.skip(countValue);
		}
		catch(IllegalArgumentException e)
		{
			throw new PsiException("rangecheck");
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public PsiBoolean psiReady()
		throws PsiException
	{
		try
		{
			return PsiBoolean.valueOf(reader.ready());
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public void psiClose()
		throws PsiException
	{
		try
		{
			reader.close();
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	@Override
	public void psiReset()
		throws PsiException
	{
		try
		{
			reader.reset();
		}
		catch(java.io.IOException e)
		{
			throw new PsiException("ioerror");
		}
	}

	private java.io.Reader reader;
}
