package org.stellardev.galacticlib.item;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkullWrapper {

    @Getter @Setter private String owner, texture;

}
