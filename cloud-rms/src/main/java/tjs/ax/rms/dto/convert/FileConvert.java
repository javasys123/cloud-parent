package tjs.ax.rms.dto.convert;


import org.mapstruct.factory.Mappers;
import tjs.ax.rms.domain.File;
import tjs.ax.rms.dto.FileDto;

import java.util.List;

@org.mapstruct.Mapper
public interface FileConvert {
    FileConvert MAPPER = Mappers.getMapper(FileConvert.class);

    public FileDto do2dto(File person);

    public List<FileDto> dos2dtos(List<File> list);
}