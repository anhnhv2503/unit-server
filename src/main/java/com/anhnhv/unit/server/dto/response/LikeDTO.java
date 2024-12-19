package com.anhnhv.unit.server.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeDTO {

    private boolean liked;
    private String message;
    private Object data;
}
