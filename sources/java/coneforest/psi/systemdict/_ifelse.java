package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _ifelse extends PSIOperator
{
	public String getName()	{ return "ifelse"; }

	public void execute(PSIInterpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<3)
			interpreter.error("stackunderflow");
		else
		{
			PSIObject obj2=opstack.pop();
			PSIObject obj1=opstack.pop();
			PSIObject cond=opstack.pop();
			if(cond instanceof PSIBoolean)
				(((PSIBoolean)cond).getValue()? obj1: obj2).invoke(interpreter);
			else
			{
				opstack.push(cond);
				opstack.push(obj1);
				opstack.push(obj2);
				interpreter.error("typecheck");
			}
		}
	}
}
