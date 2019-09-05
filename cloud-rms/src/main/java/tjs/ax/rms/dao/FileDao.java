package tjs.ax.rms.dao;


import org.apache.ibatis.annotations.Mapper;
import tjs.ax.rms.domain.File;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 */
@Mapper
public interface FileDao {

	File get(Long id);
	
	List<File> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(File file);
	
	int update(File file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
