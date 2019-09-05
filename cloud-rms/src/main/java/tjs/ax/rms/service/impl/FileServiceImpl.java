package tjs.ax.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tjs.ax.rms.dao.FileDao;
import tjs.ax.rms.domain.File;
import tjs.ax.rms.service.FileService;

import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao fileDao;
	
	@Override
	public File get(Long id){
		return fileDao.get(id);
	}
	
	@Override
	public List<File> list(Map<String, Object> map){
		return fileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fileDao.count(map);
	}
	
	@Override
	public int save(File file){
		return fileDao.save(file);
	}
	
	@Override
	public int update(File file){
		return fileDao.update(file);
	}
	
	@Override
	public int remove(Long id){
		return fileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return fileDao.batchRemove(ids);
	}
	
}
