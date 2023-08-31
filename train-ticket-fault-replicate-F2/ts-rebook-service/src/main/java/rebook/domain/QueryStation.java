package rebook.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Chenjie Xu on 2017/5/19.
 */
public class QueryStation {

    @Valid
    @NotNull
    private String name;

    public QueryStation(){
        //Default Constructor
    }

    public QueryStation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
