package tjs.ax.admin.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Token {

    private Long userId;
    private String token;
    private Date expireTime;
    private Date updateTime;

}
