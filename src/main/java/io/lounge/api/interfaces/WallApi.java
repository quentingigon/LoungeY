/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.lounge.api.interfaces;

import io.lounge.models.Wall;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Api(value = "wall", description = "the wall API")
public interface WallApi {

    @ApiOperation(value = "Get wall of user", nickname = "getWall", notes = "Return public info of user", response = Wall.class, responseContainer = "List", tags={ "wall", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Wall.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/wall/{idUser}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Wall> getWall(@ApiParam(value = "", required = true) @PathVariable("idUser") String idUser);

}
