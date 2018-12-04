package com.ying.auth.payload;

import com.ying.core.data.tree.payload.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bvvy
 * @date 2018/9/18
 */
@Data
@AllArgsConstructor
public class MenuPayload  implements Payload {
    private String path;
    private String icon;
}
