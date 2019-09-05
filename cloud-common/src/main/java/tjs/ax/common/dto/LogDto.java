package tjs.ax.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class LogDto {
	private Long id;
	private Long userId;
	private String username;
	private String operation;
	private Integer time;
	private String method;
	private String params;
	private String ip;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;

}