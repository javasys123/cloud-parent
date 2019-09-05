package tjs.ax.common.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserToken{

    private String userId;
    private String username;
    private String name;


}
