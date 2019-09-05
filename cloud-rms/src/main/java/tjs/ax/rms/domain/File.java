package tjs.ax.rms.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 文件上传
 *
 */
@Data
@ToString
public class File{

	private Long id;
	//文件类型
	private Integer type;
	//URL地址
	private String url;
	//创建时间
	private Date createDate;

}
