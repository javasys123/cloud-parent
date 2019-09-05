package tjs.ax.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tjs.ax.common.context.FilterContextHandler;
import tjs.ax.common.utils.FileUtils;
import tjs.ax.common.utils.PageUtils;
import tjs.ax.common.utils.Query;
import tjs.ax.common.utils.Result;
import tjs.ax.rms.domain.File;
import tjs.ax.rms.dto.FileDto;
import tjs.ax.rms.dto.convert.FileConvert;
import tjs.ax.rms.service.FileService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 */

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${app.filePath}")
    String filePath;

    @Value("${app.pre}")
    String filePre;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public Result get(@PathVariable Long id) {
        FileDto fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return Result.data(fileDTO);
    }

    @GetMapping("getToken")
    public Result getToken() {
        return Result.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-rms/file/upload")
                .put("key", UUID.randomUUID().toString());
    }

    @PostMapping("upload")
    public Result upload(MultipartFile file, String key) {
        try {
            String resPath = FileUtils.saveFile(file.getBytes(), filePath, key);
            fileService.save(new File() {{
                setCreateDate(new Date());
                setUrl("http://localhost:8004" + filePre + "/"+resPath);
                setType(1);
            }});
            return Result.ok().put("resPath", resPath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败");
        }
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<File> fileList = fileService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        int total = fileService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return Result.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result save(File file) {
        return Result.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public Result update(File file) {
        return Result.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result remove(Long id) {
        return Result.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] ids) {
        return Result.operate(fileService.batchRemove(ids) > 0);
    }
}
