package org.coderearth.springcliapp.command;

import java.time.Year;

/**
 * Created by kunal_patel on 20/11/17.
 */
@lombok.Builder
@lombok.Getter
@lombok.ToString
public class Command {

    private String username;
    private String password;
    private Year year;

}
