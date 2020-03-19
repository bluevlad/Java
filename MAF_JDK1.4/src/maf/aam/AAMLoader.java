package maf.aam;

import java.util.List;

public abstract interface  AAMLoader {

	/**
	 * AAMBean 의 List 형태로 돌려줌 
	 * @param site
	 * @return
	 */
	public abstract List getAuthAll(String site);
	public abstract void reload();
}
