package model;

import lombok.Builder;
import lombok.Data;

/**
 * @author pradipta.sarma
 * @since 03/04/22
 */
@Data
@Builder
public class Slot {
    private int from;
    private int to;
}
