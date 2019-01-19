/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.lounge.api.interfaces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "upload", description = "the file upload API")
public interface FileApi {

    @ApiOperation(value = "upload image to server, identified by filename", nickname = "uploadImage", notes = "upload files", response = Boolean.class, tags={ "upload", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "File was uploaded", response = Boolean.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/upload/image",
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file);
}