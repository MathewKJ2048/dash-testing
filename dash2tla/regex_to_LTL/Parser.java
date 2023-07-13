import java.util.List;
import java.util.ArrayList;

//user-generated imports

	import java.util.*;


public class Parser
{
	//start of user-generated global code

	public static void main(String[] args)
	{
		String input = "";
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		Parser p = new Parser(input);
		if(p._parse())
		{
		}
		else
		{
			System.out.println("INValid formula");
		}
	}
	//end of user-generated global code
static class _Node
	{
		@FunctionalInterface
		interface Executable
		{
			void execute();
		}
		Executable code;
		String symbol;
		List<_Node> children;
		Object $$;
		void add(_Node x)
		{
			this.children.add(x);
		}
		Object get_$(int i)
		{
			return this.children.get(i).$$;
		}
		void set_$(int i, Object o)
		{
			this.children.get(i).$$ = o;
		}
		public _Node(String symbol)
		{
			this.$$ = null;
			this.symbol = symbol;
			this.children = new ArrayList<>();
			this.code=null;
		}
		public void set_code(Executable e)
		{
			this.code = e;
		}
		public void execute()
		{
			if(code!=null)code.execute();
		}
	}
	String _DELIMITER = "";
	String _SPACE = "  ";
	String _OPEN = "{";
	String _CLOSE = "}";
	_Node _parse_tree;
	String _input;
	int _pt;
	public Parser(String in)
	{
		this._input = in;
		this._pt = 0;
		this._parse_tree = null;
	}
	String _parse_tree()
	{
		return _parse_tree_util(_parse_tree,0);
	}
	String _parse_tree_util(_Node x, int spaces)
	{
		StringBuilder s = new StringBuilder();
		if(x==null)return s.toString();
		String t = _SPACE.repeat(spaces);
		s.append(t).append(x.symbol).append("\n");
		if(x.children.size()==0)return s.toString();
		s.append(t).append(_OPEN).append("\n");
		spaces++;
		for(_Node k : x.children)s.append(_parse_tree_util(k,spaces));
		s.append(t).append(_CLOSE).append("\n");
		return s.toString();
	}
	String _derivation(boolean rightmost)
	{
		StringBuilder s = new StringBuilder();
		List<_Node> l = new ArrayList<>();
		l.add(_parse_tree);
		while(true)
		{
			s.append(_DELIMITER);
			for(_Node n : l)s.append(n.symbol).append(_DELIMITER);
			_Node e = null;
			int id=-1;
			int start = rightmost?0:l.size()-1;
			int end = l.size()-1-start;
			int update = rightmost?1:-1;
			for(int i=start;(start-i)*(i-end)>=0;i+=update)
			{
				if(l.get(i).children.size()!=0){e=l.get(i);id=i;}
			}
			if(e==null)break;
			s.append(" ->\n");
			l.remove(id);
			for(int i=e.children.size()-1;i>=0;i--)l.add(id,e.children.get(i));
		}
		return s.toString();
	}
	boolean _search(String terminal)
	{
		for(int i=0;i<terminal.length();i++)
		{
			if(_pt+i>=_input.length())return false;
			if(_input.charAt(_pt+i)!=terminal.charAt(i))return false;
		}
		_pt+=terminal.length();
		_parse_tree.add(new _Node(terminal));
		return true;
	}
	void _execute_code(_Node x)
	{
		if(x==null)return;
		for(_Node t : x.children)_execute_code(t);
		_parse_tree = x;
		x.execute();
	}
	//autogenerated parsing logic
	boolean _parse()
	{
		_parse_tree = new _Node("_START");
		boolean x = _START();
		if(_parse_tree.children.size()!=0)_parse_tree = _parse_tree.children.get(0);
		if(x&&(_pt==_input.length()))
		{
			_execute_code(_parse_tree);
			return true;
		}
		return false;
	}
	boolean _START()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("START");
		if(_R()){_parse_tree.set_code(this::_START0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _START0()
	{
		Object $$ = (Object)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
{System.out.println($1);}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
	}

	boolean _R()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("R");
		if(_search("("))if(_P())if(_search(")"))if(_R()){_parse_tree.set_code(this::_R0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("R");
		if(_search("("))if(_P())if(_search(")"))if(_search("*"))if(_R()){_parse_tree.set_code(this::_R1);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("R");
		if(_search("e")){_parse_tree.set_code(this::_R2);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("R");
		if(_S())if(_R()){_parse_tree.set_code(this::_R3);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("R");
		if(_S())if(_search("*"))if(_R()){_parse_tree.set_code(this::_R4);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _R0()
	{
		String $$ = (String)_parse_tree.$$;
		String $2 = (String)_parse_tree.get_$(1);
		String $4 = (String)_parse_tree.get_$(3);
{$$ = $2+" /\\ X("+$4+")";}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(1,$2);
		_parse_tree.set_$(3,$4);
	}
	void _R1()
	{
		String $$ = (String)_parse_tree.$$;
		String $2 = (String)_parse_tree.get_$(1);
		String $5 = (String)_parse_tree.get_$(4);
{$$="("+$2+") U ("+$5+")";}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(1,$2);
		_parse_tree.set_$(4,$5);
	}
	void _R2()
	{
		String $$ = (String)_parse_tree.$$;
{$$="e";}
		_parse_tree.$$ = $$;
	}
	void _R3()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
		String $2 = (String)_parse_tree.get_$(1);
{$$ = $1+" /\\ X("+$2+")";}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
		_parse_tree.set_$(1,$2);
	}
	void _R4()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
		String $3 = (String)_parse_tree.get_$(2);
{$$="("+$1+") U ("+$3+")";}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
		_parse_tree.set_$(2,$3);
	}

	boolean _P()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("P");
		if(_S())if(_search("+"))if(_P()){_parse_tree.set_code(this::_P0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("P");
		if(_S()){_parse_tree.set_code(this::_P1);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _P0()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
		String $3 = (String)_parse_tree.get_$(2);
{$$ = $1+"\\/"+$3;}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
		_parse_tree.set_$(2,$3);
	}
	void _P1()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
{$$=$1;}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
	}

	boolean _S()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("S");
		if(_search("s"))if(_Num()){_parse_tree.set_code(this::_S0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _S0()
	{
		String $$ = (String)_parse_tree.$$;
		String $2 = (String)_parse_tree.get_$(1);
{$$="s"+$2;}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(1,$2);
	}

	boolean _N()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("1")){_parse_tree.set_code(this::_N0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("2")){_parse_tree.set_code(this::_N1);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("3")){_parse_tree.set_code(this::_N2);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("4")){_parse_tree.set_code(this::_N3);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("5")){_parse_tree.set_code(this::_N4);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("6")){_parse_tree.set_code(this::_N5);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("7")){_parse_tree.set_code(this::_N6);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("8")){_parse_tree.set_code(this::_N7);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("9")){_parse_tree.set_code(this::_N8);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("N");
		if(_search("0")){_parse_tree.set_code(this::_N9);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _N0()
	{
		String $$ = (String)_parse_tree.$$;
{$$="1";}
		_parse_tree.$$ = $$;
	}
	void _N1()
	{
		String $$ = (String)_parse_tree.$$;
{$$="2";}
		_parse_tree.$$ = $$;
	}
	void _N2()
	{
		String $$ = (String)_parse_tree.$$;
{$$="3";}
		_parse_tree.$$ = $$;
	}
	void _N3()
	{
		String $$ = (String)_parse_tree.$$;
{$$="4";}
		_parse_tree.$$ = $$;
	}
	void _N4()
	{
		String $$ = (String)_parse_tree.$$;
{$$="5";}
		_parse_tree.$$ = $$;
	}
	void _N5()
	{
		String $$ = (String)_parse_tree.$$;
{$$="6";}
		_parse_tree.$$ = $$;
	}
	void _N6()
	{
		String $$ = (String)_parse_tree.$$;
{$$="7";}
		_parse_tree.$$ = $$;
	}
	void _N7()
	{
		String $$ = (String)_parse_tree.$$;
{$$="8";}
		_parse_tree.$$ = $$;
	}
	void _N8()
	{
		String $$ = (String)_parse_tree.$$;
{$$="9";}
		_parse_tree.$$ = $$;
	}
	void _N9()
	{
		String $$ = (String)_parse_tree.$$;
{$$="0";}
		_parse_tree.$$ = $$;
	}

	boolean _Num()
	{
		int k = _pt;
		_Node x = _parse_tree;
		_pt = k;
		_parse_tree = new _Node("Num");
		if(_N())if(_Num()){_parse_tree.set_code(this::_Num0);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = new _Node("Num");
		if(_N()){_parse_tree.set_code(this::_Num1);x.add(_parse_tree);_parse_tree=x;return true;}
		_pt = k;
		_parse_tree = x;
		return false;
	}
	void _Num0()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
		String $2 = (String)_parse_tree.get_$(1);
{$$=$1+$2;}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
		_parse_tree.set_$(1,$2);
	}
	void _Num1()
	{
		String $$ = (String)_parse_tree.$$;
		String $1 = (String)_parse_tree.get_$(0);
{$$=$1;}
		_parse_tree.$$ = $$;
		_parse_tree.set_$(0,$1);
	}

}