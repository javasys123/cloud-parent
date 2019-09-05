package tjs.ax.base.dao;

import org.apache.ibatis.annotations.Mapper;
import tjs.ax.common.dto.LogDto;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 */
@Mapper
public interface LogDao {

	LogDto get(Long id);
	
	List<LogDto> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(LogDto log);
	
	int update(LogDto log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
