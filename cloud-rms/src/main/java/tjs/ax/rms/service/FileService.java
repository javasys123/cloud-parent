package tjs.ax.rms.service;


import tjs.ax.rms.domain.File;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 */
public interface FileService {
	
	File get(Long id);
	
	List<File> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(File file);
	
	int update(File file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
