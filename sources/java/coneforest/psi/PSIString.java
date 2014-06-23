package coneforest.psi;

public class PSIString extends PSIStringlike
{
	public PSIString(String string)
	{
		setValue(string);
	}

	public PSIString(Token token)
	{
		if(token.kind==PSIParserConstants.TOKEN_STRING)
		{
			StringBuilder sb=new StringBuilder();
			for(int i=1; i<token.image.length()-1; i++)
			{
				char c=token.image.charAt(i);
				switch(c)
				{
					case '\\':
						i++;
						switch(token.image.charAt(i))
						{
							case 'n':
								sb.append('\n');
								break;
							case 'r':
								sb.append('\r');
								break;
							case 't':
								sb.append('\t');
								break;
							case 'f':
								sb.append('\f');
								break;
							case 'e':
								sb.append('\u001B');
								break;
							case '"':
								sb.append('"');
								break;
							case '\\':
								sb.append('\\');
								break;
							case 'u':
								sb.append(Character.toChars(Integer.valueOf(token.image.substring(i+1, i+5), 16)));
								i+=4;
								break;
						}
						break;
					default:
						sb.append(c);
						break;
				}
			}
			setValue(new String(sb));
		}
	}

	public byte getType()
	{
		return TYPE_STRING;
	}

	public void execute(PSIInterpreter interpreter)
	{
		interpreter.getOperandStack().push(this);
		// TODO: executable strings
	}

	public String toString()
	{
		return "\""+getValue()+"\"";
	}

}
