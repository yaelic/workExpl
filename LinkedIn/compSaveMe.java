import net.stixar.util.NumAdaptor;


public class compSaveMe implements NumAdaptor<Integer> {

	public compSaveMe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Integer arg0, Integer arg1) {
		if (Integer.valueOf(arg0)==Integer.valueOf(arg1)){
			return 0;
		}
		if (Integer.valueOf(arg0)>Integer.valueOf(arg1)){
			return 1;
		}
		return -1;
	}

	@Override
	public Integer add(Integer arg0, Integer arg1) {
		//System.out.println(""+ arg0 + "" + arg1);
		return Integer.valueOf(arg0)+Integer.valueOf(arg1);
	}

	@Override
	public Integer inf() {
		// TODO Auto-generated method stub
		return Integer.valueOf(7000000);
	}

	@Override
	public Integer minusInf() {
		// TODO Auto-generated method stub
		return Integer.valueOf(-7000000);
	}

	@Override
	public Integer subtract(Integer arg0, Integer arg1) {
		return Integer.valueOf(arg0)-Integer.valueOf(arg1);
	}

	@Override
	public Integer zero() {
		return Integer.valueOf(0);
	}

}
