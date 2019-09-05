package tjs.ax.admin.service;


import tjs.ax.admin.domain.Dept;
import tjs.ax.admin.domain.Tree;

import java.util.List;
import java.util.Map;


public interface DeptService {
	
	Dept get(Long deptId);
	
	List<Dept> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Dept sysDept);
	
	int update(Dept sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<Dept> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
