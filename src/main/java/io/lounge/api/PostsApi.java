/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.lounge.api;

import java.math.BigDecimal;
import io.lounge.models.Comment;
import io.lounge.models.NewPost;
import io.lounge.models.Post;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Api(value = "posts", description = "the posts API")
public interface PostsApi {

    @ApiOperation(value = "Add comment to post", nickname = "comment", notes = "Allow a user to post a comment", response = Boolean.class, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Boolean.class),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/posts/comment",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Boolean> comment(@ApiParam(value = "The id of user to log out", required = true) @Valid @RequestBody Comment newPost);


    @ApiOperation(value = "Get particular post", nickname = "getPost", notes = "Get a post", response = Post.class, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Post.class),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/posts/get/{postId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Post> getPost(@ApiParam(value = "", required = true) @PathVariable("postId") BigDecimal postId);


    @ApiOperation(value = "Get all posts from user", nickname = "getUserPosts", notes = "Get all the posts of particular user", response = Post.class, responseContainer = "List", tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Post.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/posts/getall/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Post>> getUserPosts(@ApiParam(value = "", required = true) @PathVariable("userId") BigDecimal userId);


    @ApiOperation(value = "Add post of user", nickname = "post", notes = "Allow a user to post a post (ahah)", response = Boolean.class, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Boolean.class),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/posts",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Boolean> post(@ApiParam(value = "The id of user to log out", required = true) @Valid @RequestBody NewPost newPost);

}
