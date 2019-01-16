package io.lounge.api;

import java.math.BigDecimal;
import io.lounge.models.Comment;
import io.lounge.models.NewPost;
import io.lounge.models.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class PostsApiController implements PostsApi {

    private static final Logger log = LoggerFactory.getLogger(PostsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Boolean> comment(@ApiParam(value = "The id of user to log out" ,required=true )  @Valid @RequestBody Comment newPost) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Boolean>(objectMapper.readValue("true", Boolean.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Post> getPost(@ApiParam(value = "",required=true) @PathVariable("postId") BigDecimal postId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Post>(objectMapper.readValue("{  \"hashtags\" : [ {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  }, {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  } ],  \"responses\" : [ null, null ],  \"id\" : 6.02745618307040320615897144307382404804229736328125,  \"text\" : \"text\",  \"type\" : 5.962133916683182377482808078639209270477294921875,  \"userId\" : 1.46581298050294517310021547018550336360931396484375,  \"isCorrectAnswer\" : true,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}", Post.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Post>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Post>> getUserPosts(@ApiParam(value = "",required=true) @PathVariable("userId") BigDecimal userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Post>>(objectMapper.readValue("[ {  \"hashtags\" : [ {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  }, {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  } ],  \"responses\" : [ null, null ],  \"id\" : 6.02745618307040320615897144307382404804229736328125,  \"text\" : \"text\",  \"type\" : 5.962133916683182377482808078639209270477294921875,  \"userId\" : 1.46581298050294517310021547018550336360931396484375,  \"isCorrectAnswer\" : true,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"}, {  \"hashtags\" : [ {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  }, {    \"name\" : \"name\",    \"postsWithThisHashtag\" : [ 5.63737665663332876420099637471139430999755859375, 5.63737665663332876420099637471139430999755859375 ],    \"conditions\" : {      \"levelValues\" : [ \"levelValues\", \"levelValues\" ],      \"name\" : \"name\",      \"collection\" : \"collection\",      \"operator\" : \"operator\"    }  } ],  \"responses\" : [ null, null ],  \"id\" : 6.02745618307040320615897144307382404804229736328125,  \"text\" : \"text\",  \"type\" : 5.962133916683182377482808078639209270477294921875,  \"userId\" : 1.46581298050294517310021547018550336360931396484375,  \"isCorrectAnswer\" : true,  \"timestamp\" : \"2000-01-23T04:56:07.000+00:00\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Post>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Post>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Boolean> post(@ApiParam(value = "The id of user to log out" ,required=true )  @Valid @RequestBody NewPost newPost) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Boolean>(objectMapper.readValue("true", Boolean.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Boolean>(HttpStatus.NOT_IMPLEMENTED);
    }

}
