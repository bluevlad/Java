package maf.aam;

import java.util.List;

public abstract interface  AAMLoader {

	/**
	 * AAMBean �� List ���·� ������ 
	 * @param site
	 * @return
	 */
	public abstract List getAuthAll(String site);
	public abstract void reload();
}
