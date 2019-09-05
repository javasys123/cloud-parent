package tjs.ax.common.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class RouterDto{
    private String path;
    private String component;
    private Long id;
    private String name;

    private String redirect;
    /**
     * 是否为叶子节点
     */
    private boolean leaf;
    private boolean menuShow;
    private Long parentId;
    private String iconCls;
    List<RouterDto> children;

    public static List<RouterDto> buildList(List<RouterDto> nodes, Long idParam) {
        if (nodes == null) {
            return null;
        }
        List<RouterDto> topNodes = new ArrayList<RouterDto>();
        for (RouterDto child : nodes) {
            Long pid = child.getParentId();
            if (pid == null || idParam == pid) {
                topNodes.add(child);
                continue;
            }
            for (RouterDto parent : nodes) {
                Long id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(child);
                    // child.setHasParent(true);
                   // parent.setChildren(true);
                    //parent.setLeaf(false);
                    continue;
                } else {
                   // parent.setLeaf(true);
                }
            }
        }
        return topNodes;
    }
}
