package tjs.ax.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tjs.ax.base.dao.LogDao;
import tjs.ax.base.service.LogService;
import tjs.ax.common.dto.LogDto;
import tjs.ax.common.utils.Query;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logMapper;

    @Async
    @Override
    public int save(LogDto logDto) {
        return logMapper.save(logDto);
    }

    @Override
    public List<LogDto> queryList(Query query) {
        List<LogDto> logs = logMapper.list(query);
        return logs;
    }

    @Override
    public int count(Query query) {
        return logMapper.count(query);
    }

    @Override
    public int remove(Long id) {
        int count = logMapper.remove(id);
        return count;
    }

    @Override
    public int batchRemove(Long[] ids) {
        return logMapper.batchRemove(ids);
    }
}
