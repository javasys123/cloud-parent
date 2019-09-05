package tjs.ax.base.service;


import org.springframework.stereotype.Service;
import tjs.ax.common.dto.LogDto;
import tjs.ax.common.utils.Query;

import java.util.List;


@Service
public interface LogService {
    int save(LogDto logDto);

    List<LogDto> queryList(Query query);

    int count(Query query);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
